import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ISubscription } from 'src/app/_models/models';

const API_URL = 'http://localhost:8081/subscriptions/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {

  constructor(private http: HttpClient) { }

  createSubscription(subscription: ISubscription): Observable<ISubscription>{
    return this.http.post<ISubscription>(API_URL + 'create', subscription, httpOptions);
  }

  editSubscription(subscription: ISubscription): Observable<ISubscription>{
    return this.http.post<ISubscription>(API_URL + 'edit', subscription, httpOptions);
  }

  getAllSubscriptions():  Observable<any>{
    return this.http.get(API_URL + 'getAll', { responseType: 'json' });
  }

  getSubscriptionById(id: number):  Observable<any>{
    return this.http.get(API_URL + 'getById/' + id, { responseType: 'json' });
  }

  deleteSubscriptionById(id: number): Observable<ISubscription>{
    return this.http.get<ISubscription>(API_URL + 'delete/' + id);
  }
  
}
