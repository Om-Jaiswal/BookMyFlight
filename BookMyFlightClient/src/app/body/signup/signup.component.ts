import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AppService } from '../../app.service';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  same: boolean = false;

  constructor(private service: AppService) { }

  signup(form: NgForm) {
    if (form.valid) {
      const username = form.value.username;
      const password = form.value.password;
      const rePassword = form.value.rePassword;

      if (password === rePassword) {
        this.service.signup(username, password);
      } else {
        this.same = true;
      }
    } 
  }

}
