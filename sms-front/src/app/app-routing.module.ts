import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

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



const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },

  { path: 'header', component: HeaderComponent },
  { path: 'footer', component: FooterComponent },

  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  { path: 'category-mng', component: CategoryMngComponent },

  { path: 'careers', component: CareersComponent },
  { path: 'waiter', component: WaiterComponent },
  { path: 'manager', component: ManagerComponent },
  { path: 'janitor', component: JanitorComponent },
  { path: 'system-admin', component: SystemAdminComponent },
  { path: 'cook', component: CookComponent },
  { path: 'economist', component: EconomistComponent },
  { path: 'front-end-dev', component: FrontEndDevComponent },
  { path: 'back-end-dev', component: BackEndDevComponent },
  { path: 'delivery-person', component: DeliveryPersonComponent },
  { path: 'operator', component: OperatorComponent },
  { path: 'customer-relations', component: CustomerRelationsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
