import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { RecipeLine } from '../../../../entities/classes/item/recipe/recipeLine';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecipeLineService {

  private BASE_URL = 'http://localhost:8090/recipeLine/';
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getRecipeLines(): Observable<RecipeLine[]> {
    return this.http.get(this.BASE_URL + 'all')
      .pipe(map((res: RecipeLine[]) => res));
  }

  insertRecipeLine(recipeLine: RecipeLine) {
    return this.http.post<RecipeLine>(this.BASE_URL + 'add', JSON.stringify(recipeLine), this.httpOptions)
      .pipe(map((resp: RecipeLine) => resp));
  }

  deleteRecipeLine(recipeLine: RecipeLine) {
    return this.http.delete(this.BASE_URL + 'delete/' + recipeLine.id, this.httpOptions)
      .pipe(map((resp: RecipeLine) => resp));
  }

  updateRecipeLine(recipeLine: RecipeLine) {
    return this.http.put<RecipeLine>(this.BASE_URL + 'update', JSON.stringify(recipeLine), this.httpOptions)
      .pipe(map((resp: RecipeLine) => resp));
  }
}
