import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Product } from 'src/app/entities/classes/product';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private BASE_URL = 'http://localhost:8090/product/';
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getProducts(): Observable<Product[]> {
    return this.http.get(this.BASE_URL + 'all')
      .pipe(map((res: Product[]) => res));
  }

  insertProduct(product: Product) {
    return this.http.post<Product>(this.BASE_URL + 'add', JSON.stringify(product), this.httpOptions)
      .pipe(map((resp: Product) => resp));
  }

  deleteProduct(product: Product) {
    return this.http.delete(this.BASE_URL + 'delete/' + product.id, this.httpOptions)
      .pipe(map((resp: Product) => resp));
  }

  updateProduct(product: Product) {
    return this.http.put<Product>(this.BASE_URL + 'update', JSON.stringify(product), this.httpOptions)
      .pipe(map((resp: Product) => resp));
  }
}
