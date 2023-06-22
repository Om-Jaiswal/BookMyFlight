import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from '../../app.service';
import { Flight } from '../../model/flight';
import { Airport } from '../../model/airport';
import { BookedFlight } from '../../model/booked-flight';
import { Booking } from '../../model/booking';

@Component({
  selector: 'app-flights',
  templateUrl: './flights.component.html',
  styleUrls: ['./flights.component.css']
})
export class FlightsComponent implements OnInit {

  flight: BookedFlight = {flightNumber: '', airline: '', departureTime: '', arrivalTime: '', date: '', passengerCount: NaN};
  source: Airport = { iataCode: '', airportCity: '', airportName: ''};
  destination: Airport = { iataCode: '', airportCity: '', airportName: ''};

  flights: Flight[] = [];

  booking: Booking = {
    flight: this.flight,
    source: this.source,
    destination: this.destination,
  };

  constructor(private router: Router, private service: AppService) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation && navigation.extras && navigation.extras.state) {
      this.source.iataCode = navigation.extras.state['source'].slice(0,3);
      this.source.airportCity = navigation.extras.state['source'].substring(6, navigation.extras.state['source'].indexOf('('));
      this.source.airportName = navigation.extras.state['source'].substring(navigation.extras.state['source'].indexOf('(') + 1, navigation.extras.state['source'].indexOf(')'));

      this.destination.iataCode = navigation.extras.state['destination'].slice(0,3);
      this.destination.airportCity = navigation.extras.state['destination'].substring(6, navigation.extras.state['destination'].indexOf('('));
      this.destination.airportName = navigation.extras.state['destination'].substring(navigation.extras.state['destination'].indexOf('(') + 1, navigation.extras.state['destination'].indexOf(')'));

      this.flight.date = navigation.extras.state['date'];
      this.flight.passengerCount = navigation.extras.state['passenger'];
    }
  }

  ngOnInit(): void {
    this.service.getAllFlights().subscribe(flights => this.flights = flights);
  }

  bookTicket() {
    const airline = document.getElementById('airline');
    const departureTime = document.getElementById('departureTime');
    const arrivalTime = document.getElementById('arrivalTime');
    const flightNumber = document.getElementById('flightNumber');
    if (airline && departureTime && arrivalTime && flightNumber) {
      this.flight.airline = airline.innerHTML;
      this.flight.departureTime = departureTime.innerHTML;
      this.flight.arrivalTime = arrivalTime.innerHTML;
      this.flight.flightNumber = flightNumber.innerHTML;
      this.router.navigate(['/booking'], { state: this.booking });
    }
  }

}
