import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { PaymentService } from '../../payment.service';
import { AppService } from '../../app.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent {

  booking: any;
  confirmSeats: string[] = [];
  flightClass: string = '';
  price: number = 0;

  passengers: any[] = [];

  constructor(private router: Router, private http: HttpClient, private paymentService: PaymentService, private service: AppService) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation && navigation.extras && navigation.extras.state) {
      this.booking = navigation.extras.state['booking'];
      this.confirmSeats = navigation.extras.state['seats'];
      this.flightClass = navigation.extras.state['booking'].flight.classAndPrice.slice(0, navigation.extras.state['booking'].flight.classAndPrice.indexOf(' '));
      this.price = navigation.extras.state['booking'].flight.classAndPrice.slice(navigation.extras.state['booking'].flight.classAndPrice.indexOf('.') + 2,);
    }
    this.addPassenger();
  }

  addPassenger() {
    for (let i = 0; i < this.confirmSeats.length; i++) {
      this.passengers.push({ name: '', age: null, gender: '' });
    }
  }

  isFormValid(): boolean {
    for (const passenger of this.passengers) {
      if (!passenger.name || !passenger.age || !passenger.gender) {
        return false;
      }
    }
    return true;
  }

  initiatePayment(): void {
    const amountToPay = ((this.price * this.confirmSeats.length) + (this.price * this.confirmSeats.length * 0.05)) * 100;
    const booked = {
      flightNumber: this.booking['flight'].flightNumber,
      airline: this.booking['flight'].airline,
      departureTime: this.booking['flight'].departureTime,
      arrivalTime: this.booking['flight'].arrivalTime,
      date: this.booking['flight'].date,
      source: this.booking['source'],
      destination: this.booking['destination'],
      passengerCount: this.booking['flight'].passengerCount,
      passengerDetails: this.passengers,
      seats: this.confirmSeats,
      flightClass: this.flightClass,
      amountPaid: amountToPay,
      paidBy: this.service.getUsername()
    };
    this.paymentService.initiatePayment(amountToPay, booked);
  }

}
