import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../../providers/services/customer.service';
import { Customer } from '../../../entities/customer';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  customer: Customer;
  firstName: String;
  lastName: String;
    
  constructor(private customerService: CustomerService) { }

  ngOnInit() {
     this.customer = new Customer();
  }
    
  onRegister() {
     this.insertCustomer();
  }

  insertCustomer() {
      this.customer.name = `${this.firstName} ${this.lastName}`;
      this.customerService.insertCustomer(this.customer).subscribe(data => {
        location.reload();
      });
  }
}
