import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8081/user/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }), 
  observe: 'response' as 'body'
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    const tmp = { username, password };

    return this.http.post(AUTH_API + 'login', tmp, httpOptions );
  }

  register(
    firstName: string,
    lastName: string,
    username: string,
    email: string,
    password: string,
    phone: string,
    img: string,
    city: string,
    street: string
  ): Observable<any> {
    return this.http.post(
      AUTH_API + 'register',
      {
        firstName,
        lastName,
        username,
        email,
        password,
        phone,
        img,
        city,
        street,
      },
      httpOptions
    );
  }

  // register(user: IUser): Observable<IUser> {
  //   return this.http.post<IUser>(AUTH_API + 'register',user, httpOptions );
  // }
}
