import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, catchError, map } from 'rxjs';
import { MessageService } from './message.service';
import { Flight } from './model/flight';
import { SigninResponse } from './model/siginin-response';
import { Details } from './model/details';

@Injectable()
export class AppService {

  private authenticated = false;

  private token: string = '';
  private username: string = '';
  private details: any;

  constructor(private http: HttpClient, private router: Router, private messageService: MessageService) { }

  isAuthenticated(): boolean {
    return this.authenticated;
  }

  signin(username: string, password: string): void {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const credentials = { username: username, password: password };

    this.http.post<SigninResponse>('http://localhost:8200/user-profile/signin', credentials, { headers: headers })
      .subscribe(
        (response: SigninResponse) => {
          this.token = response.jwtToken;
          this.username = response.username;
          this.details = response.details;
          this.authenticated = true;
          sessionStorage.setItem('token', this.token);
          this.router.navigate(['/home']);
        },
        (error) => {
          console.log('Signin Failed:', error);
        }
      );
  }

  signout(): void {
    const token = sessionStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    this.http.post('http://localhost:8200/user-profile/signout', null, { headers })
      .subscribe(
        () => {
          this.token = '';
          this.authenticated = false;
          sessionStorage.removeItem('token');
          this.messageService.setMessage('Signout Successfully!');
          this.router.navigate(['/signin']);
        },
        (error) => {
          console.log('Signout Failed:', error);
        }
      );
  }

  signup(name: string, username: string, email: string, mobile: string, password: string): Observable<string> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const newUser = { username: username, password: password, details: {name: name, email: email, mobile: mobile, address: ''} };
    return this.http.post<string>('http://localhost:8200/user-profile/signup', newUser, { headers })
      .pipe(
        map(() => {
          this.messageService.setMessage('Signup Successfully!');
          this.router.navigate(['/signin']);
          return 'Signup Successfully!';
        }),
        catchError((error) => {
          console.log('Sigup Failed:', error);
          throw error;
        })
      );
  }

  update(details: Details): Observable<string> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const params = new HttpParams().set('username', this.username);
    return this.http.post<string>('http://localhost:8200/user-profile/update', details, { headers: headers, params: params })
      .pipe(
        catchError((error: any) => {
          console.error('Update Failed:', error);
          throw error;
        })
      );
  }

  getAllFlights(source: string, destination: string, date: string): Observable<Flight[]> {
    const params = new HttpParams()
    .set('source', source)
    .set('destination', destination)
    .set('date', date);

    return this.http.get<Flight[]>('http://localhost:8100/search-flights/flights', { params });
  }

  getDetails(): any {
    return this.details;
  }

  setDetails(details: Details): void {
    this.details = details;
  }

}