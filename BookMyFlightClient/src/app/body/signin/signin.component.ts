import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AppService } from '../../app.service';


@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent {

  fail: boolean = false;
  message: string = '';

  constructor(private service: AppService) { }

  signin(form: NgForm) {
    if (form.valid) {
      const username = form.value.username;
      const password = form.value.password;

      this.service.signin(username, password);

      if(!this.service.isAuthenticated()) {
        this.fail = true;
      }
    }
  }
}
