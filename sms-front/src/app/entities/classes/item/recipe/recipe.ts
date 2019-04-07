import { Category } from '../../category';
import { ItemType } from '../../../enums/item-type';
import { Product } from '../product';
import { RecipeLine } from './recipeLine';

export class Recipe implements Product {
    id: number;
    name: string;
    stockQuantity: number;
    stockPrice: number;
    updateDate: Date;
    description: string;
    category: Category;
    imageUrl: string;
    listed: Boolean;
    price: number;
    itemType: ItemType;
    recipeLines: RecipeLine[];

    constructor(name: string) {
        this.name = name;
        this.recipeLines = [];
    }
}