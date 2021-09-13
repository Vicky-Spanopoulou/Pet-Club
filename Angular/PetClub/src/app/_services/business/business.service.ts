import { HttpClient, HttpEvent, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IBusiness } from 'src/app/_models/models';


const API_URL = 'http://localhost:8081/business/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class BusinessService {

  constructor(private http: HttpClient) { }

  createBusiness(business: IBusiness): Observable<IBusiness>{
    return this.http.post<IBusiness>(API_URL + 'create', business, httpOptions);
  }

  editBusiness(business: IBusiness): Observable<IBusiness>{
    return this.http.post<IBusiness>(API_URL + 'edit', business, httpOptions);
  }

  getAllBusinesses():  Observable<any>{
    return this.http.get(API_URL + 'avgRating', { responseType: 'json' });
  }

  getAllPublishedBusinesses():  Observable<any>{
    return this.http.get(API_URL + 'getAllPublished', { responseType: 'json' });
  }

  getBusinessesById(id: number):  Observable<any>{
    return this.http.get(API_URL + 'get/' + id, { responseType: 'json' });
  }

  getBusinessesByUserId(id: number):  Observable<any>{
    return this.http.get(API_URL + 'getbyUser/' + id, { responseType: 'json' });
  }

  deleteBusinessById(id: number): Observable<IBusiness>{
    return this.http.get<IBusiness>(API_URL + 'delete/' + id);
  }

  uploadLogo(img: File , username:string,id:number): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', img);
    const req = new HttpRequest('POST', `http://localhost:8081/image/upload/businessProfile/`+username+'/'+id, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.http.request(req);
  }
 
  getFilesLogo(username:string, id:number): Observable<any> {
    return  this.http.get(`http://localhost:8081/image/files/`+username+'/busProfImage'+id,{ responseType: 'blob' });
  }

  uploadBg(img: File , username:string, id:number): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', img);
    const req = new HttpRequest('POST', `http://localhost:8081/image/upload/businessBackground/`+username+'/'+id, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.http.request(req);
  }
 
  getFilesBg(username:string,id:number): Observable<any> {
    return  this.http.get(`http://localhost:8081/image/files/`+username+'/busBackImage'+id,{ responseType: 'blob' });
  }

  getTotalUncheckedBusiness(): Observable<any>{
    return this.http.get(API_URL + 'unchekedTotalNumber',{ responseType: 'json' } )
  }
}


