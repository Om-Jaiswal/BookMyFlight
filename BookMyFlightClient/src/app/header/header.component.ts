import 'bootstrap/dist/js/bootstrap.min.js';

import { Component } from '@angular/core';
import { AppService } from '../app.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  admin: boolean = false;

  constructor(private service: AppService) {}

  isSignedIn(): boolean {
    if(this.service.isAdmin()) {
      this.admin = true;
      return true;
    }
    return this.service.isAuthenticated();
  }

  signout() {
    this.admin = false;
    this.service.signout();
  }
}
