import { HttpClient, HttpEvent, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IPet } from 'src/app/_models/models';

const API_URL = 'http://localhost:8081/pets/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class PetService {

  constructor(private http: HttpClient) { }

  createPet(pet: IPet): Observable<IPet>{
    return this.http.post<IPet>(API_URL + 'create', pet, httpOptions);
  }

  editPet(pet: IPet): Observable<IPet>{
    return this.http.post<IPet>(API_URL + 'edit', pet, httpOptions);
  }

  getAllPets():  Observable<any>{
    return this.http.get(API_URL + 'getAll', { responseType: 'json' });
  }

  getPetById(id: number):  Observable<any>{
    return this.http.get(API_URL + 'get/' + id, { responseType: 'json' });
  }

  deletePetById(id: number): Observable<IPet>{
    return this.http.get<IPet>(API_URL + 'delete/' + id);
  }
  
  upload(img: File , username:string,id:number): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', img);
    const req = new HttpRequest('POST', `http://localhost:8081/image/upload/pet/`+username+'/'+id, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.http.request(req);
  }
 
  getFiles(username:string,id:number): Observable<any> {
    return  this.http.get(`http://localhost:8081/image/files/`+username+'/petImage'+id,{ responseType: 'blob' });
  }
}
