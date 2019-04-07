import { Item } from '../product';
import { Category } from '../category';
import { ItemType } from '../../enums/item-type';

export class Product extends Item {
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
}