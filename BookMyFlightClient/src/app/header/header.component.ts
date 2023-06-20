import 'bootstrap/dist/js/bootstrap.min.js';

import { Component } from '@angular/core';
import { AppService } from '../app.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(private service: AppService) { }

  isSignedIn(): boolean {
    return this.service.isAuthenticated();
  }

  signout() {
    this.service.signout();
  }
}
