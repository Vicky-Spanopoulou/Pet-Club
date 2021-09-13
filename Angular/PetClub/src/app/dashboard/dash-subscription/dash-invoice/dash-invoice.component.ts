import { Component, Input, OnInit, Output ,EventEmitter} from '@angular/core';
import { IInvoiceDetails, ITransaction, IUser } from 'src/app/_models/models';
import { InvoiceDetailsService } from 'src/app/_services/invoice-details/invoice-details.service';
import { TransactionService } from 'src/app/_services/transaction/transaction.service';
import { UserService } from 'src/app/_services/user.service';



@Component({
  selector: 'app-dash-invoice',
  templateUrl: './dash-invoice.component.html',
  styleUrls: ['./dash-invoice.component.css',
              '../../../../assets/css/dashboardCss/Defaultdashboard.css']
  
})
export class DashInvoiceComponent implements OnInit {

   
  @Input() dtoTrastaction:any;
  @Output() notify = new EventEmitter<any>();
  user:any;
  invoiceDetails:any
  constructor(private serviceUSer:UserService,private invoiveService:InvoiceDetailsService,private transtactionService:TransactionService){} 

  ngOnInit(): void {
    this. getUserByUsername();   
  }

  getUserByUsername(){
    this.serviceUSer.getUserByUsername(this.dtoTrastaction.username).subscribe((actualyUser:IUser) => {
      this.user = actualyUser;
      this.invoiveService.getInvoiceDetailsById(this.user.invoiceDetails.id).subscribe((actualyInvoice:IInvoiceDetails) => {
        this.invoiceDetails = actualyInvoice;
      });  
    })
  }

 editTransaction(invoice:any): void {
  invoice.userId = this.user.id;
  let answer = confirm('Are you sure change paid state on this transtaction?');
    if(answer){
      this.transtactionService.updateTransaction(invoice).subscribe(
        (data) => {
          this.notify.emit();
        },
        (error) => null
      );
      window.scrollTo(0, 0);
    }else{
      invoice.paid = !invoice.paid;
    }


  }


}
