import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IUser } from 'src/app/_models/models';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-horizontal-nav',
  templateUrl: './horizontal-nav.component.html',
  styleUrls: ['./horizontal-nav.component.css',
              '../../../assets/css/dashboardCss/Defaultdashboard.css']
})
export class HorizontalNavComponent implements OnInit {
  loggedUser:IUser;
  menuOption:boolean = false;

  constructor(private tokenStorageService:TokenStorageService,private router:Router,private userService:UserService ) { 
    this.loggedUser = this.tokenStorageService.getUser();
  }

  ngOnInit(): void {
    this.getUser();
  }

  toggleMenu(){
    this.menuOption = !this.menuOption;
  }

  logout(): void {
    this.tokenStorageService.signOut();
    localStorage.removeItem("rememberMe");
    localStorage.removeItem("auth-token");
    localStorage.removeItem("auth-user");    
    this.router.navigate(['home']);
  }

  goToHome(){
    this.router.navigate(['home']);
  }
  getUser(): void {
    this.userService
      .getUserByUsername(this.loggedUser.username)
      .subscribe((event: any) => {
        this.loggedUser = event;
        this.tokenStorageService.saveUser(event);
      });
  }
}
