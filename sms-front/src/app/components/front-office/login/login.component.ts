import { Component, OnInit } from '@angular/core';
import { User } from '../../../entities/classes/user';
import { AuthService } from '../../../providers/services/auth.service';
import { Router } from '../../../../../node_modules/@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User;
  foundUser: User;

  constructor(private auth: AuthService, private router: Router) { }

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
      this.router.navigate(['home']);
      this.auth.setLoggedIn(this.foundUser.id);
    } else {
      window.alert("No user found!!");
    }
  }

}
