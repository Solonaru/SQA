import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Beverage } from '../../../entities/classes/item/beverage';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BeverageService {

  private BASE_URL = 'http://localhost:8090/beverage/';
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getBeverages(): Observable<Beverage[]> {
    return this.http.get(this.BASE_URL + 'all')
      .pipe(map((res: Beverage[]) => res));
  }

  insertBeverage(beverage: Beverage) {
    return this.http.post<Beverage>(this.BASE_URL + 'add', JSON.stringify(beverage), this.httpOptions)
      .pipe(map((resp: Beverage) => resp));
  }

  deleteBeverage(beverage: Beverage) {
    return this.http.delete(this.BASE_URL + 'delete/' + beverage.id, this.httpOptions)
      .pipe(map((resp: Beverage) => resp));
  }

  updateBeverage(beverage: Beverage) {
    return this.http.put<Beverage>(this.BASE_URL + 'update', JSON.stringify(beverage), this.httpOptions)
      .pipe(map((resp: Beverage) => resp));
  }
}
