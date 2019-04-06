import { Component, OnInit } from '@angular/core';
import { Category } from '../../../entities/classes/category';
import { Ingredient } from '../../../entities/classes/item/ingredient';
import { Recipe } from '../../../entities/classes/item/recipe';
import { CategoryService } from '../../../providers/services/category.service';
import { IngredientService } from '../../../providers/services/item/ingredient.service';
import { RecipeService } from '../../../providers/services/item/recipe.service';
import {CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-make-own-pizza',
  templateUrl: './make-own-pizza.component.html',
  styleUrls: ['./make-own-pizza.component.css']
})
export class MakeOwnPizzaComponent implements OnInit {

  category: Category;
  categories: Category[];
  ingredients: Ingredient[];
  doughs: Recipe[];
  sauces: Recipe[];

  constructor(private categoryService: CategoryService,
    private ingredientService: IngredientService,
    private recipeService: RecipeService) { }

  ngOnInit() {
    this.populateData();
  }

  populateData() {
    this.populateCategoriesAndIngredients();
    this.populateDoughs();
    this.populateSauces();
  }

  populateCategoriesAndIngredients() {
    this.categoryService.getCategoryByName('Ingredients').subscribe(data => {
      this.categories = data.childCategories;
      this.category = this.categories[0];
      this.ingredients = this.category.items;
    })
  }

  populateDoughs() {
    this.categoryService.getCategoryByName("Dough recipes").subscribe(data => {
      this.doughs = data.items;
    })
  }

  populateSauces() {
    this.categoryService.getCategoryByName("Tomato sauce recipes").subscribe(data => {
      this.sauces = data.items;
    })
  }

  drop(event: CdkDragDrop<{name: string, imageUrl: string}[]>) {
    moveItemInArray(this.ingredients, event.previousIndex, event.currentIndex);
  }
}
