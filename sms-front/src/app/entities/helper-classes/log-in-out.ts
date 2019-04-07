import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from '../../providers/services/auth.service';

@Injectable()
export class LogInOutService {
    public isAdmin: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
    public isUser: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
    public isGuest: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

    constructor(private auth: AuthService) {

    }

    checkUser() {
        if (this.auth.isAUserLoggedIn()) {
            this.auth.getUserLoggedIn().subscribe(data => {
                if (data.accountType == "ADMIN") {
                    this.isAdmin.next(true);
                } else if (data.accountType == "USER") {
                    this.isUser.next(true);
                }
            });

            this.isGuest.next(false);
        } else {
            this.isAdmin.next(false);
            this.isUser.next(false);
            this.isGuest.next(true);
        }

    }
}