import { Component, OnInit } from '@angular/core';
import { User } from '../../../entities/classes/user';
import { AuthService } from '../../../providers/services/auth.service';
import { Router } from '@angular/router';
import { LogInOutService } from '../../../entities/helper-classes/log-in-out';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User;
  foundUser: User;

  constructor(private auth: AuthService,
    private logInOutService: LogInOutService,
    private router: Router) { }

  ngOnInit() {
  }

  loginUser(event : any) {
    this.user = new User();

    event.preventDefault();
    const target = event.target;
    this.user.username = target.querySelector('#username').value;
    this.user.password = target.querySelector('#password').value;

    this.auth.login(this.user).subscribe(data => { this.foundUser = data; this.loginCheck(); });
  }

  loginCheck() {
    if (this.foundUser != null) {
      this.auth.setLoggedIn(this.foundUser.id);
      this.logInOutService.checkUser();
      this.router.navigate(['home']);
    } else {
      window.alert("No user found!!");
    }
  }

}
