import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Ingredient } from '../../../entities/classes/item/ingredient';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Category } from '../../../entities/classes/category';

@Injectable({
  providedIn: 'root'
})
export class IngredientService {

  private BASE_URL = 'http://localhost:8090/ingredient/';
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getIngredients(): Observable<Ingredient[]> {
    return this.http.get(this.BASE_URL + 'all')
      .pipe(map((res: Ingredient[]) => res));
  }

  getIngredientsByCategory(category: Category) {
    return this.http.put(this.BASE_URL + 'all/category', JSON.stringify(category), this.httpOptions)
        .pipe(map((resp: Ingredient[]) => { return resp }));
  }

  getIngredientsByCategoryId(categoryId: Number) {
    return this.http.get(this.BASE_URL + `all/category/${categoryId}` ).pipe(map((res: Ingredient[]) => { return res }));
  }

  insertIngredient(ingredient: Ingredient) {
    return this.http.post<Ingredient>(this.BASE_URL + 'add', JSON.stringify(ingredient), this.httpOptions)
      .pipe(map((resp: Ingredient) => resp));
  }

  deleteIngredient(ingredient: Ingredient) {
    return this.http.delete(this.BASE_URL + 'delete/' + ingredient.id, this.httpOptions)
      .pipe(map((resp: Ingredient) => resp));
  }

  updateIngredient(ingredient: Ingredient) {
    return this.http.put<Ingredient>(this.BASE_URL + 'update', JSON.stringify(ingredient), this.httpOptions)
      .pipe(map((resp: Ingredient) => resp));
  }
}
