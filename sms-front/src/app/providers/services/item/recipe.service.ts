import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Recipe } from '../../../entities/classes/item/recipe/recipe';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private BASE_URL = 'http://localhost:8090/recipe/';
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getRecipes(): Observable<Recipe[]> {
    return this.http.get(this.BASE_URL + 'all')
      .pipe(map((res: Recipe[]) => res));
  }

  insertRecipe(recipe: Recipe) {
    console.log(recipe);
    return this.http.post<Recipe>(this.BASE_URL + 'add', JSON.stringify(recipe), this.httpOptions)
      .pipe(map((resp: Recipe) => resp));
  }

  deleteRecipe(recipe: Recipe) {
    return this.http.delete(this.BASE_URL + 'delete/' + recipe.id, this.httpOptions)
      .pipe(map((resp: Recipe) => resp));
  }

  updateRecipe(recipe: Recipe) {
    return this.http.put<Recipe>(this.BASE_URL + 'update', JSON.stringify(recipe), this.httpOptions)
      .pipe(map((resp: Recipe) => resp));
  }
}
