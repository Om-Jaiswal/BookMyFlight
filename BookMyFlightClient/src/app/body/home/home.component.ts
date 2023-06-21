import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  cities: string[] = [];

  data: { source: string, destination: string, date: string, passenger: number} = { source: '', destination: '', date: '', passenger: NaN};

  constructor(private router: Router, private http: HttpClient) {}

  ngOnInit(): void {
    this.getAllCodes();
  }

  exchangeValues() {
    const temp = this.data.source;
    this.data.source = this.data.destination;
    this.data.destination = temp;
  }

  onSubmit() {
    this.router.navigate(['/flights'], { state: this.data });
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
