import { Injectable } from '@angular/core';
import { AuthService } from '../../providers/services/auth.service';

@Injectable()
export class Globals {
  isAdmin: boolean = false;

  constructor(private auth: AuthService) {

  }

  checkUser() {
    if (this.auth.isAUserLoggedIn()) {
      this.auth.getUserLoggedIn().subscribe(data => {
        console.log(data.accountType);
        if(data.accountType == "ADMIN") {
          console.log("isAdmin");
          this.isAdmin = true;
        }
      });
    } else {
      this.isAdmin = false;
    }
  }
}