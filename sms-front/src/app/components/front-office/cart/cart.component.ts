import { Component, OnInit } from '@angular/core';
import { Cart } from '../../../entities/classes/cart';
import { CartService } from '../../../providers/services/cart.service';
import { CartLine } from '../../../entities/classes/cartLine';
import { CartLineService } from '../../../providers/services/cartLine.service';
import { Customer } from '../../../entities/classes/customer';
import { CustomerService } from '../../../providers/services/customer.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  carts: Cart[];
  cart: Cart;
  customer: Customer;
  
  constructor(private cartService: CartService, private cartLineService: CartLineService) { }

  ngOnInit() {
    const currentUserId = Number(localStorage.getItem('userLoggedIn'));
    this.customer = new Customer();
    this.customer.id = currentUserId;
    this.cart = new Cart();
    this.cart.customer = this.customer;
    this.addCart();
  }
  
  getAllCarts(id) {
    this.cartService.getCustomerCarts(id).subscribe(data => { 
      this.carts = data;
    });
  }
  
  addCart() {
    this.cartService.insertCart(this.cart).subscribe(data => { return data});  
  }

}
