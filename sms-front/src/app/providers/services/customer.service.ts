import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Customer } from '../../entities/classes/customer';

import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private BASE_URL: string = "http://localhost:8090/customer/";
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  insertCustomer(customer: Customer) {
    return this.http.post<Customer>(this.BASE_URL + 'add', JSON.stringify(customer), this.httpOptions)
      .pipe(map((resp: any) => { return resp }));
  }

  getCustomerById(customerId: Number) {
    return this.http.get(this.BASE_URL + customerId)
      .pipe(map((res: Customer) => { return res }));
  }

  getCustomers() {
    return this.http.get(this.BASE_URL + 'all').pipe(map((res: Customer[]) => { return res }));
  }

  updateCustomer(customer: Customer) {
    return this.http.put<Customer>(this.BASE_URL + 'update', JSON.stringify(customer), this.httpOptions)
        .pipe(map((resp: any) => { return resp }));
  }

}
