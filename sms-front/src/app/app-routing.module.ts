import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HeaderComponent } from './components/front-office/header/header.component';
import { FooterComponent } from './components/front-office/footer/footer.component';

import { LoginComponent } from './components/front-office/login/login.component';
import { RegisterComponent } from './components/front-office/register/register.component';
import { ProfileComponent } from './components/front-office/profile/profile.component';
import { HomeComponent } from './components/front-office/home/home.component';

import { CategoryMngComponent } from './components/back-office/category-mng/category-mng.component';
import { ItemMngComponent } from './components/back-office/item-mng/item-mng.component';
import { SubscriptionsMngComponent } from './components/back-office/subscriptions-mng/subscriptions-mng.component';
import { JobMngComponent } from './components/back-office/job-mng/job-mng.component';

import { CareersComponent } from './components/front-office/careers/careers.component';
import { JobComponent } from './components/front-office/careers/job/job.component';

import { PromotionsComponent } from './components/front-office/promotions/promotions.component';
import { AboutUsComponent } from './components/front-office/about-us/about-us.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },

  { path: 'header', component: HeaderComponent },
  { path: 'footer', component: FooterComponent },

  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'home', component: HomeComponent },

  { path: 'category-mng', component: CategoryMngComponent },
  { path: 'item-mng', component: ItemMngComponent },
  { path: 'subscription-mng', component: SubscriptionsMngComponent },
  { path: 'job-mng', component: JobMngComponent },

  { path: 'careers', component: CareersComponent },
  { path: 'job', component: JobComponent },

  { path: 'promotions', component: PromotionsComponent },
  { path : 'about-us', component: AboutUsComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
