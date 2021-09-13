import { Component, OnChanges, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { toDoc } from 'ngx-editor';
import { IBusiness, IUser } from 'src/app/_models/models';
import { BusinessService } from 'src/app/_services/business/business.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-dash-pages',
  templateUrl: './dash-pages.component.html',
  styleUrls: [
    './dash-pages.component.css',
    '../../../assets/css/dashboardCss/Defaultdashboard.css',
  ],
})
export class DashPagesComponent implements OnInit, OnChanges {
  searchBusiness: string;
  editPageForm: boolean = false;
  businessOwner: any;
  editBusiness: any;
  allBusiness: IBusiness[];
  filterBusiness: any[];
  providedBusinesses: IBusiness[];
  loggedInUser:IUser;

  businessForm: any = {
    id: null,
    name: null,
    email: null,
    phone: null,
    description: null,
    text: null,
    imgLogo: null,
    imgBackground: null,
    facebook: null,
    instagram: null,
    pageUrl: null,
    businessCategory: null,
    street: null,
    city: null,
    postalCode: null,
    latitude: null,
    longitude: null,
    averageRating: null,
    user: null,
    status: null,
  };
  
  constructor(
    private businessService: BusinessService,
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

  ngOnInit() {
    this.getAllBusiness();
  }

  ngOnChanges() {
    this.getUserByBusiness();
  }

  editBusinessForm(business: any) {
    this.editPageForm = true;
    this.businessForm.id = business.id;
    this.businessForm.name = business.name;
    this.businessForm.email = business.email;
    this.businessForm.imgLogo = business.imgLogo;
    this.businessForm.imgBackground = business.imgBackground;
    this.businessForm.businessCategory = business.businessCategory.name;
    this.businessForm.description = business.description;
    this.businessForm.text = business.text;
    this.businessForm.phone = business.phone;
    this.businessForm.facebook = business.facebook;
    this.businessForm.instagram = business.instagram;
    this.businessForm.pageUrl = business.pageUrl;
    this.businessForm.street = business.address.street;
    this.businessForm.city = business.address.city.name;
    this.businessForm.postalCode = business.address.postalCode;
    this.businessForm.latitude = business.address.latitude;
    this.businessForm.longitude = business.address.longitude;
    this.businessForm.imgLogoURL = business.imgLogo;
    this.businessForm.imgBackgroundURL = business.imgBackground;
    this.businessForm.descriptionJson = toDoc(business.description);
    this.businessForm.textJson = toDoc(business.text);
    this.businessForm.status = business.status;
    this.getUserByBusiness();
  }

  getAllBusiness() {
    this.businessService
      .getAllBusinesses()
      .subscribe((actualBusiness: IBusiness[]) => {
        this.allBusiness = actualBusiness;
        this.filterBusiness = actualBusiness;
        this.resetBusinesses();
      });
  }

  onCloseForm() {
    this.editPageForm = false;
  }

  getUserByBusiness() {
    if (this.editBusiness != null) {
      this.userService
        .getUserByBusinessId(this.editBusiness.id)
        .subscribe((acualUser: IUser) => {
          this.businessOwner = acualUser;
        });
    }
  }

  resetBusinesses() {
    this.providedBusinesses = JSON.parse(JSON.stringify(this.allBusiness));
  }

  searchBusinessByName() {
    this.resetBusinesses();

    if (this.searchBusiness) {
      this.providedBusinesses = this.providedBusinesses.filter((x) =>
        x.businessCategory.name
          ? x.businessCategory.name
              .toUpperCase()
              .includes(this.searchBusiness.toUpperCase())
          : null
      );
    }
  }

  filterStatus(type: string): void {
    this.resetBusinesses();

    if (type !== 'All') {
      this.providedBusinesses = this.providedBusinesses.filter(
        (x) => x.status.toUpperCase() === type.toUpperCase()
      );
    }
    if (type === 'All') {
      this.providedBusinesses;
    }
  }

  updateTable() {
    this.getAllBusiness();
    this.editPageForm = false;
  }
}
