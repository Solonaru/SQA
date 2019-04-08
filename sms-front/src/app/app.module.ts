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
import { CartComponent } from './components/front-office/cart/cart.component';
import { FooterComponent } from './components/front-office/footer/footer.component';

import { LoginComponent } from './components/front-office/login/login.component';
import { RegisterComponent } from './components/front-office/register/register.component';
import { HomeComponent } from './components/front-office/home/home.component';
import { ProfileComponent } from './components/front-office/profile/profile.component';
import { ShoppingComponent } from './components/front-office/shopping/shopping.component';
import { MakeOwnPizzaComponent } from './components/front-office/make-own-pizza/make-own-pizza.component';

import { AdministrativeComponent } from './components/back-office/administrative/administrative.component';
import { ReportsComponent } from './components/back-office/reports/reports.component';
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
import { FeedbackMngComponent } from './components/back-office/feedback-mng/feedback-mng.component';

import { ProductChartMonthComponent } from './components/stats/product-chart-month/product-chart-month.component';
import { ProductPriceSalesChartComponent } from './components/stats/product-price-sales-chart/product-price-sales-chart.component';
import { ProductForecastMonthComponent } from './components/stats/product-forecast-month/product-forecast-month.component';
import { CategoryChartMonthComponent } from './components/stats/category-chart-month/category-chart-month.component';
import { CategoryForecastMonthComponent } from './components/stats/category-forecast-month/category-forecast-month.component';
import { ProductsShareInCategoryComponent } from './components/stats/products-share-in-category/products-share-in-category.component';
import { TopProductsByQuantityChartComponent } from './components/stats/top-products-by-quantity-chart/top-products-by-quantity-chart.component';
import { TopProductsByPriceChartComponent } from './components/stats/top-products-by-price-chart/top-products-by-price-chart.component';

import { ChartsModule } from 'ng2-charts';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    CartComponent,
    FooterComponent,

    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    ShoppingComponent,
    MakeOwnPizzaComponent,

    AdministrativeComponent,
    ReportsComponent,
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
    FeedbackMngComponent,

    ProductChartMonthComponent,
    FooterComponent,
    ProductPriceSalesChartComponent,
    ProductForecastMonthComponent,
    CategoryChartMonthComponent,
    CategoryForecastMonthComponent,
    ProductsShareInCategoryComponent,
    TopProductsByQuantityChartComponent,
    TopProductsByPriceChartComponent
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
    ChartsModule
  ],
  providers: [LogInOutService],
  bootstrap: [AppComponent]
})
export class AppModule { }
