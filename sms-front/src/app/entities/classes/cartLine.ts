import { Cart } from './cart';
import { Item } from './item/item';

export class CartLine {
  id: Number;
  quantity: Number;
  cart: Cart;
  item: Item;
}