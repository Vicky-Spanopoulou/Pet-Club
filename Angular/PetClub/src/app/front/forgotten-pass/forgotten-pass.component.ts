import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IUser } from 'src/app/_models/models';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-forgotten-pass',
  templateUrl: './forgotten-pass.component.html',
  styleUrls: ['./forgotten-pass.component.css']
})
export class ForgottenPassComponent implements OnInit {
  user_email:string;
  loggedInUser:IUser;

  constructor(private userService: UserService,
    private tokenStorage:TokenStorageService,
    private router:Router) {
    this.tokenStorage.autoLogin();
    this.loggedInUser = this.tokenStorage.getUser();
    if(this.loggedInUser){
      this.router.navigate(['home']);
    } 
   }

  ngOnInit(): void {
  }
  resetPassword(){
    this.userService.resetPassword(this.user_email).subscribe(
      (data) => null,
      (error)=>null     
    );
  }
}
