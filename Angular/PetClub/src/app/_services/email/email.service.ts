import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IEmail } from 'src/app/_models/models';

const API_URL = 'http://localhost:8081/email/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  constructor(private http: HttpClient) { }

  // sendMail(): Observable<any>{}
  sendEmail(mail: IEmail): Observable<IEmail>{
    return this.http.post<IEmail>(API_URL + 'send', mail, httpOptions);
  }

  newsletterRegister(mail:IEmail): Observable<IEmail>{
    return this.http.post<IEmail>(API_URL + "newsLetter", mail, httpOptions);
  }

}
