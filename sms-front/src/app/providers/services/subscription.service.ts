import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Subscription } from '../../entities/classes/subscription';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {
  private BASE_URL: string = "http://localhost:8090/subscription/";
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }
  
  getSubscriptions() {
    return this.http.get(this.BASE_URL + 'all').pipe(map((res: Subscription[]) => { return res }));
  }

  getActiveSubscriptions() {
    return this.http.get(this.BASE_URL + 'all/active').pipe(map((res: Subscription[]) => { return res }));
  }

  getSubscriptionById(subscriptionId: Number) {
    return this.http.get(this.BASE_URL + subscriptionId)
      .pipe(map((res: Subscription) => { return res }));
  }

  insertSubscription(subscription: Subscription) {
    return this.http.post<Subscription>(this.BASE_URL + 'add', JSON.stringify(subscription), this.httpOptions)
      .pipe(map((resp: Subscription) => { return resp }));
  }

  updateSubscription(subscription: Subscription) {
    return this.http.put<Subscription>(this.BASE_URL + 'update', JSON.stringify(subscription), this.httpOptions)
      .pipe(map((resp: any) => { return resp }));
  }

  deleteSubscription(subscription: Subscription) {
    return this.http.delete(this.BASE_URL + 'delete/' + subscription.id, this.httpOptions)
      .pipe(map((resp: any) => { return resp }));
  }
}
