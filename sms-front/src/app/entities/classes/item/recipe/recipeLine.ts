import { Recipe } from './recipe';
import { Product } from '../product';

export class RecipeLine {
    id: Number;
    quantity: Number;
    recipe: Recipe;
    component: Product;

    constructor(component: Product) {
        this.component = component;
    }
}