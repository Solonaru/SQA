import { Component, OnInit } from '@angular/core';
import { Customer } from '../../../entities/classes/customer';
import { CustomerService } from '../../../providers/services/customer.service';
import { Address } from '../../../entities/classes/address';
import { City } from '../../../entities/classes/city';
import { Order } from '../../../entities/classes/order';
import { OrderService } from '../../../providers/services/order.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  
  currentUser: Customer;
  password: String;
  secondPassword: String;
  match: Boolean;
  orders: Order[] = [];
  
  pages: any[] = [
    {
      title: 'Personal info',
      icon: 'fas fa-user-circle',
      active: true
    },
    {
      title: 'Orders',
      icon: 'fas fa-utensils',
      active: false
    },
    {
      title: 'Subscriptions',
      icon: 'fas fa-birthday-cake',
      active: false
    }
  ];

  constructor(private customerService: CustomerService, private orderService: OrderService) { }

  ngOnInit() {
      const currentUserId = Number(localStorage.getItem('userLoggedIn'));
      this.currentUser = new Customer();
      this.currentUser.address = new Address();
      this.currentUser.address.city = new City();
      this.getEmployee(currentUserId);
      this.getOrders(currentUserId);
  }
  
  getEmployee(id) {
    this.customerService.getCustomerById(id).subscribe(data => { this.currentUser = data; });
  }
  
  updateCustomer() {
    this.customerService.updateCustomer(this.currentUser).subscribe();
  }
  
  onCheck() {
    this.secondPassword !== this.password ? this.match = false : this.match = true;
  }
  
  updatePassword() {
    this.currentUser.password = this.password;
    this.customerService.updateCustomer(this.currentUser).subscribe();
  }
  
  addActive(page) {
    for(let page of this.pages) {
        page.active = false;
    }
    this.pages[this.pages.findIndex(i => i.title === page.title)].active = true;
  }
  
  getOrders(id) {
    this.orderService.getCustomerOrders(id).subscribe( data => { this.orders = data; console.log(this.orders)} );
  }

}
