import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'

import { map } from 'rxjs/operators';
import { Location } from '../../entities/classes/location';


@Injectable({
    providedIn: 'root'
})

export class LocationService {

    private BASE_URL: string = "http://localhost:8090/location/";
    private httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    constructor(private http: HttpClient) { }

    getLocations() {
        return this.http.get(this.BASE_URL + 'all/active').pipe(map((res: Location[]) => { return res }));
    }

    getLocationById(locationId: String) {
        return this.http.get(this.BASE_URL + locationId)
            .pipe(map((res: Location) => { return res }));
    }

    insertLocation(location: Location) {
        return this.http.post<Location>(this.BASE_URL + 'add', JSON.stringify(location), this.httpOptions)
            .pipe(map((resp: Location) => { return resp }));
    }

    updateLocation(location: Location) {
        return this.http.put<Location>(this.BASE_URL + 'update', JSON.stringify(location), this.httpOptions)
            .pipe(map((resp: any) => { return resp }));
    }

    deleteLocation(location: Location) {
        return this.http.delete(this.BASE_URL + 'delete/' + location.id, this.httpOptions)
            .pipe(map((resp: any) => { return resp }));
    }

}