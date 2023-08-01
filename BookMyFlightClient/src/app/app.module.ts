import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { TabViewModule } from 'primeng/tabview';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { InputNumberModule } from 'primeng/inputnumber';
import { CalendarModule } from 'primeng/calendar';
import { FileUploadModule } from 'primeng/fileupload';
import { ToastModule } from 'primeng/toast';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { DataViewModule } from 'primeng/dataview';
import { TagModule } from 'primeng/tag';

import { AppGuard } from './app.guard';
import { AppService } from './app.service';
import { MessageService } from './message.service';
import { PaymentService } from './payment.service';
import { AdminGuard } from './app.admin.gaurd';
import { AdminService } from './admin/service/admin.service';

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
import { AdminComponent } from './admin/admin.component';
import { EditFlightsComponent } from './admin/edit-flights/edit-flights.component';
import { EditOffersComponent } from './admin/edit-offers/edit-offers.component';
import { EditBookingsComponent } from './admin/edit-bookings/edit-bookings.component';

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
    BookedComponent,
    AdminComponent,
    EditFlightsComponent,
    EditOffersComponent,
    EditBookingsComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    TabViewModule,
    InputTextModule,
    DropdownModule,
    InputNumberModule,
    CalendarModule,
    FileUploadModule,
    ToastModule,
    AutoCompleteModule,
    DataViewModule,
    TagModule
  ],
  providers: [AppGuard, AppService, MessageService, PaymentService, AdminGuard, AdminService],
  bootstrap: [AppComponent]
})
export class AppModule { }
