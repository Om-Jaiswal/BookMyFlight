import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Flight } from '../admin-models/Flight';
import { url } from 'src/urls/url';
import { Booked } from '../admin-models/booked';

@Injectable()
export class AdminService {

    constructor(private http: HttpClient) {}

    formatDate(inputDateStr: string): string {
        const months = [
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
          ];

          const inputDate = new Date(inputDateStr);

          const day = inputDate.getDate();
          const month = inputDate.getMonth() + 1;
          const year = inputDate.getFullYear();

          const formattedDay = day.toString().padStart(2, '0');
          const formattedMonth = month.toString().padStart(2, '0');
        
          const formattedDate = `${formattedDay}-${formattedMonth}-${year}`;
        
          return formattedDate;
    }

    getCities() {
        return this.http.get<string[]>(url.apiGatewayUrl + 'search-flights/cities-code');
    }

    addFlight(flight: Flight) {
        const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        return this.http.post<string>(url.apiGatewayUrl + 'search-flights/add-flight', flight, { headers: headers })
    }

    getFlightsData() {
        return this.http.get<Flight[]>(url.apiGatewayUrl + 'search-flights/all-flights');
    }

    getFlights() {
        return Promise.resolve(this.getFlightsData());
    }

    getBookingsData() {
        return this.http.get<Booked[]>(url.apiGatewayUrl + 'book-flights/get-all-bookings');
    }

    getBookings() {
        return Promise.resolve(this.getBookingsData());
    }

    updateFlight(flightNumber: string, flight: Flight) {
        const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        const params = new HttpParams().set('flightNumber', flightNumber);
        return this.http.put<string>(url.apiGatewayUrl + 'search-flights/update-flight', flight, { headers: headers, params: params })
    }

    deleteFlight(flightNumber: string) {
        const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        const params = new HttpParams().set('flightNumber', flightNumber);
        return this.http.delete<string>(url.apiGatewayUrl + 'search-flights/delete-flight', { headers: headers, params: params })
    }

};