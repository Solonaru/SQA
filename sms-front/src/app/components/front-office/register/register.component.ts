import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../../providers/services/customer.service';
import { Customer } from '../../../entities/classes/customer';
import { AddressService } from '../../../providers/services/address.service';
import { Address } from '../../../entities/classes/address';
import { CityService } from '../../../providers/services/city.service';
import { City } from '../../../entities/classes/city';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit { 
  cities: City[];
  selectedCity: Number;
    
  customer: Customer;
  firstName: String;
  lastName: String;
  secondPassword: String;
  match: Boolean;
    
  constructor(private customerService: CustomerService, private addressService: AddressService, private cityService: CityService) {}

  ngOnInit() {
     this.customer = new Customer();
     this.customer.address = new Address();
     this.populateCities();
  };
    
  populateCities() {
    this.cityService.getCities().subscribe(data => { this.cities = data; });
  };
    
  onRegister() {
     this.insertCustomer();
  };
    
  onCheck() {
      this.secondPassword !== this.customer.password ? this.match = false : this.match = true;
  }

  insertCustomer() {
      this.customer.name = `${this.firstName} ${this.lastName}`;     
      
      this.cityService.getCityById(this.selectedCity).subscribe(data => {
        this.customer.address.city = data;
        this.addressService.insertAddress(this.customer.address).subscribe(data => {
            this.customer.address = data;
            this.customerService.insertCustomer(this.customer).subscribe(data => {
                window.location.replace('/login');
            });
        });
      });
      
  };
}
