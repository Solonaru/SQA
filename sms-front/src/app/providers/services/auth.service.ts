import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { map } from 'rxjs/operators';

import { User } from '../../entities/classes/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private BASE_URL: string = "http://localhost:8090/account/";

  constructor(private http: HttpClient) { }

  setLoggedIn(userId: Number) {
    console.log(userId);
    localStorage.setItem('userLoggedIn', JSON.stringify(userId));
  }

  setLoggedOut() {
    localStorage.removeItem('userLoggedIn');
  }

  /* Should always check if a user is logged in before calling this method (isAUserLoggedIn())*/
  getUserLoggedIn() : Observable<User> {
    return this.http.get(this.BASE_URL + localStorage.getItem('userLoggedIn')).pipe(map((res: User) => { return res }));
  }

  isAUserLoggedIn(): Boolean {
    return null !== localStorage.getItem('userLoggedIn');
  }

  login(user: User) {
    return this.http.post(this.BASE_URL + 'login', user)
      .pipe(map((res: any) => { return res }));
  }
}