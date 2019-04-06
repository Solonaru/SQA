import { Component, OnInit } from '@angular/core';
import { Category } from '../../../entities/classes/category';
import { Ingredient } from '../../../entities/classes/item/ingredient';
import { Recipe } from '../../../entities/classes/item/recipe';
import { CategoryService } from '../../../providers/services/category.service';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { IngredientService } from '../../../providers/services/item/ingredient.service';

@Component({
  selector: 'app-make-own-pizza',
  templateUrl: './make-own-pizza.component.html',
  styleUrls: ['./make-own-pizza.component.css']
})
export class MakeOwnPizzaComponent implements OnInit {

  category: Category;
  categories: Category[];
  ingredients: Ingredient[];
  pizzaIngredients: Ingredient[] = [];

  dough: Recipe;
  doughs: Recipe[];

  sauce: Recipe;
  sauces: Recipe[];

  constructor(private categoryService: CategoryService, private ingredientService: IngredientService) { }

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
      this.ingredientService.getIngredientsByCategoryId(this.category.id).subscribe(data => {
        this.ingredients = data;
      });
    });
  }

  populateDoughs() {
    this.categoryService.getCategoryByName("Dough recipes").subscribe(data => {
      this.doughs = data.items;
      this.dough = this.doughs[0];
    })
  }

  populateSauces() {
    this.categoryService.getCategoryByName("Tomato sauce recipes").subscribe(data => {
      this.sauces = data.items;
      this.sauce = this.sauces[0];
    })
  }

  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex);

      if (event.container.id == "cdk-drop-list-1") {

        this.refreshIngredientsData()

      }

      if (event.container.id == "cdk-drop-list-0") {

        this.ingredients.map(function (x) {
          x.disabled = false;
          return x
        });

        this.refreshIngredientsData();

      }

    }
  }

  refreshIngredientsData() {
    for (let ingredient of this.pizzaIngredients) {
      for (let conflictIngredient of ingredient.conflictIngredients) {
        let foundConflictIngredient = this.ingredients.find(i => i.id === conflictIngredient.id);
        if (undefined != foundConflictIngredient) {
          foundConflictIngredient.disabled = true;
        }
      }
    }
  }

  categoryChanged() {
    this.ingredientService.getIngredientsByCategoryId(this.category.id).subscribe(data => {
      this.ingredients = data;
      this.refreshIngredientsData();
    });
  }
}
