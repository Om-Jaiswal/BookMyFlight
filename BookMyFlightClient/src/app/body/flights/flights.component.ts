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

  isDisabled: boolean = true;

  flight: BookedFlight = {flightNumber: '', airline: '', departureTime: '', arrivalTime: '', date: '', classAndPrice: '', passengerCount: NaN};
  stringSource: string = '';
  source: Airport = { iataCode: '', airportCity: '', airportName: ''};
  stringDestination: string = '';
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
      this.stringSource = navigation.extras.state['source'];
      this.source.iataCode = navigation.extras.state['source'].slice(0,3);
      this.source.airportCity = navigation.extras.state['source'].substring(6, navigation.extras.state['source'].indexOf('('));
      this.source.airportName = navigation.extras.state['source'].substring(navigation.extras.state['source'].indexOf('(') + 1, navigation.extras.state['source'].indexOf(')'));

      this.stringDestination = navigation.extras.state['destination'];
      this.destination.iataCode = navigation.extras.state['destination'].slice(0,3);
      this.destination.airportCity = navigation.extras.state['destination'].substring(6, navigation.extras.state['destination'].indexOf('('));
      this.destination.airportName = navigation.extras.state['destination'].substring(navigation.extras.state['destination'].indexOf('(') + 1, navigation.extras.state['destination'].indexOf(')'));

      this.flight.date = navigation.extras.state['date'].slice(8,10) + '-' + navigation.extras.state['date'].slice(5,7) + '-' + navigation.extras.state['date'].slice(0,4);
      this.flight.passengerCount = navigation.extras.state['passengerCount'];
    }
  }

  ngOnInit(): void {
    this.service.getAllFlights(this.stringSource, this.stringDestination, this.flight.date).subscribe(flights => this.flights = flights);
  }

  onSelectChange() {
    this.isDisabled = false;
  }

  bookTicket() {
    const airline = document.getElementById('airline');
    const departureTime = document.getElementById('departureTime');
    const arrivalTime = document.getElementById('arrivalTime');
    const flightNumber = document.getElementById('flightNumber');
    const classAndPrice = document.getElementsByName('classAndPrice') as NodeListOf<HTMLInputElement>;;
    if (airline && departureTime && arrivalTime && flightNumber) {
      this.flight.airline = airline.innerHTML;
      this.flight.departureTime = departureTime.innerHTML;
      this.flight.arrivalTime = arrivalTime.innerHTML;
      this.flight.flightNumber = flightNumber.innerHTML;
      classAndPrice.forEach((option: HTMLInputElement) => {
        if (option.checked) {
          this.flight.classAndPrice = option.value;
        }
      });
      this.router.navigate(['/booking'], { state: this.booking });
    }
  }

}
