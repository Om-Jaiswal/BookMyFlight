import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './body/home/home.component';
import { OffersComponent } from './body/offers/offers.component';
import { ContactComponent } from './body/contact/contact.component';
import { SigninComponent } from './body/signin/signin.component';
import { SignupComponent } from './body/signup/signup.component';
import { FlightsComponent } from './body/flights/flights.component';
import { BookingComponent } from './body/booking/booking.component';
import { AppGuard } from './app.guard';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent},
  { path: 'offers', component: OffersComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'flights', component: FlightsComponent },
  { path: 'signin', component: SigninComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'booking', component: BookingComponent, canActivate: [AppGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
