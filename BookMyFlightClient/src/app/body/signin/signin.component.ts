import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from '../../app.service';


@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent {
  user: { username: string, password: string } = { username: '', password: '' };

  constructor(private service: AppService, private router: Router) { }

  signin() {
    this.service.signin(this.user.username, this.user.password);
  }
}
