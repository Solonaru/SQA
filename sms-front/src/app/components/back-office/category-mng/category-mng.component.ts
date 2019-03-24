import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../../providers/services/category.service';
import { Category } from '../../../entities/category';

@Component({
  selector: 'app-category-mng',
  templateUrl: './category-mng.component.html',
  styleUrls: ['./category-mng.component.css']
})
export class CategoryMngComponent implements OnInit {

  category: Category;
  categories: Category[];
  icon: String = "fas fa-plus-circle";

  constructor(private categoryService: CategoryService) { }

  ngOnInit() {
    this.populateCategories();
  }

  populateCategories() {
    this.categoryService.getNoChildCategories().subscribe(data => { this.categories = data; });
  }

  onAdd() {
    this.category = new Category();
  }

  onUpdate(category: Category) {
    this.category = category;
  }

  onDelete(category: Category) {
    this.category = category;
  }

  onConfirmDelete() {
    this.categoryService.deleteCategory(this.category).subscribe(data => { location.reload(); });
  }

  onSubmit() {
    if (this.category.id != undefined) {
      this.updateCategory();
    } else {
      this.insertCategory();
    }
  }

  insertCategory() {
    this.categoryService.insertCategory(this.category).subscribe(date => { location.reload(); });
  }

  updateCategory() {
    this.categoryService.updateCategory(this.category).subscribe();
  }

  switch() {
    if (this.icon === "fas fa-minus-circle") {
      this.icon = "fas fa-plus-circle"
    } else {
      this.icon = "fas fa-minus-circle"
    }
  }

}
