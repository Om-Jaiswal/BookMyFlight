import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  cities: string[] = ["Test-1", "Test-2"];

  source: string = '';
  destination: string = '';

  exchangeValues() {
    const temp = this.source;
    this.source = this.destination;
    this.destination = temp;
  }
}
