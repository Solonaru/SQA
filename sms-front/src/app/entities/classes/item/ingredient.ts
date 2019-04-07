import { Category } from '../category';
import { ItemType } from '../../enums/item-type';
import { Product } from './product';

export class Ingredient implements Product {
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
    disabled: Boolean;
    conflictIngredients: Ingredient[];
}