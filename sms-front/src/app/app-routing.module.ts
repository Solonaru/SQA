import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/front-office/login/login.component';
import { HeaderComponent } from './components/front-office/header/header.component';
import { FooterComponent } from './components/front-office/footer/footer.component';
import { CategoryMngComponent } from './components/back-office/category-mng/category-mng.component';
import { RegisterComponent } from './components/front-office/register/register.component';

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



const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'header', component: HeaderComponent },
  { path: 'footer', component: FooterComponent },
  { path: 'careers', component: CareersComponent,
  { path: 'waiter', component: WaiterComponent},
  {path:'manager', component: ManagerComponent},
  {path:'janitor', component: JanitorComponent},
  {path:'system-admin', component: SystemAdminComponent},
  {path:'cook', component: CookComponent},
  {path:'economist', component: EconomistComponent},
  {path:'front-end-dev', component: FrontEndDevComponent},
  {path:'back-end-dev', component: BackEndDevComponent},
  {path:'delivery-person', component: DeliveryPersonComponent},
  {path:'operator', component: OperatorComponent},
  {path:'customer-relations', component: CustomerRelationsComponent},

  { path: 'category-mng', component: CategoryMngComponent },
  { path: 'register', component: RegisterComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
