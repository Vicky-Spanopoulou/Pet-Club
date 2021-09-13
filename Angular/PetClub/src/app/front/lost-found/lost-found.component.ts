import { Component, OnChanges, OnInit, ViewChild } from '@angular/core';
import { MapComponent } from 'src/app/map/map.component';
import { IAd, ICity, IPetCategory } from 'src/app/_models/models';
import { AdsService } from 'src/app/_services/ads/ads.service';
import { CategoriesService } from 'src/app/_services/categories/categories.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-lost-found',
  templateUrl: './lost-found.component.html',
  styleUrls: [
    './lost-found.component.css',
    '../../../assets/css/searches.css',
    '../../../assets/css/default.css',
    '../../../assets/css/containers.css',
    '../../../assets/css/ribbon.css',
  ],
})
export class LostFoundComponent implements OnInit, OnChanges {
  ads: IAd[];
  providedAds: IAd[];
  dataReady:boolean = false;
  adCategories: string[];

  genders: string[];

  petCategories: IPetCategory[];

  cities: ICity[];
  defaultCity: ICity = {
    id: 0,
    name: 'All',
    latitude: '39.11035094005105',
    longitude: '23.263987890625017',
  };

  adCategory: string = 'All';
  gender: string = 'All';
  petCategory: string = 'All';
  city: string | null = this.defaultCity.name;

  @ViewChild('mapComponent', { static: false })
  private mapElement: MapComponent;

  constructor(
    private adsService: AdsService,
    private catService: CategoriesService,
    private tokenStorage:TokenStorageService
  ) {
    this.tokenStorage.autoLogin();
  }

  ngOnInit(): void {
    this.getAllCategories();
    this.getAllAds();
  }

  ngOnChanges(): void {
  }

  ngAfterViewInit() {
    this.filter();
  }

  getAllAds() {
    this.adsService.getAllAds().subscribe((actualAds: IAd[]) => {
      this.ads = actualAds;
      this.resetProvidedAds();
      this.adMarkers();
      this.dataReady = true;
    });
  }

  getAllCategories() {
    this.catService
      .getAllPetCategories()
      .subscribe((actualPetCat: IPetCategory[]) => {
        this.petCategories = actualPetCat;
      });

    this.catService.getAllCities().subscribe((actualCities: any[]) => {
      this.cities = actualCities.sort((a, b) => (a.name > b.name)?1:-1);
    });

    this.catService.getAllPetSexes().subscribe((actualPetSex: string[]) => {
      this.genders = actualPetSex;
    });

    this.catService.getAllAdCategories().subscribe((actualAdCat: string[]) => {
      this.adCategories = actualAdCat;
    });
  }

  resetProvidedAds() {
    this.providedAds =JSON.parse(JSON.stringify(this.ads));
  }

  filter() {
    this.resetProvidedAds();
    //Filter Ads by adCategory if value is NOT All
    if (this.adCategory !== 'All') {
      this.providedAds = this.providedAds.filter(
        (x) => x.adCategory.toUpperCase() === this.adCategory.toUpperCase()
      );
    }

    //Filter Ads by gender if value is NOT All
    if (this.gender !== 'All') {
      this.providedAds = this.providedAds.filter((x) => x.sex === this.gender);
    }

    //Filter ads by petCategory if value is NOT All
    if (this.petCategory !== 'All') {
      this.providedAds = this.providedAds.filter((x) =>
        x.petCategory.name
          .toUpperCase()
          .includes(this.petCategory.toUpperCase())
      );
    }

    //Filter Ads by city, and make the map focus in the right ciy
    if (this.city !== this.defaultCity.name) {
      this.providedAds = this.providedAds.filter(
        (x) => x.address.city.name === this.city
      );
      this.cities.forEach((x) =>
        x.name === this.city ? this.mapElement.moveMapToLocation(x, 10) : null
      );
    } else {
      this.mapElement.moveMapToLocation(
        { latitude: '39.11035094005105', longitude: '23.263987890625017' },
        6
      );
    }

    // Filter Ads by Date - Must dateOption be created if you want to use this filter
    // switch(this.dateOption){
    //   case "Newest Ads": this.providedAds.sort((a,b) => a.postDate.getFullYear() - b.postDate.getFullYear());break;
    //   case "Older Ads": this.providedAds.sort((a,b) => a.postDate.getFullYear() - b.postDate.getFullYear());break;
    // }
  }

  adMarkers(): void {
    this.ads.forEach((x) => {
      this.mapElement.addAdMarker(x);
    });
  }
}
