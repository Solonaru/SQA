import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HeaderComponent } from './components/front-office/header/header.component';
import { FooterComponent } from './components/front-office/footer/footer.component';

import { LoginComponent } from './components/front-office/login/login.component';
import { RegisterComponent } from './components/front-office/register/register.component';
import { HomeComponent } from './components/front-office/home/home.component';

import { CategoryMngComponent } from './components/back-office/category-mng/category-mng.component';

import { CareersComponent } from './components/front-office/careers/careers.component';
import { JobComponent } from './components/front-office/careers/job/job.component';
import { ProductMngComponent } from './components/back-office/product-mng/product-mng.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },

  { path: 'header', component: HeaderComponent },
  { path: 'footer', component: FooterComponent },

  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },

  { path: 'category-mng', component: CategoryMngComponent },
  { path: 'product-mng', component: ProductMngComponent },

  { path: 'careers', component: CareersComponent },
  { path: 'job', component: JobComponent }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
