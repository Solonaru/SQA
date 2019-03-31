import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Consumable } from '../../../entities/classes/item/consumable';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConsumableService {

  private BASE_URL = 'http://localhost:8090/consumable/';
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getConsumables(): Observable<Consumable[]> {
    return this.http.get(this.BASE_URL + 'all')
      .pipe(map((res: Consumable[]) => res));
  }

  insertConsumable(consumable: Consumable) {
    return this.http.post<Consumable>(this.BASE_URL + 'add', JSON.stringify(consumable), this.httpOptions)
      .pipe(map((resp: Consumable) => resp));
  }

  deleteConsumable(consumable: Consumable) {
    return this.http.delete(this.BASE_URL + 'delete/' + consumable.id, this.httpOptions)
      .pipe(map((resp: Consumable) => resp));
  }

  updateConsumable(consumable: Consumable) {
    return this.http.put<Consumable>(this.BASE_URL + 'update', JSON.stringify(consumable), this.httpOptions)
      .pipe(map((resp: Consumable) => resp));
  }
}
