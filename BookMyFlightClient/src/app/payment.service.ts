import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { AppService } from './app.service';

declare const Razorpay: any;

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private razorpayOptions: any;
  private booked: any;

  constructor(private service: AppService, private router: Router) {
    this.razorpayOptions = {
      key: environment.razorpayKey,
      amount: 0,
      name: 'BookMyFlight',
      description: 'Book Flight With Ease',
      image: 'assets/logo.png',
      handler: this.paymentHandler.bind(this),
      prefill: {
        name: service.getDetails().name,
        email: service.getDetails().email,
        contact: service.getDetails().mobile,
      },
      notes: {
        address: service.getDetails().address,
      },
      theme: {
        color: '#0a8cdc'
      }
    };
  }

  initiatePayment(amount: number, booked: any): void {
    this.razorpayOptions.amount = amount;
    this.booked = booked;
    const rzp = new Razorpay(this.razorpayOptions);
    rzp.open();
  }

  paymentHandler(response: any): void {
    if (response.razorpay_payment_id) {
        this.router.navigate(['/booked'], { state: this.booked });
    } else {
        this.router.navigate(['/error']);
    }
  }
}
