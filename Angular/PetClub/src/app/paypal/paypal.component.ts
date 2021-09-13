import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IPayPalConfig, ICreateOrderRequest } from 'ngx-paypal';
import { ISubscription, ITransaction, IUser } from '../_models/models';
import { TokenStorageService } from '../_services/token-storage.service';
import { TransactionService } from '../_services/transaction/transaction.service';

@Component({
  selector: 'app-paypal',
  templateUrl: './paypal.component.html',
  styleUrls: ['./paypal.component.css']
})
export class PaypalComponent implements OnInit {
    @Input()
    subscription:ISubscription;

    user:IUser;
    transaction:ITransaction;
  public payPalConfig ? : IPayPalConfig;

  constructor(
      private transactionService:TransactionService,
      private tokenService: TokenStorageService,
      private router: Router
      ) 
      { }

  ngOnInit(): void {
    this.user = this.tokenService.getUser();
    this.initConfig();
  }

  

  private initConfig(): void {
    this.payPalConfig = {
        currency: 'USD',
        clientId: 'sb',
        createOrderOnClient: (data) => < ICreateOrderRequest > {
            intent: 'CAPTURE',
            purchase_units: [{
                amount: {
                    currency_code: 'USD',
                    value: this.subscription.price.toString(),
                    breakdown: {
                        item_total: {
                            currency_code: 'USD',
                            value: this.subscription.price.toString()
                        }
                    }
                },
                items: [{
                    name: this.subscription.name,
                    quantity: '1',
                    category: 'DIGITAL_GOODS',
                    unit_amount: {
                        currency_code: 'USD',
                        value: this.subscription.price.toString(),
                    },
                }]
            }]
        },
        advanced: {
            commit: 'true'
        },
        style: {
            label: 'paypal',
            layout: 'vertical'
        },
        onApprove: (data, actions) => {
            actions.order.get().then((details:any) => {
            });

        },
        onClientAuthorization: (data) => {
            // console.log('onClientAuthorization - you should probably inform your server about completed transaction at this point', data);
            let now = new Date();
            this.transaction ={
                id:null,
                date : now,
                type : "PAYPAL",
                paypalId : data.id,
                paid : true,
                sub_name : this.subscription.name,
                sub_price : this.subscription.price.toString(),
                sub_duration : this.subscription.duration,
                sub_role : this.subscription.role,
                userId : this.user.id,
                advForEver : this.subscription.advForEver
            }
            
            this.sendTransaction();
            this.router.navigate(['/profile_properties']);
        },
        onCancel: (data, actions) => {
            // console.log('OnCancel', data, actions);
            // this.showCancel = true;

        },
        onError: err => {
            console.log('OnError', err);
            // this.showError = true;
        },
        onClick: (data, actions) => {
            // console.log('onClick', data, actions);
            // this.resetStatus();
        },
    };
  }

  sendTransaction(){
      this.transactionService.createTransaction(this.transaction).subscribe(
          (data)=> this.router.navigate(['/successful_payment']),
          (error) => null
      );
  }
}

