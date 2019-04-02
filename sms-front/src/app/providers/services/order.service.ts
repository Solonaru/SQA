import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Order } from '../../entities/classes/order';

import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private BASE_URL: string = "http://localhost:8090/order/";
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }
  
  getCustomerOrders(id) {
    return this.http.get(this.BASE_URL + `allByCustomer/${id}` ).pipe(map((res: Order[]) => { return res }));
  }


}
