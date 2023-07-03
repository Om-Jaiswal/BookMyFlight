import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent {

  constructor(private http: HttpClient) {}
  dataSent: boolean = false;

  onSubmit(form: NgForm) {
    if(form.valid) {
      const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
      const data = { name: form.value.name, email: form.value.email, message: form.value.message };

      this.http.post<string>('http://localhost:8765/flight-management-system/add-contact', data, { headers: headers })
      .subscribe(
        (response: string) => {
          console.log(response);
          form.reset();
          this.dataSent = true;
        },
        (error) => {
          console.log('Failed:', error);
        }
      );
    }
  }

}
