import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MapComponent } from 'src/app/map/map.component';
import { IBusiness, IBusinessCategory, ICity } from 'src/app/_models/models';
import { BusinessService } from 'src/app/_services/business/business.service';
import { CategoriesService } from 'src/app/_services/categories/categories.service';
import { RatingService } from 'src/app/_services/rating/rating.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-find-service',
  templateUrl: './find-service.component.html',
  styleUrls: [
    './find-service.component.css',
    '../../../assets/css/searches.css',
    '../../../assets/css/default.css',
    '../../../assets/css/containers.css',
    '../../../assets/css/ribbon.css',
  ],
})
export class FindServiceComponent implements OnInit {
  businesses: IBusiness[];
  dataReady:boolean=false;
  providedBusinesses: IBusiness[];

  categories: IBusinessCategory[];

  ratings: string[] = ['Highest Rates First', 'Lowest Rates First'];

  cities: ICity[];

  searchName: string = '';
  city: string = 'All';
  defaultCity: ICity = {
    id: 0,
    name: 'All',
    latitude: '39.11035094005105',
    longitude: '23.263987890625017',
  };

  category: string = 'Choose Category';
  rateingOption: string = 'Highest Rates First';

  @ViewChild('mapComponent', { static: false })
  private mapElement: MapComponent;

  constructor(
    private businessService: BusinessService,
    private categoryService: CategoriesService,
    private ratingService: RatingService,
    private tokenStorage:TokenStorageService
  ) {
    this.tokenStorage.autoLogin();
  }

  ngOnInit(): void {
    this.getAllPublishedBusinesses();
    this.getAllCategories();
  }

  ngOnChanges(): void {
    this.providedBusinesses = this.businesses;
  }

  ngAfterViewInit() {
  }

  getAllPublishedBusinesses() {
    this.businessService
      .getAllPublishedBusinesses()
      .subscribe((actualBusinesses: IBusiness[]) => {
        this.businesses = actualBusinesses;
        this.resetProvidedBusinesses();
        this.adMarkers();
        this.filter();
        this.dataReady=true;
      });
  }

  getAllCategories() {
    this.categoryService
      .getAllBusinessCategories()
      .subscribe((actualBusinessCategories: IBusinessCategory[]) => {
        this.categories = actualBusinessCategories;
      });

    this.categoryService.getAllCities().subscribe((actualCities: any[]) => {
      this.cities = actualCities.sort((a, b) => (a.name > b.name)?1:-1);
    });

    // this.ratingService.
  }

  resetProvidedBusinesses() {
    this.providedBusinesses = this.businesses;
    
  }

  filter() {
    this.resetProvidedBusinesses();
    //Filter Businesses by city, and make the map focus in the right ciy
    if (this.city !== this.defaultCity.name) {
      this.providedBusinesses = this.providedBusinesses.filter(
        (x) => x.address.city.name === this.city
      );
      this.cities.forEach((x) =>
        x.name === this.city
          ? this.mapElement.moveMapToLocation(x, 10): null
      );
    } else {
      this.mapElement.moveMapToLocation(
        { latitude: '39.11035094005105', longitude: '23.263987890625017' },
        6
      );
    }

    //Filter Businesses by search name
    if (this.searchName) {
      this.providedBusinesses = this.providedBusinesses.filter((x) =>
        x.name.toUpperCase().includes(this.searchName.toUpperCase())
      );
    }

    //Filter Businesses by category if value is NOT All
    if (this.category !== 'Choose Category') {
      this.providedBusinesses = this.providedBusinesses.filter((x) =>
        x.businessCategory.name.toUpperCase().includes(this.category.toUpperCase())
      );
    }

    // Filter Businesses by rating
    switch (this.rateingOption) {
      case 'Highest Rates First':
        this.providedBusinesses.sort((a, b) =>
          a.avgRating > b.avgRating ? -1 : 1
        );
        break;
      case 'Lowest Rates First':
        this.providedBusinesses.sort((a, b) =>
          a.avgRating < b.avgRating ? -1 : 1
        );
        break;
    }
  }

  

  adMarkers(): void {
    this.businesses.forEach((element) => {
      this.mapElement.addBusinessMarker(element);
    });
  }
}
