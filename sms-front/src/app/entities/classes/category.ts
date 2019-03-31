import { Item } from './item/item';

export class Category {
    id: Number;
    name: String;
    updateDate: Date;
    description: Blob;
    parentCategory: Category;
    childCategories: Category[];
    items: Item[];
    parent: Boolean;
}
