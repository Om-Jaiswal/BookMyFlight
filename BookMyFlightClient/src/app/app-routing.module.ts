import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './body/home/home.component';
import { OffersComponent } from './body/offers/offers.component';
import { ContactComponent } from './body/contact/contact.component';
import { SigninComponent } from './body/signin/signin.component';
import { SignupComponent } from './body/signup/signup.component';
import { ProfileComponent } from './body/profile/profile.component';
import { FlightsComponent } from './body/flights/flights.component';
import { BookingComponent } from './body/booking/booking.component';
import { PaymentComponent } from './body/payment/payment.component';
import { BookedComponent } from './body/booked/booked.component';
import { AppGuard } from './app.guard';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent},
  { path: 'offers', component: OffersComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'flights', component: FlightsComponent },
  { path: 'signin', component: SigninComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'profile', component: ProfileComponent, canActivate: [AppGuard] },
  { path: 'booking', component: BookingComponent, canActivate: [AppGuard] },
  { path: 'payment', component: PaymentComponent, canActivate: [AppGuard] },
  { path: 'booked', component: BookedComponent, canActivate: [AppGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
