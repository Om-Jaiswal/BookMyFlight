import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';
import { FlightCapacity } from '../../model/flight-capacity';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {

  booking: any;
  maxSeats: number = 0;

  flightCapacity: any;
  flightClass: string = '';
  selectedSeats: string[] = [];
  bookedSeats: string[] = [];

  constructor(private router: Router, private http: HttpClient) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation && navigation.extras && navigation.extras.state) {
      this.booking = navigation.extras.state;
      this.maxSeats = navigation.extras.state['flight'].passengerCount;
    }
  }

  ngOnInit(): void {
    this.flightClass = this.booking.flight.classAndPrice.slice(0, this.booking.flight.classAndPrice.indexOf(' '));
    this.getAvailableSeats();
  }

  onSeatChange(value: string) {
    const index = this.selectedSeats.indexOf(value);
    if (index === -1) {
      if (this.selectedSeats.length < this.maxSeats) {
        this.selectedSeats.push(value);
      }
    } else {
      this.selectedSeats.splice(index, 1);
    }
  }

  getAvailableSeats() {
    const params = new HttpParams().set('flightNumber', this.booking.flight.flightNumber);
    this.http.get<FlightCapacity>('http://localhost:8300/book-flights/get-flight-capacity', { params })
      .subscribe(
        response => {
          this.flightCapacity = response;
          this.getAlreadyBookedSeats();
        },
        error => {
          console.log(error);
        }
    );
  }

  getAlreadyBookedSeats() {
    const capacity = this.flightCapacity[this.flightClass.toLowerCase()];
    for (let i = 0; i < capacity.length; i++) {
      for (let j = 0; j < capacity[i].length; j++) {
        const value = capacity[i][j];
        if(value === true) {
          let seat = '';
          if(i === 0) {
            seat += 'A' + (j+1);
          } else if(i === 1) {
            seat += 'B' + (j+1);
          } else if(i === 2) {
            seat += 'C' + (j+1);
          } else {
            seat += 'D' + (j+1);
          }
          this.bookedSeats.push(seat);
        }
      }
    }
  }

  confirmSeats() {
    const confirmBooking = {booking: this.booking, seats: this.selectedSeats };
    this.router.navigate(['/payment'], { state: confirmBooking });
  }

}
