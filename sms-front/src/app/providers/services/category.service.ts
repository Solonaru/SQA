import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

import { Category } from '../../entities/classes/category';


@Injectable({
    providedIn: 'root'
})
export class CategoryService {
    private BASE_URL: string = "http://localhost:8090/category/";
    private httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    constructor(private http: HttpClient) { }

    getCategories() {
        return this.http.get(this.BASE_URL + 'all/active').pipe(map((res: Category[]) => { return res }));
    }

    getFrontOfficeCategories() {
        return this.http.get(this.BASE_URL + 'all/active/frontOffice').pipe(map((res: Category[]) => { return res }));
    }

    getNoParentCategories() {
        return this.http.get(this.BASE_URL + 'all/noParent').pipe(map((res: Category[]) => { return res }));
    }

    getNoChildCategories() {
        return this.http.get(this.BASE_URL + 'all/noChild').pipe(map((res: Category[]) => { return res }));
    }

    getCategoryById(categoryId: String) {
        return this.http.get(this.BASE_URL + categoryId)
            .pipe(map((res: Category) => { return res }));
    }

    getCategoryByName(categoryName: String) {
        return this.http.get(this.BASE_URL + 'name/' + categoryName)
            .pipe(map((res: Category) => { return res }));
    }

    insertCategory(category: Category) {
        return this.http.post<Category>(this.BASE_URL + 'add', JSON.stringify(category), this.httpOptions)
            .pipe(map((resp: Category) => { return resp }));
    }

    updateCategory(category: Category) {
        return this.http.put<Category>(this.BASE_URL + 'update', JSON.stringify(category), this.httpOptions)
            .pipe(map((resp: any) => { return resp }));
    }
    
    deleteCategory(category: Category) {
        return this.http.delete(this.BASE_URL + 'delete/' + category.id, this.httpOptions)
          .pipe(map((resp: any) => { return resp }));
    }
}