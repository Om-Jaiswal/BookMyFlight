import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent {

  booking: any = {flight: {}, source: {}, destination: {}};

  constructor(private router: Router) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation && navigation.extras && navigation.extras.state) {
      this.booking = navigation.extras.state;
    }
  }

}
