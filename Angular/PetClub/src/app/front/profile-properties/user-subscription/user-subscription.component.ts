import { DatePipe, formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IUser } from 'src/app/_models/models';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-user-subscription',
  templateUrl: './user-subscription.component.html',
  styleUrls: ['./user-subscription.component.css']
})
export class UserSubscriptionComponent implements OnInit {

  user:IUser;
  subscriptionName:string;
  from:Date;
  fromPipe:any;
  until:Date;
  duration:number;

  constructor(private tokenStorageService:TokenStorageService, private router: Router) { 
    this.user =this.tokenStorageService.getUser();
    if(this.user == null){
      this.router.navigate(['/login']);
    }
  }

  ngOnInit(): void {
    this.calculateDetails();
  }

  calculateDetails():void{
    if(formatDate(this.user.sub_until,'yyyy-MM-dd','en_US') >= formatDate(new Date(),'yyyy-MM-dd','en_US')){
      this.subscriptionName = this.user.transactions[this.user.transactions.length-1].sub_name;
      this.from = this.user.transactions[this.user.transactions.length-1].date;
      this.until = this.user.sub_until;
      this.duration= this.user.transactions[this.user.transactions.length-1].sub_duration;
    }
    else{
      window.location.reload()
    }
  }

  
  
}
