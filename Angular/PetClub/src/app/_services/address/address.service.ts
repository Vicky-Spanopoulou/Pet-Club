import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IAddress } from 'src/app/_models/models';

const API_URL = 'http://localhost:8081/address/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class AddressService {

  constructor(private http: HttpClient) { }


  createAddress(address: IAddress): Observable<IAddress>{
    return this.http.post<IAddress>(API_URL + 'create', address, httpOptions);
  }

  editAddress(address: IAddress): Observable<IAddress>{
    return this.http.post<IAddress>(API_URL + 'edit', address, httpOptions);
  }


}
