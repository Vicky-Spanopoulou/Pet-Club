import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IUser } from 'src/app/_models/models';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css', '../../../assets/css/default.css']
})
export class HomePageComponent implements OnInit {

  hasToken:boolean =false;
  isLoggedIn:boolean = false;
  loggedInUser:IUser;
  // user:User;
  role:string;

  constructor(private tokenStorageService: TokenStorageService,private router:Router) { 
    this.tokenStorageService.autoLogin();
    this.loggedInUser = this.tokenStorageService.getUser();
    this.role = this.loggedInUser.role;
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn && this.role === 'ROLE_ADMIN') {
      this.router.navigate(['dashboard/businesses']);
    } 
  }





  ngOnInit(): void {
 
  }

}
