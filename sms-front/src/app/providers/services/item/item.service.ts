import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Item } from '../../../entities/classes/item/item';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  private BASE_URL = 'http://localhost:8090/item/';
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getItems(): Observable<Item[]> {
    return this.http.get(this.BASE_URL + 'all')
      .pipe(map((res: Item[]) => res));
  }

  getListedItemsByCategoryId(categoryId: Number) {
    return this.http.get(this.BASE_URL + 'all/listed/' + categoryId)
      .pipe(map((res: Item[]) => { return res }));
  }

  getItemById(itemId: string) {
    return this.http.get(this.BASE_URL + itemId)
      .pipe(map((res: Item) => { return res }));
  }

  insertItem(item: Item) {
    return this.http.post<Item>(this.BASE_URL + 'add', JSON.stringify(item), this.httpOptions)
      .pipe(map((resp: Item) => resp));
  }

  deleteItem(item: Item) {
    return this.http.delete(this.BASE_URL + 'delete/' + item.id, this.httpOptions)
      .pipe(map((resp: Item) => resp));
  }

  updateItem(item: Item) {
    return this.http.put<Item>(this.BASE_URL + 'update', JSON.stringify(item), this.httpOptions)
      .pipe(map((resp: Item) => resp));
  }
}
