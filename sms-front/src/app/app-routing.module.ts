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
import { SessionGuard } from './providers/guards/session.guard';
import { FeedbackMngComponent } from './components/back-office/feedback-mng/feedback-mng.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },

  {
    path: 'header', component: HeaderComponent,
    canActivate: [SessionGuard], data: { allowed: ['ADMIN'] }
  },
  {
    path: 'footer', component: FooterComponent,
    canActivate: [SessionGuard], data: { allowed: ['ADMIN'] }
  },


  {
    path: 'login', component: LoginComponent,
    canActivate: [SessionGuard], data: { allowed: ['GUEST', 'ADMIN'] }
  },
  {
    path: 'register', component: RegisterComponent,
    canActivate: [SessionGuard], data: { allowed: ['GUEST', 'ADMIN'] }
  },
  {
    path: 'profile', component: ProfileComponent,
    canActivate: [SessionGuard], data: { allowed: ['USER', 'ADMIN'] }
  },
  { path: 'home', component: HomeComponent },
  { path: 'shopping', component: ShoppingComponent },
  {
    path: 'make-own-pizza', component: MakeOwnPizzaComponent,
    canActivate: [SessionGuard], data: { allowed: ['USER', 'ADMIN'] }
  },

  {
    path: 'administrative', component: AdministrativeComponent,
    canActivate: [SessionGuard], data: { allowed: ['ADMIN'] }
  },
  {
    path: 'category-mng', component: CategoryMngComponent,
    canActivate: [SessionGuard], data: { allowed: ['ADMIN'] }
  },
  {
    path: 'item-mng', component: ItemMngComponent,
    canActivate: [SessionGuard], data: { allowed: ['ADMIN'] }
  },
  {
    path: 'subscription-mng', component: SubscriptionsMngComponent,
    canActivate: [SessionGuard], data: { allowed: ['ADMIN'] }
  },
  {
    path: 'job-mng', component: JobMngComponent,
    canActivate: [SessionGuard], data: { allowed: ['ADMIN'] }
  },
  {
    path: 'location-mng', component: LocationMngComponent,
    canActivate: [SessionGuard], data: { allowed: ['ADMIN'] }
  },
  {
    path: 'feedback-mng', component: FeedbackMngComponent,
    canActivate: [SessionGuard], data: { allowed: ['ADMIN'] }
  },


  { path: 'careers', component: CareersComponent },
  { path: 'job', component: JobComponent },


  { path: 'promotions', component: PromotionsComponent },
  { path: 'about-us', component: AboutUsComponent },
  { path: 'contact-us', component: ContactUsComponent },
  { path: 'terms-cond', component: TermsCondComponent },
  { path: 'privacy', component: PrivacyComponent },
  { path: 'feedback', component: FeedbackComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
  
}
