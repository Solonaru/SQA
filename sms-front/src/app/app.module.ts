import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/front-office/login/login.component';
import { HeaderComponent } from './components/front-office/header/header.component';
import { FooterComponent } from './components/front-office/footer/footer.component';
import { CategoryMngComponent } from './components/back-office/category-mng/category-mng.component';
import { RegisterComponent } from './components/front-office/register/register.component';

import { FormsModule } from '@angular/forms';
import { CareersComponent } from './components/front-office/footer/careers/careers.component';
import { WaiterComponent } from './components/front-office/footer/careers/waiter/waiter.component';
import { ManagerComponent } from './components/front-office/footer/careers/manager/manager.component';
import { JanitorComponent } from './components/front-office/footer/careers/janitor/janitor.component';
import { SystemAdminComponent } from './components/front-office/footer/careers/system-admin/system-admin.component';
import { CookComponent } from './components/front-office/footer/careers/cook/cook.component';
import { EconomistComponent } from './components/front-office/footer/careers/economist/economist.component';
import { FrontEndDevComponent } from './components/front-office/footer/careers/front-end-dev/front-end-dev.component';
import { BackEndDevComponent } from './components/front-office/footer/careers/back-end-dev/back-end-dev.component';
import { CustomerRelationsComponent } from './components/front-office/footer/careers/customer-relations/customer-relations.component';
import { DeliveryPersonComponent } from './components/front-office/footer/careers/delivery-person/delivery-person.component';
import { OperatorComponent } from './components/front-office/footer/careers/operator/operator.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    CareersComponent,
    CategoryMngComponent,
    RegisterComponent,
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
    OperatorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
