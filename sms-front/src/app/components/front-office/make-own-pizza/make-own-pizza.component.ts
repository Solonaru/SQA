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
  doughs: Recipe[];
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
    })
  }

  populateSauces() {
    this.categoryService.getCategoryByName("Tomato sauce recipes").subscribe(data => {
      this.sauces = data.items;
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
        
        for (let ingredient of event.container.data) {
          for (let conflictIngredient of JSON.parse(JSON.stringify(ingredient)).conflictIngredients) {
            let foundConflictIngredient = this.ingredients.find(i => i.id === conflictIngredient.id);
            if (undefined != foundConflictIngredient) {
              foundConflictIngredient.disabled = true;
            }
          }
        }

      }

      if (event.container.id == "cdk-drop-list-0") {

        this.ingredients.map(function(x) { 
          x.disabled = false; 
          return x
        });

        for (let ingredient of event.previousContainer.data) {
          for (let conflictIngredient of JSON.parse(JSON.stringify(ingredient)).conflictIngredients) {
            let foundConflictIngredient = this.ingredients.find(i => i.id === conflictIngredient.id);
            if (undefined != foundConflictIngredient) {
              foundConflictIngredient.disabled = true;
            }
          }
        }

      }

    }
  }
}
