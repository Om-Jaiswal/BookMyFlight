import { Component, OnInit } from '@angular/core';
import { AppService } from '../../app.service';
import { Booked } from '../../model/booked';

@Component({
  selector: 'app-booked',
  templateUrl: './booked.component.html',
  styleUrls: ['./booked.component.css']
})
export class BookedComponent implements OnInit {

  bookings: Booked[] = [];

  constructor(private service: AppService) {}

  ngOnInit(): void {
    this.service.getAllBookings().subscribe((data: Booked[]) => {
      this.bookings = data;
    });
  }

}
