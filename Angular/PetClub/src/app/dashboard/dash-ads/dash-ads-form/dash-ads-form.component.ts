import { HttpEventType, HttpResponse } from '@angular/common/http';
import {
  Component,
  ElementRef,
  EventEmitter,
  Input,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { Editor, schema, toDoc, toHTML } from 'ngx-editor';
import { Observable } from 'rxjs';
import { MapComponent } from 'src/app/map/map.component';
import { IAd, ICity, IUser } from 'src/app/_models/models';
import { AdsService } from 'src/app/_services/ads/ads.service';
import { CategoriesService } from 'src/app/_services/categories/categories.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-dash-ads-form',
  templateUrl: './dash-ads-form.component.html',
  styleUrls: [
    './dash-ads-form.component.css',
    '../../../../assets/css/dashboardCss/DashboardForms.css',
  ],
})
export class DashAdsFormComponent implements OnInit {
  hideSearchOwers: boolean = false;
  searchOwner: string;

  petCategories: any[];
  adCategories: any[];
  sexCategories: any[];
  cityCategories: any[];

  @Input() dtoUser: any;
  @Input() dtoAd!: any;

  editor: Editor;
  editor2: Editor;

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

  isSuccessful: boolean = false;
  selectedFiles?: FileList;
  progress = 0;
  message = '';
  fileInfos?: Observable<any>;
  user: IUser;
  imgUrl: string = '../../../../assets/images/pet_icon.png';

  selectedOwner: any = {
    id: 0,
    username: '',
    firstName: '',
    lastName: '',
    email: '',
  };

  owners!: any[];

  filterOwners: any[] = this.owners;

  descriptionJson: any;

  someWordsJson: any;

  @Output() notify = new EventEmitter<any>();

  constructor(
    private userService: UserService,
    private adsService: AdsService,
    private serviceCategories: CategoriesService
  ) {
    this.editor = new Editor();
    this.editor2 = new Editor();
  }

  ngOnInit(): void {

    this.getAllUsers();
    this.getAllPetCategories();
    this.getAllAdCategories();
    this.getSexCategories();
    this.getAllCitiesCategories();

    if (this.dtoAd.id != null) {
      this.getUserByAd();
    }

    if (this.dtoAd.descriptions) {
      this.descriptionJson = toDoc(this.dtoAd.descriptions);
    }

    if (this.dtoAd.someWords) {
      this.someWordsJson = toDoc(this.dtoAd.someWords);
    }
  }

  ngOnChanges() {}

  handleUsers() {
    this.hideSearchOwers = true;
    this.filterOwners = this.owners;

    if (this.searchOwner) {
      this.filterOwners = this.filterOwners.filter(
        (x) =>
          x.email.toUpperCase().includes(this.searchOwner.toUpperCase()) ||
          x.firstName.toUpperCase().includes(this.searchOwner.toUpperCase()) ||
          x.lastName.toUpperCase().includes(this.searchOwner.toUpperCase())
      );
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

  selectFile(event: any): void {
    if (event.target.files) {
      let reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      this.selectedFiles = event.target.files;
      reader.onload = (e: any) => {
        this.dtoAd.image = e.target.result;
      };
    }
  }

  submitAdForm(): void {
    this.dtoAd.latitude = this.latitude.nativeElement.value;
    this.dtoAd.longitude = this.longitude.nativeElement.value;
    this.dtoAd.user = this.selectedOwner.id;

    if (this.dtoAd.descriptionJson) {
      this.dtoAd.descriptions = toHTML(this.dtoAd.descriptionJson, schema);
    }

    if (this.dtoAd.someWordsJson) {
      this.dtoAd.someWords = toHTML(this.dtoAd.someWordsJson, schema);
    }

    if (this.dtoAd.id === null) {
      this.adsService.createAd(this.dtoAd).subscribe(
        (data: IAd) => {
          this.dtoAd.id = data.id;
          if (this.selectedFiles) {
            this.upload();
          }
          this.notify.emit();
        },
        (error) => null
      );
      window.scrollTo(0, 0);
    } else {
      this.adsService.editAd(this.dtoAd).subscribe(
        (data) => {
          if (this.selectedFiles) {
            this.upload();
          }
          this.notify.emit();
        },
        (error) => null
      );
      window.scrollTo(0, 0);
    }
  }

  onDeleteAd() {
    let answer = confirm('Do you want to delete this ad?');
    if (answer && this.dtoAd.id != null) {
      this.adsService.deleteAdById(this.dtoAd.id).subscribe((deletedCity) => {
        this.notify.emit();
      });
      window.scrollTo(0, 0);
    }
  }

  upload(): void {
    this.progress = 0;
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles[0];
      if (file) {
        this.adsService
          .upload(file, this.dtoUser.username, this.dtoAd.id)
          .subscribe(
            (event: any) => {
              if (event.type === HttpEventType.UploadProgress) {
                this.progress = Math.round((100 * event.loaded) / event.total);
              } else if (event instanceof HttpResponse) {
                this.message = event.body.message;
                this.fileInfos = this.adsService.getFiles(
                  this.dtoUser.username,
                  this.dtoAd.id
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

  // <========= Here is the methods for find owners ==========>
  showOwners() {
    this.hideSearchOwers = true;
  }
  selectOwner(owner: any) {
    this.selectedOwner = owner;
    this.hideSearchOwers = false;
  }

  hideOwners() {
    this.hideSearchOwers = false;
  }
  getAllUsers() {
    this.userService.getAllUsers().subscribe((actualUsers: IUser[]) => {
      this.owners = actualUsers;
      this.filterOwners = this.owners;
    });
  }
  // <========= END find owners  methods==========>

  getAllPetCategories() {
    this.serviceCategories
      .getAllPetCategories()
      .subscribe((actualyPetCategory: any[]) => {
        this.petCategories = actualyPetCategory;
      });
  }

  getAllAdCategories() {
    this.serviceCategories
      .getAllAdCategories()
      .subscribe((actualyAdCategory: any[]) => {
        this.adCategories = actualyAdCategory;
      });
  }

  getSexCategories() {
    this.serviceCategories.getAllPetSexes().subscribe((acualSex: any[]) => {
      this.sexCategories = acualSex;
    });
  }
  getUserByAd() {
    this.userService
      .getUserByAd(this.dtoAd.id)
      .subscribe((actualuUser: IUser) => {
        this.selectedOwner = actualuUser;
      });
  }
  getAllCitiesCategories() {
    this.serviceCategories.getAllCities().subscribe((actualuCities: any[]) => {
      this.cityCategories = actualuCities.sort((a, b) =>
        a.name > b.name ? 1 : -1
      );
    });
  }
}
