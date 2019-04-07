import { Recipe } from './recipe';
import { Product } from '../product';

export class RecipeLine {
    id: Number;
    quantity: Number;
    recipe: Recipe;
    product: Product;

    constructor(product: Product) {
        this.product = product;
    }
}