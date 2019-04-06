import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../../providers/services/category.service';
import { Category } from '../../../entities/classes/category';
import { Router } from '@angular/router';
import { AuthService } from '../../../providers/services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  categories: Category[];

  constructor(private auth: AuthService, private categoryService: CategoryService, private router: Router) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit() {
    this.populateCategories();
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
    this.router.navigate(['login']);
  }

}
