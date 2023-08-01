import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';

import { url } from '../../../urls/url';

import { Flight } from '../admin-models/Flight';

import { AdminService } from '../service/admin.service';
import { Dropdown } from 'primeng/dropdown';

interface Status {
  name: string;
  code: string;
}
@Component({
  selector: 'app-edit-flights',
  templateUrl: './edit-flights.component.html',
  styleUrls: ['./edit-flights.component.css'],
  providers: [MessageService]
})
export class EditFlightsComponent implements OnInit {

  url: string = 'http://localhost:8100';
  gatewayUrl: string = url.apiGatewayUrl;

  form: FormGroup;

  status!: Status[];

  cities!: string[];
  filteredCities!: string[];
  isInvalidCity!: boolean;

  imageUrl!: string;
  uploadedFile!: File;
  message!: string;

  flights!: Flight[];

  updateFlight: boolean = false;

  constructor(private formBuilder: FormBuilder, private messageService: MessageService, private adminService: AdminService) {
    this.form = this.formBuilder.group({
      flightNumber: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9\s]+$')]],
      flightAirline: ['', [Validators.required]],
      flightStatus: [''],
      sourceCity: ['', [Validators.required, this.cityValidator.bind(this)]],
      destinationCity: ['', [Validators.required, this.cityValidator.bind(this)]],
      journeyDate: ['', [Validators.required]],
      departureTime: ['', [Validators.required]],
      arrivalTime: ['', [Validators.required]],
      standardPrice: ['', [Validators.required]],
      economyPrice: ['', [Validators.required]],
      businessPrice: ['', [Validators.required]]
    });
  }

  ngOnInit() {
    this.status = [
      { name: 'On-Time', code: 'OT' },
      { name: 'Delayed', code: 'DL' },
      { name: 'Cancelled', code: 'CN' }
    ];
    this.adminService.getCities().subscribe(response => this.cities = response, error =>
      console.log('Cities Retrieval Failed: ', error)
    );
    this.adminService.getFlights().then(data => data.subscribe(response => this.flights = response, error =>
      console.log('Flights Retrieval Failed: ', error)
    ));
  }

  filterCities(event: any) {
    const query = event.query.toLowerCase();
    this.filteredCities = this.cities.filter(
      (city) => city.toLowerCase().indexOf(query) !== -1
    );
  }

  cityValidator(control: FormControl): { [key: string]: any } | null {
    const city = control.value;
    if (city === '') {
      this.isInvalidCity = false;
      return null;
    }
    if (this.cities?.includes(city)) {
      this.isInvalidCity = false;
      return null;
    } else {
      this.isInvalidCity = true;
      return { invalidCity: true };
    }
  }

  onFileUpload(event: any): void {
    this.uploadedFile = new File([], '');
    if (event.files.length > 0) {
      const file = event.files[0];
      this.uploadFile(file);
      for (let file of event.files) {
        this.uploadedFile = file;
      }
    }
  }

  uploadFile(file: File): void {
    const formData = new FormData();
    formData.append('image', file);

    fetch('http://localhost:8765/search-flights/upload', {
      method: 'POST',
      body: formData
    })
      .then(response => response.json())
      .then(data => {
        if (data.success) {
          this.imageUrl = data.imageUrl;
          this.messageService.add({ severity: 'success', summary: 'Upload Successful', detail: 'File is uploaded!' });
        } else {
          this.messageService.add({ severity: 'error', summary: 'Upload Failed', detail: 'File could not be uploaded!' });
        }
      })
      .catch(error => {
        console.error('Error:', error);
        this.messageService.add({ severity: 'error', summary: 'Upload Failed', detail: 'An error occurred during file upload!' });
      });
  }

  onDragOver(event: DragEvent) {
    event.preventDefault();
    event.stopPropagation();
  }

  onDragLeave(event: DragEvent) {
    event.preventDefault();
    event.stopPropagation();
  }

  onDrop(event: DragEvent) {
    event.preventDefault();
    event.stopPropagation();
    const files = event.dataTransfer?.files;
    if (files && files.length > 0) {
      this.onFileUpload({ files });
    }
  }

  getSeverity(flight: Flight) {
    switch (flight.status) {
      case 'On-Time':
        return 'success';

      case 'Delayed':
        return 'warning';

      case 'Cancelled':
        return 'danger';

      default:
        return null;
    }
  };

  onSubmit() {
    if (this.form.valid) {
      if (this.updateFlight) {
        const departure = new Date(this.form.value.departureTime);
        const arrival = new Date(this.form.value.arrivalTime);

        const flight: Flight = {
          flightNumber: this.form.value.flightNumber,
          departureTime: departure.toLocaleTimeString('en-US', { hour: '2-digit', minute: '2-digit' }),
          arrivalTime: arrival.toLocaleTimeString('en-US', { hour: '2-digit', minute: '2-digit' }),
          airline: this.form.value.flightAirline,
          airlineImage: this.imageUrl,
          date: this.adminService.formatDate(this.form.value.journeyDate),
          source: this.form.value.sourceCity,
          destination: this.form.value.destinationCity,
          status: this.form.value.flightStatus.name || 'On-Time',
          prices: [
            this.form.value.standardPrice,
            this.form.value.economyPrice,
            this.form.value.businessPrice
          ]
        };

        this.adminService.updateFlight(this.form.value.flightNumber, flight).subscribe(
          response => {
            this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Flight Updated Successfully!' });

            this.uploadedFile = new File([], '');
            this.form.reset();

            this.isInvalidCity = false;

            this.updateFlight = false;

            this.adminService.getFlights().then(data => data.subscribe(response => this.flights = response, error =>
              console.log('Flights Retrieval Failed: ', error)
            ));
          },
          error => {
            this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Flight Updating Failed!' })
            console.log('Updating Failed: ', error);
          }
        );
      } else {
        const departure = new Date(this.form.value.departureTime);
        const arrival = new Date(this.form.value.arrivalTime);

        const flight: Flight = {
          flightNumber: this.form.value.flightNumber,
          departureTime: departure.toLocaleTimeString('en-US', { hour: '2-digit', minute: '2-digit' }),
          arrivalTime: arrival.toLocaleTimeString('en-US', { hour: '2-digit', minute: '2-digit' }),
          airline: this.form.value.flightAirline,
          airlineImage: this.imageUrl,
          date: this.adminService.formatDate(this.form.value.journeyDate),
          source: this.form.value.sourceCity,
          destination: this.form.value.destinationCity,
          status: this.form.value.flightStatus.name || 'On-Time',
          prices: [
            this.form.value.standardPrice,
            this.form.value.economyPrice,
            this.form.value.businessPrice
          ]
        };

        this.adminService.addFlight(flight).subscribe(
          response => {
            this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Flight Added Successfully!' });

            this.uploadedFile = new File([], '');
            this.form.reset();

            this.isInvalidCity = false;

            this.adminService.getFlights().then(data => data.subscribe(response => this.flights = response, error =>
              console.log('Flights Retrieval Failed: ', error)
            ));
          },
          error => {
            this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Flight Adding Failed!' })
            console.log('Adding Failed: ', error);
          }
        );
      }
    }
  }

  addOneDay(dateString: string): string {
    const [day, month, year] = dateString.split("-").map(Number);

    const date = new Date(year, month - 1, day);

    date.setDate(date.getDate() + 1);

    const nextDay = date.getDate();
    const nextMonth = date.getMonth() + 1;
    const nextYear = date.getFullYear();

    return `${String(nextDay).padStart(2, "0")}-${String(nextMonth).padStart(2, "0")}-${nextYear}`;
  }

  parseDateStringToDate(dateString: string): Date {
    const [day, month, year] = dateString.split("-").map(Number);
    return new Date(year, month - 1, day + 1);
  }

  convertTimeStringToDate(timeString: string): Date | null {
    const timeRegex = /^(\d{1,2}):(\d{2})\s(AM|PM)$/i;
    const match = timeString.match(timeRegex);
  
    if (match) {
      let hours = parseInt(match[1], 10);
      const minutes = parseInt(match[2], 10);
      const ampm = match[3].toUpperCase();
  
      if (hours === 12) {
        hours = ampm === 'AM' ? 0 : 12;
      } else {
        hours += ampm === 'PM' ? 12 : 0;
      }
  
      const date = new Date();
      date.setHours(hours, minutes, 0, 0);
      return date;
    }
  
    return null;
  }

  @ViewChild('flightStatusDropdown') flightStatusDropdown?: Dropdown;

  updateFill(flight: Flight) {
    this.form.patchValue({
      flightNumber: flight.flightNumber,
      flightAirline: flight.airline,
      sourceCity: flight.source,
      destinationCity: flight.destination,
      journeyDate: this.parseDateStringToDate(flight.date || ''),
      departureTime: this.convertTimeStringToDate(flight.departureTime || ''),
      arrivalTime: this.convertTimeStringToDate(flight.arrivalTime || ''),
      standardPrice: flight.prices?.at(0),
      economyPrice: flight.prices?.at(1),
      businessPrice: flight.prices?.at(2)
    });
    const selectedIndex = this.status.findIndex(option => option.name === flight.status);
    if (this.flightStatusDropdown && selectedIndex !== -1) {
      this.flightStatusDropdown.writeValue(this.status[selectedIndex]);
    }
    this.updateFlight = true;
  }

  deleteFlight(flightNumber: string) {
    this.adminService.deleteFlight(flightNumber).subscribe(
      response => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Flight Deleted Successfully!' });

        this.adminService.getFlights().then(data => data.subscribe(response => this.flights = response, error =>
          console.log('Flights Retrieval Failed: ', error)
        ));
      },
      error => {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Flight Deleting Failed!' })
        console.log('Deleting Failed: ', error);
      }
    );
  }

}
