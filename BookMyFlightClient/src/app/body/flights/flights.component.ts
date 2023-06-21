import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from '../../app.service';
import { Flight } from '../../model/flight';

@Component({
  selector: 'app-flights',
  templateUrl: './flights.component.html',
  styleUrls: ['./flights.component.css']
})
export class FlightsComponent {

  preFlightData: any;

  flights: Flight[] = [];

  constructor(private router: Router, private service: AppService) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation && navigation.extras && navigation.extras.state) {
      this.preFlightData = navigation.extras.state;
    }
  }

  ngOnInit(): void {
    this.service.getAllFlights()
      .subscribe(flights => this.flights = flights);
  }

}
