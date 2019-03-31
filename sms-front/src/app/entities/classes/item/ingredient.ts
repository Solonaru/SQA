import { Category } from '../category';
import { ItemType } from '../../enums/item-type';

export class Ingredient {
    id: Number;
    name: String;
    stockQuantity: Number;
    stockPrice: Number;
    updateDate: Date;
    description: Date;
    category: Category;
    imageUrl: String;
    listed: Boolean;
    price: Number;
    itemType: ItemType;
}