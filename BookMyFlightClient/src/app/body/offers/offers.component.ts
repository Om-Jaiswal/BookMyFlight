import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-offers',
  templateUrl: './offers.component.html',
  styleUrls: ['./offers.component.css']
})
export class OffersComponent implements OnInit {

  offers: any;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getOffers();
  }

  getOffers() {
    this.http.get<any>('http://localhost:8765/flight-management-system/get-offers')
      .subscribe(
        response => {
          this.offers = response;
        },
        error => {
          console.log(error);
        }
    );
  }
}
