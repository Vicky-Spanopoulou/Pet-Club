import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { Editor, schema } from 'ngx-editor';
import { MapComponent } from 'src/app/map/map.component';
import { IAd, ICity, IPetCategory, IUser } from 'src/app/_models/models';
import { CategoriesService } from 'src/app/_services/categories/categories.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { toHTML, toDoc } from 'ngx-editor';
import { AdsService } from 'src/app/_services/ads/ads.service';
import { Observable } from 'rxjs';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-ad-form',
  templateUrl: './ad-form.component.html',
  styleUrls: [
    './ad-form.component.css',
    '../../../../assets/css/forms.css',
    '../../../../assets/css/default.css',
  ],
})
export class AdFormComponent implements OnInit {
  @Input() externalAd: IAd;

  editorSomeWords: Editor;

  editorDescriptions: Editor;

  modal: boolean = false;

  user: IUser;

  maxDate:Date = new Date();

  adForm: any = {
    id: null,
    petName: null,
    chipNumber: null,
    sex: "OTHER",
    someWords: null,
    descriptions: null,
    postDate: null,
    lostDate: null,
    image: null,
    user: null,
    petCategory: null,
    adCategory: null,
    street: null,
    longitude: null,
    latitude: null,
    city: null,
  };

  someWordsJson: any;
  descriptionJson: any;

  petCategories: IPetCategory[];
  sex: string[];
  sexCategories: string[];
  cities: ICity[];
  adCategories: string[];

  isSuccessful: boolean = false;

  selectedFiles?: FileList;
  progress = 0;
  message = '';
  fileInfos?: Observable<any>;
  imgUrl= '../../../../assets/images/pet_icon.png';

  transformDate: Date;

  @ViewChild('mapComponent', { static: false })
  map: MapComponent;

  queryPosition: any;

  constructor(
    private tokenStorage: TokenStorageService,
    private categoriesService: CategoriesService,
    private adsService: AdsService,
    private userService: UserService
  ) {
    this.editorSomeWords = new Editor();
    this.editorDescriptions = new Editor();

  }

  ngOnInit(): void {
    this.user = this.tokenStorage.getUser();
    this.adForm.user = this.user.id;
    this.fillTheFormWithExternalData();
    this.getAllPetCategories();
    this.getAllGenderCategories();
    this.getAllAdCategories();
    this.getAllCities();

      if (this.adForm.someWords || this.adForm.descriptions) {
        this.someWordsJson = toDoc(this.adForm.someWords);
        this.descriptionJson = toDoc(this.adForm.description);
      }

  }

  ngAfterViewInit(): void {
  }



  popModal() {
    this.modal = true;
  }

  closeModal() {
    this.modal = false;
  }

  getAllPetCategories() {
    this.categoriesService
      .getAllPetCategories()
      .subscribe((actualPetCat: IPetCategory[]) => {
        this.petCategories = actualPetCat;
      });
  }

  getAllGenderCategories() {
    this.categoriesService
      .getAllPetSexes()
      .subscribe((actualPetSexes: any[]) => {
        this.sexCategories = actualPetSexes;
      });
  }

  getAllAdCategories() {
    this.categoriesService
      .getAllAdCategories()
      .subscribe((actualAdCat: any[]) => {
        this.adCategories = actualAdCat;
      });
  }

  getAllCities() {
    this.categoriesService.getAllCities().subscribe((actualCities: ICity[]) => {
      this.cities = actualCities;
    });
  }

  selectFile(event: any): void {
    if (event.target.files) {
      let reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      this.selectedFiles=event.target.files;
      reader.onload = (e: any) => {
        this.imgUrl = e.target.result;
      };
    }
  }

  mapClick(event: any): void {
    let queryPosition = this.map.getPositionAt(event.offsetX, event.offsetY);
    this.adForm.latitude = queryPosition.lat;
    this.adForm.longitude = queryPosition.lng;
    this.map.cleanMarkers();
    this.map.addMarker({
      latitude: queryPosition.lat,
      longitude: queryPosition.lng,
    });
  }

  searchPosition(): void {
    let queryPosition = this.map.geocodeAddress(this.adForm.street);
    this.adForm.latitude = queryPosition.Latitude;
    this.adForm.longitude = queryPosition.Longitude;
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

  submitAdForm(): void {

    if (this.someWordsJson != null){
      this.adForm.someWords = toHTML(this.someWordsJson, schema);
    }
    if (this.descriptionJson != null) {
      this.adForm.descriptions = toHTML(this.descriptionJson, schema);
    }
    
    if (this.adForm.id === null) {
      //save new
      this.adsService.createAd(this.adForm).subscribe(
        (data:IAd) => {
          this.adForm.id = data.id;
          if(this.selectedFiles){
            this.upload();
          }
          this.getUser();

      },
        (error) => null
      );
    } else {
      //update update existing entry
      this.adsService.editAd(this.adForm).subscribe(
        (data) => {
          this.getUser();
        },
        (error) => null
      );
      this.upload();
    }
  }

  upload():void{
    this.progress=0;
    if(this.selectedFiles){
      const file:File|null =this.selectedFiles[0];
      if(file){
        this.adsService.upload(file,this.user.username,this.adForm.id).subscribe(
          (event:any)=>{
            if(event.type=== HttpEventType.UploadProgress){
              this.progress = Math.round(100 * event.loaded / event.total);
            }else if (event instanceof HttpResponse){
              this.message = event.body.message;
              this.fileInfos = this.adsService.getFiles(this.user.username,this.adForm.id);
            }
          },
          (err:any)=>{
            this.progress=0;
            if (err.error && err.error.message) {
              this.message = err.error.message;
            } else {
              this.message = 'Could not upload the file!';
            }
          });
      }
    }

  }

  fillTheFormWithExternalData(): void {
    if (this.externalAd) {
      (this.adForm.id = this.externalAd.id),
      (this.adForm.petName = this.externalAd.petName),
      (this.adForm.chipNumber = this.externalAd.chipNumber),
      (this.adForm.sex = this.externalAd.sex),
      (this.someWordsJson = toDoc(this.externalAd.someWords)),
      (this.descriptionJson = toDoc(this.externalAd.descriptions)),
      (this.adForm.postDate = this.externalAd.postDate),
      (this.adForm.lostDate = this.externalAd.lostDate),
      (this.adForm.image = this.externalAd.image),
      (this.imgUrl = this.externalAd.image),
      (this.adForm.user = this.user.id),
      (this.adForm.petCategory = this.externalAd.petCategory.name),
      (this.adForm.adCategory = this.externalAd.adCategory),
      (this.adForm.street = this.externalAd.address.street),
      (this.adForm.longitude = this.externalAd.address.longitude),
      (this.adForm.latitude = this.externalAd.address.latitude),
      (this.adForm.city = this.externalAd.address.city.name);
    }
  }

  reloadPage(): void {
    window.location.reload();
  }


  getUser(): void {
    this.userService
      .getUserByUsername(this.user.username)
      .subscribe((event: any) => {
        this.user = event;
        this.tokenStorage.saveUser(event);
      });
      this.closeModal();
  }
}
