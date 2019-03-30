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

import { CareersComponent } from './components/front-office/careers/careers.component';
import { WaiterComponent } from './components/front-office/careers/waiter/waiter.component';
import { ManagerComponent } from './components/front-office/careers/manager/manager.component';
import { JanitorComponent } from './components/front-office/careers/janitor/janitor.component';
import { SystemAdminComponent } from './components/front-office/careers/system-admin/system-admin.component';
import { CookComponent } from './components/front-office/careers/cook/cook.component';
import { EconomistComponent } from './components/front-office/careers/economist/economist.component';
import { FrontEndDevComponent } from './components/front-office/careers/front-end-dev/front-end-dev.component';
import { BackEndDevComponent } from './components/front-office/careers/back-end-dev/back-end-dev.component';
import { CustomerRelationsComponent } from './components/front-office/careers/customer-relations/customer-relations.component';
import { DeliveryPersonComponent } from './components/front-office/careers/delivery-person/delivery-person.component';
import { OperatorComponent } from './components/front-office/careers/operator/operator.component';
import { HomeComponent } from './components/front-office/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,

    LoginComponent,
    RegisterComponent,

    CategoryMngComponent,

    CareersComponent,
    WaiterComponent,
    ManagerComponent,
    JanitorComponent,
    SystemAdminComponent,
    CookComponent,
    EconomistComponent,
    FrontEndDevComponent,
    BackEndDevComponent,
    CustomerRelationsComponent,
    DeliveryPersonComponent,
    OperatorComponent,
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
