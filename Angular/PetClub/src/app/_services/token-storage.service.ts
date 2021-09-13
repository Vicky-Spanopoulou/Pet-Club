import { Injectable } from '@angular/core';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  constructor() { }

  signOut(): void {
    window.sessionStorage.clear();
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user: any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return {};
  }

  autoLogin(){
    const accessTokenObj = localStorage.getItem("auth-token");
    // Retrieve rememberMe value from local storage
    const rememberMe = localStorage.getItem('rememberMe');
    if (accessTokenObj && rememberMe == 'yes') {
      
      this.saveToken(accessTokenObj);
      if(localStorage.getItem('auth-user')){
       let jUser:any=localStorage.getItem('auth-user');
        this.saveUser(JSON.parse(jUser));
      }
    } else {
      // console.log("You need to login")
    }
   }
}
