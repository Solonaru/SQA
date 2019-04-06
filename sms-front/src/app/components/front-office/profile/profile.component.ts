import { Component, OnInit } from '@angular/core';
import { Customer } from '../../../entities/classes/customer';
import { CustomerService } from '../../../providers/services/customer.service';
import { Address } from '../../../entities/classes/address';
import { AddressService } from '../../../providers/services/address.service';
import { City } from '../../../entities/classes/city';
import { CityService } from '../../../providers/services/city.service';
import { Order } from '../../../entities/classes/order';
import { OrderService } from '../../../providers/services/order.service';
import { Subscription } from '../../../entities/classes/subscription';
import { SubscriptionService } from '../../../providers/services/subscription.service';

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
  selectedCity: Number;
  delSelectedCity: Number;
  cities: City[];
  currentAddress: number = 0;
  newDeliveryAddress: Address = new Address();
  newSelectedCity: number;
  subscriptions: Subscription[] = [new Subscription()];
  
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

  constructor(private customerService: CustomerService, private orderService: OrderService, private cityService: CityService, private addressService: AddressService, private subscriptionService: SubscriptionService) { }

  ngOnInit() {
      const currentUserId = Number(localStorage.getItem('userLoggedIn'));
      this.currentUser = new Customer();
      this.currentUser.address = new Address();
      this.currentUser.address.city = new City();
      this.currentUser.deliveryAddresses = [this.currentUser.address];
      this.currentUser.subscriptions = [new Subscription()];
      this.getCustomer(currentUserId);
      this.getOrders(currentUserId);
      this.populateCities();
      this.populateSubscriptions();
  }
  
  getCustomer(id) {
    this.customerService.getCustomerById(id).subscribe(data => { 
      this.currentUser = data; 
      this.selectedCity = this.currentUser.address.city.id;
      this.delSelectedCity = this.currentUser.deliveryAddresses[this.currentAddress].city.id;
    });
  }
  
  updateCustomer() {
    this.customerService.updateCustomer(this.currentUser).subscribe( data => location.reload() );
  }
  
  updateAddress() {
    this.currentUser.address.city.id = this.selectedCity;
    this.addressService.updateAddress(this.currentUser.address).subscribe( data => this.updateCustomer() );
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
    this.orderService.getCustomerOrders(id).subscribe( data => { this.orders = data; } );
  }
  
  populateCities() {
    this.cityService.getCities().subscribe(data => { this.cities = data; });
  }
  
  populateSubscriptions() {
    this.subscriptionService.getActiveSubscriptions().subscribe(data => { this.subscriptions = data; });
  }
  
  getCurrentAddress(add) {
    this.currentAddress = this.currentUser.deliveryAddresses.findIndex(a => a.id == add.id);
  }
  
  updateDeliveryAddress() {
    this.currentUser.deliveryAddresses[this.currentAddress].city.id = this.delSelectedCity;
    this.addressService.updateAddress(this.currentUser.deliveryAddresses[this.currentAddress])
                                      .subscribe( data => location.reload() );
  }
  
  newAddress() {
    this.newDeliveryAddress.city = new City();
    this.newDeliveryAddress.city.id = this.newSelectedCity;
  }
  
  cancelAddress() {
    this.newDeliveryAddress.city = new City();
    this.newDeliveryAddress = new Address();
  }
  
  addDeliveryAddress() { 
    this.cityService.getCityById(this.newSelectedCity).subscribe(data => {
        this.newDeliveryAddress.city = data; this.addressService.insertAddress(this.newDeliveryAddress).subscribe(data => {
          this.currentUser.deliveryAddresses.push(data);
          this.updateCustomer();
        });
    });
  }
  
  hasSubscription(sub) {
    return this.currentUser.subscriptions.findIndex(s => s.id == sub.id) == -1 ? false : true; 
  }
  
  updateSubscriptions(sub) {
    let userSubscription = this.currentUser.subscriptions.findIndex(s => s.id == sub.id);
    if(userSubscription == -1) {
      this.currentUser.subscriptions.push(sub);
    } else {
      this.currentUser.subscriptions.splice(userSubscription, 1);
    }
  }
  

}
