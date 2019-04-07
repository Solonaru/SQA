import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../../providers/services/category.service';
import { Category } from '../../../entities/classes/category';
import { Router } from '@angular/router';
import { AuthService } from '../../../providers/services/auth.service';
import { LogInOutService } from '../../../entities/helper-classes/log-in-out';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isAdmin: boolean;
  isUser: boolean;
  isGuest: boolean;
  categories: Category[];

  constructor(private auth: AuthService,
    private categoryService: CategoryService,
    private router: Router,
    private logInOutService: LogInOutService) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    
    this.logInOutService.isAdmin.subscribe(value => {
      this.isAdmin = value;
    });

    this.logInOutService.isUser.subscribe(value => {
      this.isUser = value;
    });

    this.logInOutService.isGuest.subscribe(value => {
      this.isGuest = value;
    });
  }

  ngOnInit() {
    this.populateCategories();
    this.logInOutService.checkUser();
  }

  populateCategories() {
    this.categoryService.getFrontOfficeCategories().subscribe(data => { this.categories = data });
  }

  onClick(category: Category) {
    /* Load the items page */
    this.router.navigate(['shopping/', { cat: category.id }]);
  }

  logout() {
    this.auth.setLoggedOut();
    this.logInOutService.checkUser();
    this.router.navigate(['login']);
  }

}
