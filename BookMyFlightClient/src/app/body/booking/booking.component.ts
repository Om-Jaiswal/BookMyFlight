import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent {

  booking: any = {flight: {}, source: {}, destination: {}};
  selectedSeats: string[] = [];
  maxSeats: number = 0;

  constructor(private router: Router) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation && navigation.extras && navigation.extras.state) {
      this.booking = navigation.extras.state;
      this.maxSeats = navigation.extras.state['flight'].passengerCount;
    }
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

}
