import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashAdsComponent } from './dashboard/dash-ads/dash-ads.component';
import { DashCategoriesComponent } from './dashboard/dash-categories/dash-categories.component';
import { DashPagesComponent } from './dashboard/dash-pages/dash-pages.component';
import { DashPetsComponent } from './dashboard/dash-pets/dash-pets.component';
import { DashSubscriptionComponent } from './dashboard/dash-subscription/dash-subscription.component';
import { DashUsersComponent } from './dashboard/dash-users/dash-users.component';
import { AboutUsComponent } from './front/about-us/about-us.component';
import { AdProfileComponent } from './front/ad-profile/ad-profile.component';
import { Error404Component } from './front/error404/error404.component';
import { FindServiceComponent } from './front/find-service/find-service.component';
import { HomePageComponent } from './front/home-page/home-page.component';
import { LostFoundComponent } from './front/lost-found/lost-found.component';
import { PetProfileComponent } from './front/pet-profile/pet-profile.component';
import { RegisterComponent } from './front/register/register.component';
import { SuccessPageComponent } from './front/success-page/success-page.component';
import { TermsPolicyComponent } from './front/terms-policy/terms-policy.component';
import { DoctorDashClientsComponent } from './dashboard/doctor-dash-clients/doctor-dash-clients.component';
import { ProfilePropertiesComponent } from './front/profile-properties/profile-properties.component';
import { ShoppingCartComponent } from './front/profile-properties/shopping-cart/shopping-cart.component';
import { BusinessProfileComponent } from './front/business-profile/business-profile.component';
import { CompanyPageComponent } from './front/profile-properties/company-page/company-page.component';
import { ChatComponent } from './dashboard/chat/chat.component';
import { ForgottenPassComponent } from './front/forgotten-pass/forgotten-pass.component';
import { BankPaymentComponent } from './front/bank-payment/bank-payment.component';
import { SuccessfulPaymentComponent } from './front/successful-payment/successful-payment.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomePageComponent },
  { path: 'about-us', component:  AboutUsComponent},
  { path: 'find-service', component: FindServiceComponent},
  { path: 'lost-found', component: LostFoundComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'login', component: RegisterComponent},
  { path: 'terms-policy', component: TermsPolicyComponent},
  { path: 'ad_profile/:id', component: AdProfileComponent},
  { path: 'pet_profile/:id', component: PetProfileComponent},
  { path: 'success_page', component: SuccessPageComponent},
  { path: 'profile_properties', component: ProfilePropertiesComponent,},
  { path: 'profile_properties/shopping-cart/:id', component: ShoppingCartComponent},
  { path: 'profile-properties/business_page', component:CompanyPageComponent},
  { path: 'business_profile/:id', component: BusinessProfileComponent},
  { path: 'forgotten_password', component: ForgottenPassComponent },  
  { path: 'successful_transaction', component: BankPaymentComponent}, 
  { path: 'successful_payment', component: SuccessfulPaymentComponent}, 


  // =====Dashboard========
  { path: 'dashboard/users', component: DashUsersComponent},
  { path: 'dashboard/pets', component: DashPetsComponent},
  { path: 'dashboard/businesses', component: DashPagesComponent},
  { path: 'dashboard/ads', component: DashAdsComponent},
  { path: 'dashboard/categories', component: DashCategoriesComponent},
  { path: 'dashboard/subscription', component: DashSubscriptionComponent},
  { path: 'dashboard/doctor/clients', component: DoctorDashClientsComponent},
  { path: 'dashboard/chat', component: ChatComponent},
  

  // Error Component must stay at the bottom 
  {path:"error", component:Error404Component},
  {path:"**", component:Error404Component}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }