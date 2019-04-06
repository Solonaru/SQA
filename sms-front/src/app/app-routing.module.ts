import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HeaderComponent } from './components/front-office/header/header.component';
import { FooterComponent } from './components/front-office/footer/footer.component';

import { LoginComponent } from './components/front-office/login/login.component';
import { RegisterComponent } from './components/front-office/register/register.component';
import { ProfileComponent } from './components/front-office/profile/profile.component';
import { HomeComponent } from './components/front-office/home/home.component';
import { ShoppingComponent } from './components/front-office/shopping/shopping.component';
import { MakeOwnPizzaComponent } from './components/front-office/make-own-pizza/make-own-pizza.component';

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

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },

  { path: 'header', component: HeaderComponent },
  { path: 'footer', component: FooterComponent },

  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'home', component: HomeComponent },
  { path: 'shopping', component: ShoppingComponent },
  { path: 'make-own-pizza', component: MakeOwnPizzaComponent },

  { path: 'category-mng', component: CategoryMngComponent },
  { path: 'item-mng', component: ItemMngComponent },
  { path: 'subscription-mng', component: SubscriptionsMngComponent },
  { path: 'job-mng', component: JobMngComponent },
  { path: 'location-mng', component: LocationMngComponent },

  { path: 'careers', component: CareersComponent },
  { path: 'job', component: JobComponent },

  { path: 'promotions', component: PromotionsComponent },
  { path : 'about-us', component: AboutUsComponent},
  { path: 'terms-cond', component: TermsCondComponent },
  { path: 'privacy', component: PrivacyComponent },
  { path: 'feedback', component: FeedbackComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
