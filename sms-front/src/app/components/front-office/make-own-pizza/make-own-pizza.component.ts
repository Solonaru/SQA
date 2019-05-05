import { Component, OnInit } from '@angular/core';
import { Category } from '../../../entities/classes/category';
import { Ingredient } from '../../../entities/classes/item/ingredient';
import { Recipe } from '../../../entities/classes/item/recipe/recipe';
import { CategoryService } from '../../../providers/services/category.service';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { IngredientService } from '../../../providers/services/item/ingredient.service';
import { RecipeLine } from '../../../entities/classes/item/recipe/recipeLine';
import { RecipeService } from '../../../providers/services/item/recipe/recipe.service';
import { RecipeLineService } from '../../../providers/services/item/recipe/recipeLine.service';

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

  totalPrice: Number;

  constructor(private categoryService: CategoryService,
    private ingredientService: IngredientService,
    private recipeService: RecipeService,
    private recipeLineService: RecipeLineService) { }

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
      this.categories.splice(this.categories.length - 1, 1);
      this.category = this.categories[0];
      this.ingredientService.getIngredientsByCategoryId(this.category.id).subscribe(data => {
        this.ingredients = data;
      });
    });
  }

  populateDoughs() {
    this.categoryService.getCategoryByName("Dough recipes").subscribe(data => {
      this.doughs = <Recipe[]>data.items;
      this.dough = this.doughs[0];
    })
  }

  populateSauces() {
    this.categoryService.getCategoryByName("Tomato sauce recipes").subscribe(data => {
      this.sauces = <Recipe[]>data.items;
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

      if (event.container.id == "cdk-drop-list-0") {

        this.ingredients.map(function (x) {
          x.disabled = false;
          return x
        });

      }

      this.refreshIngredientsData();

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

  // TODO: First of all deserializer for product should be made
  onAddToCart() {
    let recipe: Recipe = new Recipe("Personalized");

    let recipeLineDough: RecipeLine = new RecipeLine(this.dough);
    let recipeLineSauce: RecipeLine = new RecipeLine(this.sauce);

    recipe.recipeLines.push(recipeLineDough);
    recipe.recipeLines.push(recipeLineSauce);

    for (let ingredient of this.pizzaIngredients) {
      let recipeLine = new RecipeLine(ingredient);
      recipe.recipeLines.push(recipeLine);
    }

    this.recipeService.insertRecipe(recipe).subscribe(data => { });
  }

  onCloseStub() {
    this.pizzaIngredients.length = 0;
  }

}
