import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ISubscription, ITransaction, IUser } from 'src/app/_models/models';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { TransactionService } from 'src/app/_services/transaction/transaction.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-dash-subscription',
  templateUrl: './dash-subscription.component.html',
  styleUrls: [
    './dash-subscription.component.css',
    '../dash-users/dash-users.component.css',
    '../../../assets/css/dashboardCss/Defaultdashboard.css',
  ],
})
export class DashSubscriptionComponent implements OnInit {
  editInvoice: boolean = false;
  searchTransaction: string;
  editTransaction: ITransaction;
  transactions: any[];
  filterTransactions: any[];
  loggedInUser: IUser;

  trasanctionForm: any = {
    id: null,
    date: null,
    type: null,
    paypalId: null,
    paid: null,
    sub_name: null,
    sub_price: null,
    sub_duration: null,
    sub_role: null,
    username: null,
    advForEver: null,
  };
  constructor(
    private transactionService: TransactionService,
    private userService: UserService,
    private tokenSTorageService: TokenStorageService,
    private router: Router
  ) {
    this.tokenSTorageService.autoLogin();
    this.loggedInUser = tokenSTorageService.getUser();
    if (this.loggedInUser.role !== 'ROLE_ADMIN') {
      this.router.navigate(['home']);
    }
  }

  ngOnInit(): void {
    this.getAllTransactions();
  }

  getAllTransactions() {
    this.transactionService
      .getAllTransactions()
      .subscribe((actualTransactions: ITransaction[]) => {
        this.transactions = actualTransactions;
        this.filterTransactions = this.transactions;
      });
  }
  handleTransactions() {
    this.filterTransactions = JSON.parse(JSON.stringify(this.transactions));

    if (this.searchTransaction) {
      this.filterTransactions = this.filterTransactions.filter((x) =>
        x.sub_name.toUpperCase().includes(this.searchTransaction.toUpperCase())
      );
    }
  }

  visibleInvoice(transaction: any) {
    this.editInvoice = true;
    this.trasanctionForm.id = transaction.id;
    this.trasanctionForm.date = transaction.date;
    this.trasanctionForm.paid = transaction.paid;
    this.trasanctionForm.paypalId = transaction.paypalId;
    this.trasanctionForm.type = transaction.type;
    this.trasanctionForm.sub_name = transaction.sub_name;
    this.trasanctionForm.sub_role = transaction.sub_role;
    this.trasanctionForm.sub_price = transaction.sub_price;
    this.trasanctionForm.sub_duration = transaction.sub_duration;
    this.trasanctionForm.username = transaction.username;
    this.trasanctionForm.advForEver = transaction.advForEver;
  }

  updateTable() {
    this.getAllTransactions();
  }
}


