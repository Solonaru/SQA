import { CartLine } from './cartLine';
import { Customer } from './customer'

export class Cart {
  id: Number;
  cartLines: CartLine[];
  customer: Customer;
} 