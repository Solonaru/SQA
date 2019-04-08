import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { CartLine } from '../../entities/classes/cartLine';

@Injectable({
    providedIn: 'root'
})
export class CartLineService {

    private BASE_URL: string = "http://localhost:8090/cartLine/";
    private httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    constructor(private http: HttpClient) { }
    
    insertCartLine(cartLine: CartLine) {
        return this.http.post<CartLine>(this.BASE_URL + 'add', JSON.stringify(cartLine), this.httpOptions)
        .pipe(map((resp: CartLine) => { return resp }));
    }

    deleteCartLine(cartLine: CartLine) {
        return this.http.delete(this.BASE_URL + 'delete/' + cartLine.id, this.httpOptions)
            .pipe(map((resp: any) => { return resp }));
    }
}