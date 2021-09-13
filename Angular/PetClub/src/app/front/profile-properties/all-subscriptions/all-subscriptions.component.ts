import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ISubscription } from 'src/app/_models/models';
import { SubscriptionService } from 'src/app/_services/subscription/subscription.service';
import { ShoppingCartComponent } from '../shopping-cart/shopping-cart.component';

@Component({
  selector: 'app-all-subscriptions',
  templateUrl: './all-subscriptions.component.html',
  styleUrls: ['./all-subscriptions.component.css',
              '../../../../assets/css/containers.css',
              '../../../../assets/css/default.css',
              '../user-properties/user-properties.component.css']
})
export class AllSubscriptionsComponent implements OnInit {

  id:number;
  subscriptions:ISubscription[];
  
  constructor(private subService:SubscriptionService,) { }

  ngOnInit(): void {
    this.getAllSubscriptions();
  }

getAllSubscriptions(){
  this.subService.getAllSubscriptions().subscribe((actualySub:ISubscription[]) =>{
    actualySub.sort((a,b) => a.queue < b.queue ? -1 : 1);
    this.subscriptions = actualySub;
  });
}



}
