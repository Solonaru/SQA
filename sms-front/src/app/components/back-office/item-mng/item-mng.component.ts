import { Component, OnInit } from '@angular/core';
import { ItemService } from '../../../providers/services/item/item.service';
import { Item } from '../../../entities/classes/item/item';
import { ItemType } from '../../../entities/enums/item-type';
import { IngredientService } from '../../../providers/services/item/ingredient.service';
import { BeverageService } from '../../../providers/services/item/beverage.service';
import { SauceService } from '../../../providers/services/item/sauce.service';
import { ConsumableService } from '../../../providers/services/item/consumable.service';
import { RecipeService } from '../../../providers/services/item/recipe.service';
import { PackageService } from '../../../providers/services/item/package.service';

@Component({
  selector: 'app-item-mng',
  templateUrl: './item-mng.component.html',
  styleUrls: ['./item-mng.component.css']
})
export class ItemMngComponent implements OnInit {

  items: Item[];
  item: Item;

  constructor(
    private itemService: ItemService,
    private ingredientService: IngredientService,
    private beverageService: BeverageService,
    private sauceService: SauceService,
    private consumableService: ConsumableService,
    private recipeService: RecipeService,
    private packageService: PackageService
  ) { }

  ngOnInit() {
    this.getItems();
  }

  getItems() {
    this.itemService.getItems().subscribe(
      data => {
        return this.items = data;
      }
    );
  }

  onUpdate(item: Item) {
    this.item = item;
  }

  onDelete(item: Item) {
    this.item = item;
  }

  onAdd() {
    this.item = new Item();
  }

  onConfirmDelete() {
    switch (this.item.itemType) {
      case ItemType.INGREDIENT: {
        this.ingredientService.deleteIngredient(this.item).subscribe(
          data => { location.reload(); }
        );
        break;
      }
      case ItemType.BEVERAGE: {
        this.beverageService.deleteBeverage(this.item).subscribe(
          data => { location.reload(); }
        );
        break;
      }
      case ItemType.SAUCE: {
        this.sauceService.deleteSauce(this.item).subscribe(
          data => { location.reload(); }
        );
        break;
      }
      case ItemType.CONSUMABLE: {
        this.consumableService.deleteConsumable(this.item).subscribe(
          data => { location.reload(); }
        );
        break;
      }
      case ItemType.RECIPE: {
        this.recipeService.deleteRecipe(this.item).subscribe(
          data => { location.reload(); }
        );
        break;
      }
      case ItemType.PACKAGE: {
        this.packageService.deletePackage(this.item).subscribe(
          data => { location.reload(); }
        );
        break;
      }
      default: {
        console.log("Warning!! No such item.");
      }
    }
  }

  updateItem() {
    switch (this.item.itemType) {
      case ItemType.INGREDIENT: {
        this.ingredientService.updateIngredient(this.item).subscribe();
        break;
      }
      case ItemType.BEVERAGE: {
        this.beverageService.updateBeverage(this.item).subscribe();
        break;
      }
      case ItemType.SAUCE: {
        this.sauceService.updateSauce(this.item).subscribe();
        break;
      }
      case ItemType.CONSUMABLE: {
        this.consumableService.updateConsumable(this.item).subscribe();
        break;
      }
      case ItemType.RECIPE: {
        this.recipeService.updateRecipe(this.item).subscribe();
        break;
      }
      case ItemType.PACKAGE: {
        this.packageService.updatePackage(this.item).subscribe();
        break;
      }
      default: {
        console.log("Warning!! No such item.");
      }
    }
  }

  insertItem() {
    this.ingredientService.insertIngredient(this.item).subscribe(
      data => { location.reload(); }
    );

  }

  onSubmit() {
    if (this.item.id !== undefined) {
      this.updateItem();
    } else {
      this.insertItem();
    }
  }

}
