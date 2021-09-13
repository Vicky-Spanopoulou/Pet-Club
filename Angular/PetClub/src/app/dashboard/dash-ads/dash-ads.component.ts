import { nullSafeIsEquivalent } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { toDoc } from 'ngx-editor';
import { IAd, IUser } from 'src/app/_models/models';
import { AdsService } from 'src/app/_services/ads/ads.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-dash-ads',
  templateUrl: './dash-ads.component.html',
  styleUrls: [
    './dash-ads.component.css',
    '../dash-pages/dash-pages.component.css',
    '../../../assets/css/dashboardCss/Defaultdashboard.css',
  ],
})
export class DashAdsComponent implements OnInit {
  editAdsForm: boolean = false;
  editUser: IUser;
  editAd: IAd;
  ads: IAd[];
  searchAd: string;
  filterAds: IAd[];
  providedAdCategories: any[];
  loggedInUser:IUser;

  adForm: any = {
    id: null,
    petName: null,
    chipNumber: null,
    sex: null,
    breed: null,
    adCategory: null,
    someWords: null,
    descriptions: null,
    postDate: null,
    image: null,
    user: null,
    petCategory: null,
    street: null,
    latitude: null,
    longitude: null,
    postalCode: null,
    city: null,
    lostDate: null,
  };

  constructor(
    private adsService: AdsService,
    private userService: UserService,
    private tokenSTorageService: TokenStorageService,
    private router: Router
  ) {
    this.tokenSTorageService.autoLogin();
    this.loggedInUser = tokenSTorageService.getUser();
    if(this.loggedInUser.role !== 'ROLE_ADMIN'){
      this.router.navigate(['home']);
    }
  }

  ngOnInit(): void {
    this.getAllAds();
  }

  visibleAdsForm(ad: any) {
    this.editAdsForm = true;
    this.adForm.id = ad.id;
    this.adForm.petName = ad.petName;
    this.adForm.breed = ad.breed;
    this.adForm.adCategory = ad.adCategory;
    this.adForm.lostDate = ad.lostDate;
    this.adForm.postDate = ad.postDate;
    this.adForm.chipNumber = ad.chipNumber;
    this.adForm.descriptions = ad.descriptions;
    this.adForm.descriptionJson = toDoc(ad.descriptions);
    this.adForm.someWords = ad.someWords;
    this.adForm.someWordsJson = toDoc(ad.someWords);
    this.adForm.sex = ad.sex;
    this.adForm.image = ad.image;
    this.adForm.street = ad.address.street;
    this.adForm.city = ad.address.city.name;
    this.adForm.petCategory = ad.petCategory.name;
    this.adForm.latitude = ad.address.latitude;
    this.adForm.longitude = ad.address.longitude;
    this.adForm.postalCode = ad.address.postalCode;
  }

  getAllAds() {
    this.adsService.getAllAds().subscribe((actualAds: IAd[]) => {
      this.ads = actualAds;
      this.filterAds = actualAds;
      this.resetAdCategories();
    });
  }

  onCloseForm() {
    this.editAdsForm = false;
  }

  addNewAd() {
    this.editAdsForm = true;
    this.adForm.id = null;
    this.adForm.petName = null;
    this.adForm.adCategory = null;
    this.adForm.lostDate = null;
    this.adForm.postDate = null;
    this.adForm.chipNumber = null;
    this.adForm.someWords = '';
    this.adForm.postalCode = null;
    this.adForm.description = '';
    this.adForm.sex = null;
    this.adForm.image = null;
    this.adForm.address = null;
    this.adForm.city = null;
    this.adForm.latitude = null;
    this.adForm.longitude = null;
    this.adForm.petCategory = null;
    this.adForm.user = null;
  }

  resetAdCategories() {
    this.providedAdCategories = JSON.parse(JSON.stringify(this.ads));
  }

  searchAds() {
    this.resetAdCategories();
  
    if (this.searchAd) {
      this.providedAdCategories = this.providedAdCategories.filter(
        (x) =>  x.petName.toUpperCase().includes(this.searchAd.toUpperCase())
      );
    }
  }

  filterAdCategory(category: string): void {
    this.resetAdCategories();
    if (category !== 'All') {
      this.providedAdCategories = this.providedAdCategories.filter(
        (x) => x.adCategory.toUpperCase() === category.toUpperCase()
      );
    }

    if (category === 'All') {
      this.providedAdCategories;
    }
  }

  updateTable() {
    this.getAllAds();
    this.editAdsForm = false;
  }

}
