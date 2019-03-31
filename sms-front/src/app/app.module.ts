import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { SlideshowModule } from 'ng-simple-slideshow';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/front-office/header/header.component';
import { FooterComponent } from './components/front-office/footer/footer.component';

import { LoginComponent } from './components/front-office/login/login.component';
import { RegisterComponent } from './components/front-office/register/register.component';

import { CategoryMngComponent } from './components/back-office/category-mng/category-mng.component';
import { ItemMngComponent } from './components/back-office/item-mng/item-mng.component';
import { SubscriptionsMngComponent } from './components/back-office/subscriptions-mng/subscriptions-mng.component';

import { CareersComponent } from './components/front-office/careers/careers.component';
import { HomeComponent } from './components/front-office/home/home.component';
import { JobComponent } from './components/front-office/careers/job/job.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,

    LoginComponent,
    RegisterComponent,

    CategoryMngComponent,
    ItemMngComponent,
    SubscriptionsMngComponent,

    CareersComponent,
    JobComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    SlideshowModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
