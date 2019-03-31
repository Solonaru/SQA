import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Package } from '../../../entities/classes/item/package';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PackageService {

  private BASE_URL = 'http://localhost:8090/package/';
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getPackages(): Observable<Package[]> {
    return this.http.get(this.BASE_URL + 'all')
      .pipe(map((res: Package[]) => res));
  }

  insertPackage(pack: Package) {
    return this.http.post<Package>(this.BASE_URL + 'add', JSON.stringify(pack), this.httpOptions)
      .pipe(map((resp: Package) => resp));
  }

  deletePackage(pack: Package) {
    return this.http.delete(this.BASE_URL + 'delete/' + pack.id, this.httpOptions)
      .pipe(map((resp: Package) => resp));
  }

  updatePackage(pack: Package) {
    return this.http.put<Package>(this.BASE_URL + 'update', JSON.stringify(pack), this.httpOptions)
      .pipe(map((resp: Package) => resp));
  }
}
