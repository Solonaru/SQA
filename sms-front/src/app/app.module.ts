import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SlideshowModule } from 'ng-simple-slideshow';
import { MatNativeDateModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DemoMaterialModule } from './material-module';
import { LogInOutService } from './entities/helper-classes/log-in-out';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/front-office/header/header.component';
import { FooterComponent } from './components/front-office/footer/footer.component';

import { LoginComponent } from './components/front-office/login/login.component';
import { RegisterComponent } from './components/front-office/register/register.component';
import { HomeComponent } from './components/front-office/home/home.component';
import { ProfileComponent } from './components/front-office/profile/profile.component';
import { ShoppingComponent } from './components/front-office/shopping/shopping.component';
import { MakeOwnPizzaComponent } from './components/front-office/make-own-pizza/make-own-pizza.component';

import { AdministrativeComponent } from './components/back-office/administrative/administrative.component';
import { CategoryMngComponent } from './components/back-office/category-mng/category-mng.component';
import { ItemMngComponent } from './components/back-office/item-mng/item-mng.component';
import { SubscriptionsMngComponent } from './components/back-office/subscriptions-mng/subscriptions-mng.component';
import { JobMngComponent } from './components/back-office/job-mng/job-mng.component';
import { LocationMngComponent } from './components/back-office/location-mng/location-mng.component';

import { CareersComponent } from './components/front-office/careers/careers.component';
import { JobComponent } from './components/front-office/careers/job/job.component';

import { PromotionsComponent } from './components/front-office/promotions/promotions.component';
import { AboutUsComponent } from './components/front-office/about-us/about-us.component';
import { TermsCondComponent } from './components/front-office/terms-cond/terms-cond.component';
import { PrivacyComponent } from './components/front-office/privacy/privacy.component';
import { FeedbackComponent } from './components/front-office/feedback/feedback.component';
import { ContactUsComponent } from './components/front-office/contact-us/contact-us.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,

    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    ShoppingComponent,
    MakeOwnPizzaComponent,

    CategoryMngComponent,
    ItemMngComponent,
    SubscriptionsMngComponent,
    JobMngComponent,
    LocationMngComponent,

    CareersComponent,
    JobComponent,

    PromotionsComponent,
    AboutUsComponent,
    TermsCondComponent,
    PrivacyComponent,
    FeedbackComponent,
    ContactUsComponent,
    AdministrativeComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    SlideshowModule,
    DemoMaterialModule,
    MatNativeDateModule,
    ReactiveFormsModule,
  ],
  providers: [LogInOutService],
  bootstrap: [AppComponent]
})
export class AppModule { }
