import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IRating } from 'src/app/_models/models';

const API_URL = 'http://localhost:8081/rating/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  constructor(private http: HttpClient) { }

  createRating(rating: IRating): Observable<IRating>{
    return this.http.post<IRating>(API_URL + 'create', rating, httpOptions);
  }

  editRating(rating: IRating): Observable<IRating>{
    return this.http.post<IRating>(API_URL + 'edit', rating, httpOptions);
  }

  updateRating(rating: IRating): Observable<IRating>{
    return this.http.post<IRating>(API_URL + 'update', rating, httpOptions);
  }
  
  getByUserAndBusiness(userId: number,businessId:number): Observable<IRating>{
    return this.http.get<IRating>(API_URL + 'get/' + userId+"/"+businessId);
  }

  getBusinessAverageRatingById(businessId:number): Observable<number>{
    return this.http.get<number>(API_URL + 'getBusinessRating/' + businessId);
  }

  deleteRatingById(id: number): Observable<IRating>{
    return this.http.get<IRating>(API_URL + 'delete/' + id);
  }
  
}
