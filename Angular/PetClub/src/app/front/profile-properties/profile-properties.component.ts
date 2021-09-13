import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IBusiness, IUser } from 'src/app/_models/models';
import { BusinessService } from 'src/app/_services/business/business.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-profile-properties',
  templateUrl: './profile-properties.component.html',
  styleUrls: [
    './profile-properties.component.css',
    '../../../assets/css/containers.css',
    '../../../assets/css/default.css',
  ],
})
export class ProfilePropertiesComponent implements OnInit {
  user: IUser;
  showCompanyPage: boolean = false;
  showSubscription: boolean = false;
  showYourSubscription: boolean = false;

  userProperties: boolean = true;
  securityProperties: boolean = false;
  userSubscription: boolean = false;
  allSubscriptions: boolean = false;
  companyPage: boolean = false;

  business: IBusiness;
  constructor(
    private tokenStorageService: TokenStorageService,
    private router: Router,
    private businessService: BusinessService,
    private userService: UserService
  ) {
    this.tokenStorageService.autoLogin();
    if (!this.tokenStorageService.getToken()) {
      this.router.navigate(['/home']);
    }
    this.user = this.tokenStorageService.getUser();
    this.checkSubscriptions();
    this.getBusiness();
  }

  ngOnInit(): void {
    this.getUser();
  }

  getBusiness() {
    this.businessService
      .getBusinessesByUserId(this.user.id)
      .subscribe((business: IBusiness) => {
        this.business = business;
        this.checkCompanyMenu();
      });
  }

  checkCompanyMenu(): void {
    if (this.business.status !== 'UNPAID') {
      this.showCompanyPage = true;
    }
  }

  checkSubscriptions(): void {
    if (
      this.user.sub_until === null ||
      formatDate(this.user.sub_until, 'yyyy-MM-dd', 'en_US') <
        formatDate(new Date(), 'yyyy-MM-dd', 'en_US')
    ) {
      this.showSubscription = true;
    } else if (
      formatDate(this.user.sub_until, 'yyyy-MM-dd', 'en_US') >=
      formatDate(new Date(), 'yyyy-MM-dd', 'en_US')
    ) {
      this.showYourSubscription = true;
    }
  }

  onUserProperties() {
    this.userProperties = true;
    this.securityProperties = false;
    this.userSubscription = false;
    this.allSubscriptions = false;
    this.companyPage = false;
  }
  onSecurityProperties() {
    this.userProperties = false;
    this.securityProperties = true;
    this.userSubscription = false;
    this.allSubscriptions = false;
    this.companyPage = false;
  }
  onUserSubscription() {
    this.userProperties = false;
    this.securityProperties = false;
    this.userSubscription = true;
    this.allSubscriptions = false;
    this.companyPage = false;
  }
  onAllSubscriptions() {
    this.userProperties = false;
    this.securityProperties = false;
    this.userSubscription = false;
    this.allSubscriptions = true;
    this.companyPage = false;
  }
  onCompanyPage() {
    this.userProperties = false;
    this.securityProperties = false;
    this.userSubscription = false;
    this.allSubscriptions = false;
    this.companyPage = true;
  }

  getUser(): void {
    this.userService
      .getUserByUsername(this.user.username)
      .subscribe((event: any) => {
        this.user = event;
        this.tokenStorageService.saveUser(event);
      });
  }
}
