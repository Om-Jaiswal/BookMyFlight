import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AppService } from './app.service';

@Injectable()
export class AppGuard implements CanActivate {
    constructor(private service: AppService, private router: Router) { }

    canActivate(): boolean {
        if (this.service.isAuthenticated()) {
            return true;
        } else {
            this.router.navigate(['/signin']);
            return false;
        }
    }
}