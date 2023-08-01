import { Component } from '@angular/core';

import { Booked } from '../admin-models/booked';

import { AdminService } from '../service/admin.service';

@Component({
  selector: 'app-edit-bookings',
  templateUrl: './edit-bookings.component.html',
  styleUrls: ['./edit-bookings.component.css']
})
export class EditBookingsComponent {

  bookings!: Booked[];

  constructor(private adminService: AdminService) {}

  ngOnInit() {
    this.adminService.getBookings().then(data => data.subscribe(response => this.bookings = response, error =>
      console.log('Bookings Retrieval Failed: ', error)
    ));
  }
  
}
