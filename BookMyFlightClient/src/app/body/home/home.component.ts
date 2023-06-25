import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  cities: string[] = [];
  fromList: boolean = false;

  constructor(private router: Router, private http: HttpClient) {}

  ngOnInit(): void {
    this.getAllCodes();
  }

  exchangeValues() {
    const source = document.getElementById('source') as HTMLInputElement;
    const destination = document.getElementById('destination') as HTMLInputElement;

    const temp = source.value;
    source.value = destination.value;
    destination.value = temp;
  }

  onSubmit(form: NgForm) {
    if (form.valid) {
      if (this.cities.includes(form.value.source) && this.cities.includes(form.value.destination)) {
        const source = form.value.source;
        const destination = form.value.destination;
        const date = form.value.date;
        const passengerCount = form.value.passengerCount;
        this.router.navigate(['/flights'], { state: { source: source, destination: destination, date: date, passengerCount: passengerCount} });
      } else {
        this.fromList = true;
      }
    }
  }

  getAllCodes() {
    this.http.get<string[]>('http://localhost:8000/cities-code')
      .subscribe(
        response => {
          this.cities = response;
        },
        error => {
          console.log(error);
        }
    );
  }
}
