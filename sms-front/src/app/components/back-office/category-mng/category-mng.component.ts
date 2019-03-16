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

  constructor(private categoryService: CategoryService) { }

  ngOnInit() {
    this.populateCategories();
  }

  populateCategories() {
    this.categoryService.getCategories().subscribe(data => { this.categories = data; });
  }

  onAdd() {
    this.category = new Category();
  }

  onUpdate(category: Category) {
    this.category = category;
  }

  onDelete(category: Category) {
    this.categoryService.deleteCategory(category).subscribe(data => {location.reload(); });
  }

  onSubmit() {
    if (this.category.id != undefined) {
      this.updateCategory();
    } else {
      this.insertCategory();
    }
  }

  insertCategory() {

  }

  updateCategory() {
    
  }

}
