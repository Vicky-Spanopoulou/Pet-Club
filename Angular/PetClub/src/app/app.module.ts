import { CommonModule } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FrontNavbarComponent } from './front/front-navbar/front-navbar.component';
import { RegisterComponent } from './front/register/register.component';
import { FooterComponent } from './front/footer/footer.component';
import { HomePageComponent } from './front/home-page/home-page.component';
import { FindServiceComponent } from './front/find-service/find-service.component';
import { LostFoundComponent } from './front/lost-found/lost-found.component';
import { AboutUsComponent } from './front/about-us/about-us.component';
import { HorizontalNavComponent } from './dashboard/horizontal-nav/horizontal-nav.component';
import { VerticalNavComponent } from './dashboard/vertical-nav/vertical-nav.component';
import { DashUsersComponent } from './dashboard/dash-users/dash-users.component';
import { DashPagesComponent } from './dashboard/dash-pages/dash-pages.component';
import { DashUserFormComponent } from './dashboard/dash-users/dash-user-form/dash-user-form.component';
import { DashPagesFormComponent } from './dashboard/dash-pages/dash-pages-form/dash-pages-form.component';
import { DashPetsComponent } from './dashboard/dash-pets/dash-pets.component';
import { DashPetFormComponent } from './dashboard/dash-pets/dash-pet-form/dash-pet-form.component';
import { LandingPageComponent } from './front/landing-page/landing-page.component';
import { UserProfileComponent } from './front/user-profile/user-profile.component';
import {
  AuthInterceptor,
  authInterceptorProviders,
} from './_helpers/auth.interceptor';
import { SliderComponent } from './front/slider/slider.component';
import { TermsPolicyComponent } from './front/terms-policy/terms-policy.component';
import { Error404Component } from './front/error404/error404.component';
import { DashSubscriptionComponent } from './dashboard/dash-subscription/dash-subscription.component';
import { DashInvoiceComponent } from './dashboard/dash-subscription/dash-invoice/dash-invoice.component';
import { DashAdsComponent } from './dashboard/dash-ads/dash-ads.component';
import { DashAdsFormComponent } from './dashboard/dash-ads/dash-ads-form/dash-ads-form.component';
import { DashCategoriesComponent } from './dashboard/dash-categories/dash-categories.component';
import { MyPetsComponent } from './front/user-profile/my-pets/my-pets.component';
import { MyAdsComponent } from './front/user-profile/my-ads/my-ads.component';
import { MyRemindersComponent } from './front/user-profile/my-reminders/my-reminders.component';
import { PetFormComponent } from './front/forms/pet-form/pet-form.component';
import { AdFormComponent } from './front/forms/ad-form/ad-form.component';
import { ReminderFormComponent } from './front/forms/reminder-form/reminder-form.component';
import { MapComponent } from './map/map.component';
import { BusinessProfileComponent } from './front/business-profile/business-profile.component';
import { AdProfileComponent } from './front/ad-profile/ad-profile.component';
import { PetProfileComponent } from './front/pet-profile/pet-profile.component';
import { SuccessPageComponent } from './front/success-page/success-page.component';
import { NgxEditorModule } from 'ngx-editor';
import { DoctorDashClientsComponent } from './dashboard/doctor-dash-clients/doctor-dash-clients.component';
import { DashPetHistoryComponent } from './dashboard/doctor-dash-clients/dash-pet-history/dash-pet-history.component';
import { ProfilePropertiesComponent } from './front/profile-properties/profile-properties.component';
import { UserPropertiesComponent } from './front/profile-properties/user-properties/user-properties.component';
import { SecurityPropertiesComponent } from './front/profile-properties/security-properties/security-properties.component';
import { UserSubscriptionComponent } from './front/profile-properties/user-subscription/user-subscription.component';
import { AllSubscriptionsComponent } from './front/profile-properties/all-subscriptions/all-subscriptions.component';
import { CompanyPageComponent } from './front/profile-properties/company-page/company-page.component';
import { ShoppingCartComponent } from './front/profile-properties/shopping-cart/shopping-cart.component';
import { PetExamsComponent } from './pet-exams/pet-exams.component';
import { NgxAwesomePopupModule, ToastNotificationConfigModule } from '@costlydeveloper/ngx-awesome-popup';
import { ChatComponent } from './dashboard/chat/chat.component';
import { ReplaceUnderscorePipe } from './shared-pipes/replace-underscore.pipe';
import { DatePipe } from '@angular/common';
import { ForgottenPassComponent } from './front/forgotten-pass/forgotten-pass.component';
import { NgxPayPalModule } from 'ngx-paypal';
import { PaypalComponent } from './paypal/paypal.component';
import { BankPaymentComponent } from './front/bank-payment/bank-payment.component';
import { ReplacerolePipe } from './shared-pipes/replacerole.pipe';
import { SuccessfulPaymentComponent } from './front/successful-payment/successful-payment.component';

@NgModule({
  declarations: [
    AppComponent,
    FrontNavbarComponent,
    RegisterComponent,
    FooterComponent,
    HomePageComponent,
    FindServiceComponent,
    LostFoundComponent,
    AboutUsComponent,
    HorizontalNavComponent,
    VerticalNavComponent,
    DashUsersComponent,
    LandingPageComponent,
    UserProfileComponent,
    DashUsersComponent,
    DashPagesComponent,
    DashUserFormComponent,
    DashPagesFormComponent,
    DashPetsComponent,
    DashPetFormComponent,
    SliderComponent,
    TermsPolicyComponent,
    Error404Component,
    DashSubscriptionComponent,
    DashInvoiceComponent,
    DashAdsComponent,
    DashAdsFormComponent,
    DashCategoriesComponent,
    MyPetsComponent,
    MyAdsComponent,
    MyRemindersComponent,
    PetFormComponent,
    AdFormComponent,
    ReminderFormComponent,
    MapComponent,
    BusinessProfileComponent,
    AdProfileComponent,
    PetProfileComponent,
    SuccessPageComponent,
    DoctorDashClientsComponent,
    DashPetHistoryComponent,
    ProfilePropertiesComponent,
    UserPropertiesComponent,
    SecurityPropertiesComponent,
    UserSubscriptionComponent,
    AllSubscriptionsComponent,
    CompanyPageComponent,
    ShoppingCartComponent,
    PetExamsComponent,
    ChatComponent,
    ReplaceUnderscorePipe,
    ForgottenPassComponent,
    PaypalComponent,
    BankPaymentComponent,
    ReplacerolePipe,
    SuccessfulPaymentComponent,
  ],

  imports: [
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgxEditorModule,
    NgxAwesomePopupModule.forRoot(),
    ToastNotificationConfigModule.forRoot(),
    NgxPayPalModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    DatePipe,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
