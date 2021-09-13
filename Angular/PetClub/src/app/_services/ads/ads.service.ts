import { HttpClient, HttpEvent, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IAd } from 'src/app/_models/models';

const API_URL = 'http://localhost:8081/ads/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
}; 

@Injectable({
  providedIn: 'root'
})
export class AdsService {

  constructor(private http: HttpClient) { }

  getAllAds():  Observable<any>{
    return this.http.get(API_URL + 'getAll', { responseType: 'json' });
  }

  getAdById(id: number):  Observable<IAd>{
    return this.http.get<IAd>(API_URL + 'getById/' + id, { responseType: 'json' });
  }

  createAd(ad: IAd): Observable<IAd>{
    return this.http.post<IAd>(API_URL + 'create', ad, httpOptions);
  }

  editAd(ad: IAd): Observable<IAd>{
    return this.http.post<IAd>(API_URL + 'edit', ad, httpOptions);
  }

  deleteAdById(id: number): Observable<IAd>{
    return this.http.get<IAd>(API_URL + 'delete/' + id);
  }

  upload(img: File , username:string,id:number): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', img);
    const req = new HttpRequest('POST', `http://localhost:8081/image/upload/lf/`+username+'/'+id, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.http.request(req);
  }
 
  getFiles(username:string,id:number): Observable<any> {
    return  this.http.get(`http://localhost:8081/image/files/`+username+'/adImage'+id,{ responseType: 'blob' });
  }

}
