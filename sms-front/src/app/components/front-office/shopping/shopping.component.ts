import { Component, OnInit } from '@angular/core';
import { Item } from '../../../entities/classes/item/item';
import { ItemService } from '../../../providers/services/item/item.service';
import { CategoryService } from '../../../providers/services/category.service';
import { ActivatedRoute } from '@angular/router';
import { Customer } from '../../../entities/classes/customer';
import { CustomerService } from '../../../providers/services/customer.service';
import { Cart } from '../../../entities/classes/cart';
import { CartService } from '../../../providers/services/cart.service';
import { CartLine } from '../../../entities/classes/cartLine';
import { CartLineService } from '../../../providers/services/cartLine.service';

@Component({
  selector: 'app-shopping',
  templateUrl: './shopping.component.html',
  styleUrls: ['./shopping.component.css']
})
export class ShoppingComponent implements OnInit {
  
  items: Item[];
//  customer: Customer;
//  carts: Cart[];
//  cart: Cart;
//  cartLine: CartLine;

  constructor(private itemService: ItemService, private categoryService: CategoryService, private route: ActivatedRoute, private cartService: CartService, private cartLineService: CartLineService) { }

  ngOnInit() {
    this.populateItems(this.route.snapshot.paramMap.get('cat'));
//    const currentUserId = Number(localStorage.getItem('userLoggedIn'));
//    this.customer = new Customer();
//    this.customer.id = currentUserId;
//    this.cart = new Cart();
//    this.cart.customer = this.customer;
//    this.getCarts(currentUserId);
  }

  populateItems(categoryId: String) {
    this.categoryService.getCategoryById(categoryId).subscribe(category => {
      this.itemService.getListedItemsByCategoryId(category.id).subscribe(items => { this.items = items; });
    });
  }
  
//  getCarts(id) {
//    this.cartService.getCustomerCarts(id).subscribe(data => {this.carts = data; console.log(this.carts)});
//  }
//  
//  onAdd(item) {
//    this.cartLine = new CartLine();
//    this.cartLine.item = item;
//    this.cartLineService.insertCartLine(this.cartLine).subscribe(data => console.log(data));
//  }

}
