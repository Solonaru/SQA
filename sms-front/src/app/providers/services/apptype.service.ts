import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { map } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class AppTypeService {

    private BASE_URL: string = "http://localhost:8090/apptype/";
    private httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    constructor(private http: HttpClient) { }

    changeType(type: Number) {
        return this.http.post<Number>(this.BASE_URL, JSON.stringify(type), this.httpOptions)
            .pipe(map((resp: Number) => { return resp }));
    }

}