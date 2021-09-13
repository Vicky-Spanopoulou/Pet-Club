import { ResourceLoader } from '@angular/compiler';
import {
  Component,
  ElementRef,
  OnInit,
  ViewChild,
  OnChanges,
} from '@angular/core';
import { Router } from '@angular/router';
import { MapComponent } from 'src/app/map/map.component';
import {
  IBusiness,
  IBusinessCategory,
  ICity,
  IPet,
  IPetCategory,
  ISubscription,
  IUser,
} from 'src/app/_models/models';
import { CategoriesService } from 'src/app/_services/categories/categories.service';
import { SubscriptionService } from 'src/app/_services/subscription/subscription.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-dash-categories',
  templateUrl: './dash-categories.component.html',
  styleUrls: [
    './dash-categories.component.css',
    '../../../assets/css/dashboardCss/Defaultdashboard.css',
  ],
})
export class DashCategoriesComponent implements OnInit, OnChanges {
  editBusinessCategories: boolean = true;

  editPetCategories: boolean = false;

  editCityCategories: boolean = false;

  editSubscriptionCategories: boolean = false;


  editFormCategories: boolean = false;

  businessCategories: any[];
  roles:any[];
  loggedInUser:IUser;

  editBusinessForm: any = {
    id: null,
    name: '',
  };

  editCityForm: any = {
    id: null,
    name: '',
    latitude: '',
    longitude: '',
  };

  cities: any[];

  city: ICity = {
    id: null,
    name: '',
    latitude: '',
    longitude: '',
  };

  pet: IPet;

  petsCat: any[];

  editPetCategoryForm: any = {
    id: null,
    name: '',
  };

  subscription: ISubscription;

  subscriptions: any[];

  editSubscriptionForm: any = {
    id: null,
    name: '',
    duration: null,
    price: null,
    role: '',
    queue: null,
    advertiseForEver: null,
  };

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
  router: Router;

  constructor(
    private categoriesService: CategoriesService,
    private subscriptionService: SubscriptionService,
    private tokenSTorageService: TokenStorageService,
    private router1: Router
  ) {
    this.tokenSTorageService.autoLogin();
    this.loggedInUser = tokenSTorageService.getUser();
    if(this.loggedInUser.role !== 'ROLE_ADMIN'){
      this.router1.navigate(['home']);
    }
  }

  ngOnInit(): void {
    this.getAllBusinessCategories();
    this.getAllCities();
    this.getAllPetCategories();
    this.getAllSubscriptions();
    this.getRoles();
  }

  ngOnChanges() {
    this.str = this.street.nativeElement.value;
  }

  onEditBusinessCategories() {
    this.editBusinessCategories = true;
    this.editPetCategories = false;
    this.editCityCategories = false;
    this.editFormCategories = false;
    this.editSubscriptionCategories = false;

  }
  onEditPetCategories() {
    this.editBusinessCategories = false;
    this.editPetCategories = true;
    this.editCityCategories = false;
    this.editFormCategories = false;
    this.editSubscriptionCategories = false;

  }
  onEditCityCategories() {
    this.editBusinessCategories = false;
    this.editPetCategories = false;
    this.editCityCategories = true;
    this.editFormCategories = false;
    this.editSubscriptionCategories = false;
  }
  onEditSubscriptionCategories() {
    this.editBusinessCategories = false;
    this.editPetCategories = false;
    this.editCityCategories = false;
    this.editFormCategories = false;
    this.editSubscriptionCategories = true;
  }
  onEditFormCategories() {
    this.editFormCategories = true;
    this.editCityForm = {
      id: null,
      name: '',
      latitude: '',
      longitude: '',
    };
    this.editBusinessForm = {
      id: null,
      name: '',
    };
    this.editPetCategoryForm = {
      id: null,
      name: '',
    };
    this.editSubscriptionForm = {
      id: null,
      name: '',
      duration: null,
      price: null,
      role: '',
    };
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

    if (!streetPosition) {
      return;
    }

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

  //Business categories

  onSubmitBusiness(bCategory: IBusinessCategory) {
    if (bCategory.id === null) {
      //save
      this.categoriesService.createBusinessCategory(bCategory).subscribe(
        (data) => {
          this.getAllBusinessCategories();
          this.editFormCategories = false;
        },
        (error) => null
      );
    } else {
      //update
      this.categoriesService.editBusinessCategory(bCategory).subscribe(
        (data) => (this.editFormCategories = false),
        (error) => null
      );
    }
  }

  getAllBusinessCategories() {
    this.categoriesService
      .getAllBusinessCategories()
      .subscribe((actualBusiness: IBusinessCategory[]) => {
        this.businessCategories = actualBusiness;
      });
  }

  editBusines(business: any) {
    this.editFormCategories = true;
    this.editBusinessForm = business;
  }

  // DELETE
  onDeleteBusinessCat(bCategory: IBusinessCategory) {
    let id = bCategory.id;
    let answer = confirm('Do you want to delete this business category?');
    if (answer && id != null) {
      this.categoriesService.deleteBusinessCategoryById(id)
      .subscribe((deletedBCategory) => {
          this.getAllBusinessCategories();
          this.editFormCategories = false;
        });
    }
  }

  //Cities
  getAllCities() {
    this.categoriesService.getAllCities().subscribe((actualCities: any[]) => {
      this.cities = actualCities.sort((a, b) => (a.name > b.name)?1:-1);
    });
  }

  onSubmitCity(city: any) {
    city.latitude = this.latitude.nativeElement.value;
    city.longitude = this.longitude.nativeElement.value;

    if (city.id === null) {
      //save
      this.categoriesService.createCity(city).subscribe(
        (data) => {
          this.getAllCities();
          this.editFormCategories = false;
        },
        (error) => null
      );
    } else {
      //update
      this.categoriesService.editCity(city).subscribe(
        (data) => this.editFormCategories = false,
        (error) => null
      );
    }
  }

  editCity(city: any) {
    this.editFormCategories = true;
    this.editCityForm = city;
  }

  //edit true while true service for update addnew make false the edit

  // DELETE
  onDeleteCity(city: ICity) {
    let id = city.id;
    let answer = confirm('Do you want to delete this city?');
    if (answer && id != null) {
      this.categoriesService
        .deleteCityById(id)
        .subscribe((deletedCity) => {
          this.getAllCities();
          this.editFormCategories = false;
        });
    }
  }

  // Pet categories
  getAllPetCategories() {
    this.categoriesService
      .getAllPetCategories()
      .subscribe((actualPetCat: IPetCategory[]) => {
        this.petsCat = actualPetCat;
      });
  }

  editPetCategory(pet: IPet) {
    this.editFormCategories = true;
    this.editPetCategoryForm = pet;
  }

  onSubmitPetCat(petCat: IPetCategory) {
    if (petCat.id === null) {
      //save
      this.categoriesService.createPetCategory(petCat).subscribe(
        (data) => {
          this.getAllPetCategories();
          this.editFormCategories = false;
        },
        (error) => null
      );
    } else {
      //update
      this.categoriesService.editPetCategory(petCat).subscribe(
        (data) => this.editFormCategories = false,
        (error) => null
      );
    }
  }

  //delete
  onDeletePetCat(petCat: IPetCategory) {
    let id = petCat.id;
    let answer = confirm('Do you want to delete this pet category?');
    if (answer && id != null) {
      this.categoriesService
        .deletePetCategoryById(id)
        .subscribe((deletedPetCat) => {
          this.getAllPetCategories();
          this.editFormCategories = false;
        });
    }
  }


  // Subscriptions

  getAllSubscriptions() {
    this.subscriptionService
      .getAllSubscriptions()
      .subscribe((actualSubs: ISubscription[]) => {
        this.subscriptions = actualSubs;
      });
  }
  getRoles(){
    this.categoriesService.getAllRoles().subscribe((actualyRoles:any[]) =>{
      this.roles = actualyRoles;
    } )
  }

  editSubscription(subscription: ISubscription) {
    this.editFormCategories = true;
    this.editSubscriptionForm = subscription;
  }

  onSubmitSubscription(subscription: ISubscription) {
    if (subscription.id === null) {
      //save
      this.subscriptionService.createSubscription(subscription).subscribe(
        (data) => {
          this.getAllSubscriptions();
          this.editFormCategories = false;
        },
        (error) => null
      );
    } else {
      //update
      this.subscriptionService.editSubscription(subscription).subscribe(
        (data) => this.editFormCategories = false,
        (error) => null
      );
    }
  }

  //delete
  onDeleteSubscription(subscription: ISubscription) {
    let id = subscription.id;
    let answer = confirm('Do you want to delete this subscription?');
    if (answer && id != null) {
      this.subscriptionService
        .deleteSubscriptionById(id)
        .subscribe((deletedSub) => {
          this.getAllSubscriptions();
          this.editFormCategories = false;
        });
    }
  }
}
