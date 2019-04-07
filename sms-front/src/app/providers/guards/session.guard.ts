import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { map } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class SessionGuard implements CanActivate {

    constructor(private auth: AuthService, private router: Router) {

    }

    canActivate(
        next: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean> {
        if (this.auth.isAUserLoggedIn()) {
            return this.auth.getUserLoggedIn().pipe(
                map(response => {
                    let val = false;
                    if (next.data['allowed'].indexOf(response.accountType) !== -1) {
                        val = true;
                    } else {
                        this.router.navigate(['home']);
                    }

                    return val;
                }));
        } else {
            let val = false;

            if (next.data['allowed'].indexOf("GUEST") !== -1) {
                val = true;
            } else {
                this.router.navigate(['home']);
            }

            return of(val);
        }
    }
}