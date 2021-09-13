import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Editor, schema, toDoc, toHTML } from 'ngx-editor';
import { Observable } from 'rxjs';
import { MapComponent } from 'src/app/map/map.component';
import { IBusiness, IBusinessCategory, ICity, IUser } from 'src/app/_models/models';
import { BusinessService } from 'src/app/_services/business/business.service';
import { CategoriesService } from 'src/app/_services/categories/categories.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { BusinessProfileComponent } from '../../business-profile/business-profile.component';

@Component({
  selector: 'app-company-page',
  templateUrl: './company-page.component.html',
  styleUrls: ['./company-page.component.css',
              '../../../../assets/css/containers.css',
              '../../../../assets/css/default.css',
              '../user-properties/user-properties.component.css']
})
export class CompanyPageComponent implements OnInit {

  user:IUser;
  business:IBusiness;
  imgUrlLogo:any;
  imgUrlBg:any;
  editorSomeWords: Editor;
  editorDescriptions: Editor;
  someWordsJson: any;
  descriptionJson: any;

  busForm: any = {
    id:null,
    name: "",
    email: "",
    phone: "",
    description: "",
    text: "",
    imgLogo:"",
    imgBackground:"",
    facebook: "",
    instagram:"",
    pageUrl:"",
    addressId:"",
    street: "",
    postalCode: "",
    city: "",
    longitude:"",
    latitude:"",
    businessCategory:""
  };

  businessCategories:IBusinessCategory[];
  cities: ICity[];
  isSuccessful: boolean = false;

  selectedFilesLogo?: FileList;
  progressLogo = 0;
  messageLogo = '';
  fileInfosLogo?: Observable<any>;

  selectedFilesBg?: FileList;
  progressBg = 0;
  messageBg = '';
  fileInfosBg?: Observable<any>;

  queryPosition: any;

  @ViewChild('mapComponent', { static: false })
  map: MapComponent;

  constructor(
    private tokenStorageService:TokenStorageService,
    private businessService:BusinessService,
    private categoriesService:CategoriesService,
    private router: Router
  ) 
  {
    this.user = this.tokenStorageService.getUser();
    if(!this.user){
      this.router.navigate(['/home']);
    }
  }

  ngOnInit(): void {
    this.editorSomeWords = new Editor();
    this.editorDescriptions = new Editor();
    this.getAllBusinessCategories();
    this.getAllCities();
    this.getBusiness();
  }

  toggleEdit(){
    let buttons = document.getElementsByClassName('edit-buttons');
    let inactive = document.getElementsByClassName('bis-input');
    for (let i = 0; i < buttons.length; i++) {
      buttons[i].classList.toggle('hidden');
    }
    for (let i = 0; i < inactive.length; i++) {
      inactive[i].classList.toggle('disabled-input');
    }
  }

  cancelEdit(){
    this.toggleEdit();
    this.fillCanceledForm();
  }

  getAllBusinessCategories() {
    this.categoriesService.
    getAllBusinessCategories()
      .subscribe((actualBusCat: IBusinessCategory[]) => {
        this.businessCategories = actualBusCat;
      });
  }

  getAllCities() {
    this.categoriesService.getAllCities().subscribe((actualCities: ICity[]) => {
      this.cities = actualCities;
    });
  }

  selectFileLogo(event: any): void {
    if (event.target.files) {
      let reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      this.selectedFilesLogo=event.target.files;
      reader.onload = (e: any) => {
        this.imgUrlLogo = e.target.result;
      };
    }
    this.uploadLogo();
  }

  selectFileBg(event: any): void {
    if (event.target.files) {
      let reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      this.selectedFilesBg=event.target.files;
      reader.onload = (e: any) => {
        this.imgUrlBg = e.target.result;
      };
    }
    this.uploadBg();
  }

  mapClick(event: any): void {
    let queryPosition = this.map.getPositionAt(event.offsetX, event.offsetY);
    this.busForm.latitude = queryPosition.lat;
    this.busForm.longitude = queryPosition.lng;
    this.map.cleanMarkers();
    this.map.addMarker({
      latitude: queryPosition.lat,
      longitude: queryPosition.lng,
    });
  }

  searchPosition(): void {
    let queryPosition = this.map.geocodeAddress(this.busForm.street);
    this.busForm.latitude = queryPosition.Latitude;
    this.busForm.longitude = queryPosition.Longitude;
    this.map.cleanMarkers();
    this.map.addMarker({
      latitude: queryPosition.Latitude,
      longitude: queryPosition.Longitude,
    });
    this.map.moveMapToLocation(
      { latitude: queryPosition.Latitude, longitude: queryPosition.Longitude },
      18
    );
  }


  submitSubForm(): void {

    if (this.someWordsJson != null){
      this.busForm.text = toHTML(this.someWordsJson, schema);
    }
    if (this.descriptionJson != null) {
      this.busForm.description = toHTML(this.descriptionJson, schema);
    }
    if (this.busForm.id === null) {
      //save new
      this.businessService.createBusiness(this.busForm).subscribe(
        (data:IBusiness) => {
          this.busForm.id = data.id;
          if(this.selectedFilesLogo){
          }
          if(this.selectedFilesBg){
          }
      },
        (error) => null
      );
    } else {
      //update update existing entry
      this.businessService.editBusiness(this.busForm).subscribe(
        (data) => null,
        (error) => null
      );
    }
    this.toggleEdit();
  }


  uploadLogo():void{
    this.progressLogo=0;
    if(this.selectedFilesLogo){
      const file:File|null =this.selectedFilesLogo[0];
      if(file){
        this.businessService.uploadLogo(file,this.user.username,this.busForm.id).subscribe(
          (event:any)=>{
            if(event.type=== HttpEventType.UploadProgress){
              this.progressLogo = Math.round(100 * event.loaded / event.total);
            }else if (event instanceof HttpResponse){
              this.messageLogo = event.body.message;
              this.fileInfosLogo = this.businessService.getFilesLogo(this.user.username,this.busForm.id);
            }
          },
          (err:any)=>{
            this.progressLogo=0;
            if (err.error && err.error.message) {
              this.messageLogo = err.error.message;
            } else {
              this.messageLogo = 'Could not upload the file!';
            }
          });
      }
    }
  }


  uploadBg():void{
    this.progressBg=0;
    if(this.selectedFilesBg){
      const file:File|null =this.selectedFilesBg[0];
      if(file){
        this.businessService.uploadBg(file,this.user.username,this.busForm.id).subscribe(
          (event:any)=>{
            if(event.type=== HttpEventType.UploadProgress){
              this.progressBg = Math.round(100 * event.loaded / event.total);
            }else if (event instanceof HttpResponse){
              this.messageBg = event.body.message;
              this.fileInfosBg = this.businessService.getFilesBg(this.user.username,this.busForm.id);
            }
          },
          (err:any)=>{
            this.progressBg=0;
            if (err.error && err.error.message) {
              this.messageBg = err.error.message;
            } else {
              this.messageBg = 'Could not upload the file!';
            }
          });
      }
    }

  }


  getBusiness(){
    this.businessService.getBusinessesByUserId(this.user.id).subscribe(
      (actualBusiness:IBusiness)=>{
        this.business = actualBusiness;
        this.fillTheForm();
      }
    );
  }


  fillTheForm(){
    this.fillCanceledForm();
    this.imgUrlBg = this.business.imgBackground;
    this.imgUrlLogo = this.business.imgLogo;
  }

  fillCanceledForm(){
    this.busForm.id = this.business.id;
    this.busForm.name = this.business.name;
    this.busForm.email = this.business.email;
    this.busForm.phone = this.business.phone;
    this.busForm.imgBackground = this.business.imgBackground;
    this.busForm.imgLogo = this.business.imgLogo;
    
    this.descriptionJson = toDoc(this.business.description);
    this.someWordsJson = toDoc(this.business.text);
    this.busForm.facebook = this.business.facebook;
    this.busForm.instagram = this.business.instagram;
    this.busForm.pageUrl = this.business.pageUrl;
    this.busForm.addressId = this.business.address.id;
    this.busForm.street = this.business.address.street;
    this.busForm.postalCode = this.business.address.postalCode
    this.busForm.city = this.business.address.city.name,
    this.busForm.longitude = this.business.address.longitude;
    this.busForm.latitude = this.business.address.latitude;
    this.busForm.businessCategory =  this.business.businessCategory.name;
    this.busForm.status = this.business.status;
  }
}
