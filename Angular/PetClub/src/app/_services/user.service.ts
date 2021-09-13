import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IUser } from '../_models/models';
import { HttpHeaders } from '@angular/common/http';

const API_URL = 'http://localhost:8081/user/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }

  getAllUsers():  Observable<any>{
    return this.http.get(API_URL + 'getAll', { responseType: 'json' });
  }
  getUserByUsername(username:string):Observable<any>{
    return this.http.get(API_URL + 'getUserByUsername/' + username, { responseType: 'json' });
  }
  getUserByUserEmail(email:string):Observable<any>{
    return this.http.get(API_URL + 'getUserByMail/' + email, { responseType: 'json' });
  }

  getUserByAd(id: number): Observable<any>{
    return this.http.get(API_URL + 'getUserByAd/' + id, {responseType: 'json'});
  }

  deleteUserById(id: number): Observable<any>{
    return this.http.get(API_URL + 'delete/' + id, {responseType: 'text'});
  }

  getUserByPetId(id:number):Observable<any>{
    return this.http.get(API_URL + 'getByPet/' + id);
  }

  getUserByBusinessId(id:number):Observable<any>{
    return this.http.get(API_URL + 'getUserByBusiness/' + id);
  }

  upload(img: File , username:string,id:number): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', img);
    const req = new HttpRequest('POST', `http://localhost:8081/image/upload/user/`+username, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.http.request(req);
  }
 
  getFiles(username:string,id:number): Observable<any> {
    return  this.http.get(`http://localhost:8081/image/files/`+username+'/userImage'+id,{ responseType: 'blob' });
  }

  editUserDetails(user: any): Observable<any> {
    return this.http.post(API_URL + 'update', user, httpOptions);
  }

  updatePassword(userDetails: any): Observable<any> {
    return this.http.post(API_URL + 'updatePass', userDetails, httpOptions);
  }
  updatePasswordAdmin(username: any,newPassword:string): Observable<any> {
    return this.http.post(API_URL + 'resetPasswordAdmin/'+username, newPassword, httpOptions);
  }

  updateEmail(userDetails: any): Observable<any> {
    return this.http.post(API_URL + 'updateEmail', userDetails, httpOptions);
  }

  addNewUser(user: any): Observable<any> {
    return this.http.post(API_URL + 'addUser', user, httpOptions);
  }

  getUserClientsByUserId(id:number):Observable<any>{
    return this.http.get(API_URL + 'getClients/' + id);
  }

  resetPassword(email:string):Observable<any>{
    return this.http.get(API_URL+ 'resetpassword/'+email)
  }
  
}


