import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppGuard } from './app.guard';
import { AppService } from './app.service';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { BodyComponent } from './body/body.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './body/home/home.component';
import { SigninComponent } from './body/signin/signin.component';
import { SignupComponent } from './body/signup/signup.component';
import { FlightsComponent } from './body/flights/flights.component';
import { BookingComponent } from './body/booking/booking.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    BodyComponent,
    FooterComponent,
    HomeComponent,
    SigninComponent,
    SignupComponent,
    FlightsComponent,
    BookingComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [AppGuard, AppService],
  bootstrap: [AppComponent]
})
export class AppModule { }
