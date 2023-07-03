import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { AppService } from './app.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

declare const Razorpay: any;

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private razorpayOptions: any;
  private booked: any;

  constructor(private service: AppService, private router: Router, private http: HttpClient) {
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
      const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
      this.http.post<string>('http://localhost:8765/book-flights/add-booking', this.booked, { headers: headers })
      .subscribe(
        (response: string) => {
          console.log(response);
          this.router.navigate(['/booked']);
        },
        (error) => {
          console.log('Booking Failed:', error);
        }
      );
    } else {
      this.router.navigate(['/error']);
    }
  }
}
