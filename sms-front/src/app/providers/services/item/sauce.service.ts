import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Sauce } from '../../../entities/classes/item/sauce';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SauceService {

  private BASE_URL = 'http://localhost:8090/sauce/';
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getSauces(): Observable<Sauce[]> {
    return this.http.get(this.BASE_URL + 'all')
      .pipe(map((res: Sauce[]) => res));
  }

  insertSauce(sauce: Sauce) {
    return this.http.post<Sauce>(this.BASE_URL + 'add', JSON.stringify(sauce), this.httpOptions)
      .pipe(map((resp: Sauce) => resp));
  }

  deleteSauce(sauce: Sauce) {
    return this.http.delete(this.BASE_URL + 'delete/' + sauce.id, this.httpOptions)
      .pipe(map((resp: Sauce) => resp));
  }

  updateSauce(sauce: Sauce) {
    return this.http.put<Sauce>(this.BASE_URL + 'update', JSON.stringify(sauce), this.httpOptions)
      .pipe(map((resp: Sauce) => resp));
  }
}
