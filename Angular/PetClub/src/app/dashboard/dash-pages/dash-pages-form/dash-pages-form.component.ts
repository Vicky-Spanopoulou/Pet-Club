import { HttpEventType, HttpResponse } from '@angular/common/http';
import { ThrowStmt } from '@angular/compiler';
import { Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { Editor, schema, toDoc, toHTML } from 'ngx-editor';
import { Observable } from 'rxjs';
import { MapComponent } from 'src/app/map/map.component';
import { IBusinessCategory, ICity, IUser } from 'src/app/_models/models';
import { BusinessService } from 'src/app/_services/business/business.service';
import { CategoriesService } from 'src/app/_services/categories/categories.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-dash-pages-form',
  templateUrl: './dash-pages-form.component.html',
  styleUrls: ['./dash-pages-form.component.css'],
})
export class DashPagesFormComponent implements OnInit {
  editor: Editor;
  editor2: Editor;
  isSuccessful: boolean = false;
  selectedFiles?: FileList;
  selectedFiles1?: FileList;
  progress = 0;
  message = '';
  fileInfos?: Observable<any>;

  businessOwner: any = {
    email: null,
  };
  
  hideSearchOwers: boolean = false;
  showMap: boolean = false;
  descriptionJson: any;
  textJson: any;
  pageStatus:any;

  @Input() dtoBusiness: any;

  @ViewChild('mapComponent', { static: false })
  private mapElement: MapComponent;

  @ViewChild('latitude', { static: false })
  latitude: ElementRef;

  @ViewChild('longitude', { static: false })
  longitude: ElementRef;

  @ViewChild('street', { static: false })
  street: ElementRef;

  lat: string = '';
  lng: string = '';
  str: string = '';

  selectedOwner: any = {
    id: 0,
    username: '',
    password: '',
    firstName: '',
    lastName: '',
    email: '',
    role: '',
    phone: '',
    img: '',
    pets: [],
  };

  owners!: any[];
  citiesCategory!: any[];
  businessCategories!: any[];

  filterOwners: any[] = this.owners;

  @Output() notify = new EventEmitter<any>();

  constructor(
    private serviceUser: UserService,
    private serviceBusiness: BusinessService,
    private serviceCategories: CategoriesService
  ) {
    this.editor = new Editor();
    this.editor2 = new Editor();
  }

  ngOnInit(): void {
    this.getAllCitiesCategories();
    this.getAllBusinessCategories();
    this. getPageStatus();

    if (this.dtoBusiness.description || this.dtoBusiness.text) {
      this.descriptionJson = toDoc(this.dtoBusiness.description);
      this.textJson = toDoc(this.dtoBusiness.text);
    }
    if (this.dtoBusiness.id !== null) {
      this.getUserByBusinessId();
    }
  }

  submitBussForm(): void {
    this.dtoBusiness.latitude = this.latitude.nativeElement.value;
    this.dtoBusiness.longitude = this.longitude.nativeElement.value;

    if (this.dtoBusiness.descriptionJson != null) {
      this.dtoBusiness.description = toHTML(this.dtoBusiness.descriptionJson, schema);
    }
    if (this.dtoBusiness.textJson != null) {
      this.dtoBusiness.text = toHTML(this.dtoBusiness.textJson, schema);
    }
    //update
    this.serviceBusiness.editBusiness(this.dtoBusiness).subscribe(
      (data) => {
        this.notify.emit();
      },
      (error) => null
    );
    window.scrollTo(0, 0);
  }

  selectImgLogo(event: any): void {
    if (event.target.files) {
      let reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      this.selectedFiles = event.target.files;
      reader.onload = (e: any) => {
        this.dtoBusiness.imgLogoURL = e.target.result;
      };
    }
    this.uploadLogoImg();
  }

  selectImgBackground(event: any) {
    if (event.target.files) {
      let reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      this.selectedFiles1 = event.target.files;
      reader.onload = (e: any) => {
        this.dtoBusiness.imgBackgroundURL = e.target.result;
      };
    }
    this.uploadBackgroundImg();
  }

  uploadBackgroundImg(): void {
    this.progress = 0;
    if (this.selectedFiles1) {
      const file: File | null = this.selectedFiles1[0];
      if (file) {
        this.serviceBusiness
          .uploadBg(file, this.businessOwner.username, this.dtoBusiness.id)
          .subscribe(
            (event: any) => {
              if (event.type === HttpEventType.UploadProgress) {
                this.progress = Math.round((100 * event.loaded) / event.total);
              } else if (event instanceof HttpResponse) {
                this.message = event.body.message;
                this.fileInfos = this.serviceBusiness.getFilesBg(
                  this.businessOwner.username,
                  this.dtoBusiness.id
                );
              }
            },
            (err: any) => {
              this.progress = 0;
              if (err.error && err.error.message) {
                this.message = err.error.message;
              } else {
                this.message = 'Could not upload the file!';
              }
            }
          );
      }
    }
  }

  uploadLogoImg(): void {
    this.progress = 0;
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles[0];
      if (file) {
        this.serviceBusiness
          .uploadLogo(file, this.businessOwner.username, this.dtoBusiness.id)
          .subscribe(
            (event: any) => {
              if (event.type === HttpEventType.UploadProgress) {
                this.progress = Math.round((100 * event.loaded) / event.total);
              } else if (event instanceof HttpResponse) {
                this.message = event.body.message;
                this.fileInfos = this.serviceBusiness.getFilesLogo(
                  this.businessOwner.username,
                  this.dtoBusiness.id
                );
              }
            },
            (err: any) => {
              this.progress = 0;
              if (err.error && err.error.message) {
                this.message = err.error.message;
              } else {
                this.message = 'Could not upload the file!';
              }
            }
          );
      }
    }
  }

  mapClick(event: any): void {
    this.mapElement.cleanMarkers();
    let queryPosition = this.mapElement.getPositionAt(
      event.offsetX,
      event.offsetY
    );
    this.mapElement.addMarker({
      latitude: queryPosition.lat,
      longitude: queryPosition.lng,
    });
    this.lat = queryPosition.lat;
    this.lng = queryPosition.lng;
  }

  findStreet() {
    let streetPosition: any = this.mapElement.geocodeAddress(this.str);
    this.lat = streetPosition.Latitude;
    this.lng = streetPosition.Longitude;
    this.mapElement.cleanMarkers();
    this.mapElement.addMarker({
      latitude: streetPosition.Latitude,
      longitude: streetPosition.Longitude,
    });
    this.mapElement.moveMapToLocation(
      {
        latitude: streetPosition.Latitude,
        longitude: streetPosition.Longitude,
      },
      8
    );
  }

  getUserByBusinessId() {
    this.serviceUser
      .getUserByBusinessId(this.dtoBusiness.id)
      .subscribe((acualUser: IUser) => {
        this.businessOwner = acualUser;
      });
  }

  getAllCitiesCategories() {
    this.serviceCategories
      .getAllCities()
      .subscribe((acualyCityCategories: any[]) => {
        this.citiesCategory = acualyCityCategories.sort((a, b) => (a.name > b.name)?1:-1);
      });
  }
  getAllBusinessCategories() {
    this.serviceCategories
      .getAllBusinessCategories()
      .subscribe((acualyBusinessCategories: IBusinessCategory[]) => {
        this.businessCategories = acualyBusinessCategories;
      });
  }

  getPageStatus(){
    this.serviceCategories.getAllPageStatuses().subscribe((actualyPageStatus:any[]) => {
      this.pageStatus = actualyPageStatus;
    });
  }
  
}
