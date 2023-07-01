import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppGuard } from './app.guard';
import { AppService } from './app.service';
import { MessageService } from './message.service';
import { PaymentService } from './payment.service';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { BodyComponent } from './body/body.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './body/home/home.component';
import { OffersComponent } from './body/offers/offers.component';
import { ContactComponent } from './body/contact/contact.component';
import { ProfileComponent } from './body/profile/profile.component';
import { SigninComponent } from './body/signin/signin.component';
import { SignupComponent } from './body/signup/signup.component';
import { FlightsComponent } from './body/flights/flights.component';
import { BookingComponent } from './body/booking/booking.component';
import { PaymentComponent } from './body/payment/payment.component';
import { BookedComponent } from './body/booked/booked.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    BodyComponent,
    FooterComponent,
    HomeComponent,
    OffersComponent,
    ContactComponent,
    ProfileComponent,
    SigninComponent,
    SignupComponent,
    FlightsComponent,
    BookingComponent,
    PaymentComponent,
    BookedComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [AppGuard, AppService, MessageService, PaymentService],
  bootstrap: [AppComponent]
})
export class AppModule { }
