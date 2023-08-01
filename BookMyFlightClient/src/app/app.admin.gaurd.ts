import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AppService } from './app.service';

@Injectable()
export class AdminGuard implements CanActivate {
    constructor(private service: AppService, private router: Router) { }

    canActivate(): boolean {
        if (this.service.isAdmin()) {
            return true;
        } else {
            this.router.navigate(['/signin']);
            return false;
        }
    }
}