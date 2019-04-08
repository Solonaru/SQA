import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Cart } from '../../entities/classes/cart';

import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CartService {

    private BASE_URL: string = "http://localhost:8090/cart/";
    private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    constructor(private http: HttpClient) { }

    getCustomerCarts(id) {
        return this.http.get(this.BASE_URL + `all/customer/${id}` ).pipe(map((res: Cart[]) => { return res }));
    }

    insertCart(cart: Cart) {
        return this.http.post<Cart>(this.BASE_URL + 'add', JSON.stringify(cart), this.httpOptions)
            .pipe(map((resp: Cart) => { return resp }));
    }

    updateCart(cart: Cart) {
        return this.http.put<Cart>(this.BASE_URL + 'update', JSON.stringify(cart), this.httpOptions)
            .pipe(map((resp: any) => { return resp }));
    }


}
