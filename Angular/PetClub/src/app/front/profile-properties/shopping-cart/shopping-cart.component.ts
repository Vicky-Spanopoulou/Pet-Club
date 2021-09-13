import {
  Component,
  Input,
  OnChanges,
  OnInit,
  SimpleChanges,
} from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { formatDate } from '@angular/common';
import {
  ICity,
  IInvoiceDetails,
  ISubscription,
  ITransaction,
  IUser,
} from 'src/app/_models/models';
import { CategoriesService } from 'src/app/_services/categories/categories.service';
import { InvoiceDetailsService } from 'src/app/_services/invoice-details/invoice-details.service';
import { SubscriptionService } from 'src/app/_services/subscription/subscription.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { TransactionService } from 'src/app/_services/transaction/transaction.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: [
    './shopping-cart.component.css',
    '../user-properties/user-properties.component.css',
    '../../../../assets/css/containers.css',
    '../../../../assets/css/default.css',
  ],
})
export class ShoppingCartComponent implements OnInit, OnChanges {
  id: number;
  choosenSubscription: any;
  loggedUser: IUser;
  checkBox1 = false;
  checkBox2 = false;
  editMode = false;
  showPaypal = false;
  showBankTransfer = false;
  purchaseMethod: boolean;
  now: Date = new Date();
  invoiceDetails: any;
  cityCategories: any[];
  purchaceMethod: string = '';
  transaction: ITransaction;

  invoiceDetailsForm: any = {
    id: null,
    companyName: '',
    companyEmail: '',
    financialService: '',
    userId: null,
    vatNumber: 0,
    street: '',
    city: '',
    phone: '',
    postalCode: '',
  };

  //We save a copy of the invoice details
  //as it is in db before we write something
  //in form.
  invoiceDetailsAsInDb: any = {
    id: null,
    companyName: '',
    companyEmail: '',
    financialService: '',
    userId: null,
    vatNumber: 0,
    street: '',
    city: '',
    phone: '',
    postalCode: '',
  };

  constructor(
    private route: ActivatedRoute,
    private subService: SubscriptionService,
    private tokenStorageService: TokenStorageService,
    private invoiceService: InvoiceDetailsService,
    private categoriesService: CategoriesService,
    private router: Router,
    private transactionService: TransactionService
  ) {
    this.tokenStorageService.autoLogin();

    this.loggedUser = this.tokenStorageService.getUser();
    if (!this.loggedUser) {
      this.router.navigate(['home']);
    } else if (
      this.loggedUser.sub_until !== null ||
      formatDate(this.loggedUser.sub_until, 'yyyy-MM-dd', 'en_US') >
        formatDate(new Date(), 'yyyy-MM-dd', 'en_US')
    ) {
      this.router.navigate(['home']);
    }
  }
  ngOnChanges() {}

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.id = +params['id'];
      this.subService
        .getSubscriptionById(this.id)
        .subscribe((acualySubscription: ISubscription) => {
          this.choosenSubscription = acualySubscription;
        });
    });
    this.getAllCityCategories();
    this.getInvoiceDetails();
    this.checkBox2 = false;
  }

  editInvoiceDetails() {
    this.invoiceDetailsForm.id = this.invoiceDetails.id;
    this.invoiceDetailsForm.companyName = this.invoiceDetails.companyName;
    this.invoiceDetailsForm.companyEmail = this.invoiceDetails.companyEmail;
    this.invoiceDetailsForm.financialService =
      this.invoiceDetails.financialService;
    this.invoiceDetailsForm.userId = this.loggedUser.id;
    this.invoiceDetailsForm.vatNumber = this.invoiceDetails.vatNumber;
    this.invoiceDetailsForm.street = this.invoiceDetails.address.street;
    this.invoiceDetailsForm.city = this.invoiceDetails.address.city.name;
    this.invoiceDetailsForm.postalCode = this.invoiceDetails.address.postalCode;
    this.invoiceDetailsForm.phone = this.invoiceDetails.phone;

    this.invoiceDetailsAsInDb.id = this.invoiceDetails.id;
    this.invoiceDetailsAsInDb.companyName = this.invoiceDetails.companyName;
    this.invoiceDetailsAsInDb.companyEmail = this.invoiceDetails.companyEmail;
    this.invoiceDetailsAsInDb.financialService =
      this.invoiceDetails.financialService;
    this.invoiceDetailsAsInDb.userId = this.loggedUser.id;
    this.invoiceDetailsAsInDb.vatNumber = this.invoiceDetails.vatNumber;
    this.invoiceDetailsAsInDb.street = this.invoiceDetails.address.street;
    this.invoiceDetailsAsInDb.city = this.invoiceDetails.address.city.name;
    this.invoiceDetailsAsInDb.postalCode =
      this.invoiceDetails.address.postalCode;
    this.invoiceDetailsAsInDb.phone = this.invoiceDetails.phone;
  }

  getInvoiceDetails() {
    this.invoiceService
      .getInvoiceByUserId(this.loggedUser.id)
      .subscribe((acualyInvoiceDetails: IInvoiceDetails) => {
        this.invoiceDetails = acualyInvoiceDetails;
        this.editInvoiceDetails();
      });
  }

  getAllCityCategories() {
    this.categoriesService.getAllCities().subscribe((acualyCities: ICity[]) => {
      this.cityCategories = acualyCities;
    });
  }

  toggleEditForm() {
    let edit = document.getElementById('edit');
    let cancel = document.getElementById('cancel');
    edit?.classList.toggle('hidden');
    cancel?.classList.toggle('hidden');

    let inputs = document.getElementsByClassName('inputs');
    let submit = document.getElementById('submit');

    for (let i = 0; i < inputs.length; i++) {
      inputs[i].classList.toggle('disabled-input');
    }
    submit?.classList.toggle('hidden');

    this.checkBox1 = false;
    this.checkBox2 = false;
    this.checkPurchaceMethod();
    //is in edit mode?
    this.editMode = !this.editMode;
  }

  cancelEdit(): void {
    this.toggleEditForm();
  }

  toggleCheckBox1() {
    this.checkBox1 = !this.checkBox1;
    this.checkPurchaceMethod();
  }

  toggleCheckBox2() {
    this.checkBox2 = !this.checkBox2;
    this.checkPurchaceMethod();
  }

  checkPurchaceMethod() {
    if (
      this.isPurchaceReady() &&
      this.checkBox1 == true &&
      this.checkBox2 == true
    ) {
      this.purchaseMethod = true;
    } else {
      this.purchaseMethod = false;
    }
  }

  isFormReadyForUpdateInv() {
    return (
      this.invoiceDetailsForm.id !== null &&
      this.invoiceDetailsForm.companyName !== '' &&
      this.invoiceDetailsForm.companyEmail !== '' &&
      this.invoiceDetailsForm.financialService !== '' &&
      this.invoiceDetailsForm.userId !== null &&
      this.invoiceDetailsForm.vatNumber !== 0 &&
      this.invoiceDetailsForm.street !== '' &&
      this.invoiceDetailsForm.city !== '' &&
      this.invoiceDetailsForm.phone !== ''
    );
  }

  isPurchaceReady() {
    return (
      this.invoiceDetailsAsInDb.id !== null &&
      this.invoiceDetailsAsInDb.id === this.invoiceDetailsForm.id &&
      this.invoiceDetailsAsInDb.companyName !== '' &&
      this.invoiceDetailsAsInDb.companyName ===
        this.invoiceDetailsForm.companyName &&
      this.invoiceDetailsAsInDb.companyEmail !== '' &&
      this.invoiceDetailsAsInDb.companyEmail ===
        this.invoiceDetailsForm.companyEmail &&
      this.invoiceDetailsAsInDb.financialService !== '' &&
      this.invoiceDetailsAsInDb.financialService ===
        this.invoiceDetailsForm.financialService &&
      this.invoiceDetailsAsInDb.userId !== null &&
      this.invoiceDetailsAsInDb.userId === this.invoiceDetailsForm.userId &&
      this.invoiceDetailsAsInDb.vatNumber !== 0 &&
      this.invoiceDetailsAsInDb.vatNumber ===
        this.invoiceDetailsForm.vatNumber &&
      this.invoiceDetailsAsInDb.street !== '' &&
      this.invoiceDetailsAsInDb.street === this.invoiceDetailsForm.street &&
      this.invoiceDetailsAsInDb.city !== '' &&
      this.invoiceDetailsAsInDb.city === this.invoiceDetailsForm.city &&
      this.invoiceDetailsAsInDb.phone !== '' &&
      this.invoiceDetailsAsInDb.phone === this.invoiceDetailsForm.phone &&
      !this.editMode
    );
  }

  choosePurchace(value: any) {
    if (value.target.value) {
      this.purchaceMethod = value.target.value;
      switch (this.purchaceMethod) {
        case 'paypal':
          this.showPaypal = true;
          this.showBankTransfer = false;
          break;
        case 'bankTransfer':
          this.showBankTransfer = true;
          this.showPaypal = false;
      }
    }
  }

  updateInvoiceDetails() {
    this.invoiceService
      .editInvoiceDetails(this.invoiceDetailsForm)
      .subscribe((data: IInvoiceDetails) => {
        this.invoiceDetails = data;
        this.editInvoiceDetails();
        this.cancelEdit();
      });
  }

  submitInvoiceForm() {
    if (this.isFormReadyForUpdateInv()) {
      this.updateInvoiceDetails();
    }
  }

  onBankTransfer() {
    this.transaction = {
      id: null,
      date: new Date(),
      type: 'BANK_TRANSFER',
      paypalId: '',
      paid: false,
      sub_name: this.choosenSubscription.name,
      sub_price: this.choosenSubscription.price.toString(),
      sub_duration: this.choosenSubscription.duration,
      sub_role: this.choosenSubscription.role,
      userId: this.loggedUser.id,
      advForEver: this.choosenSubscription.advForEver,
    };
    this.sendTransaction();
  }

  sendTransaction() {
    this.transactionService.createTransaction(this.transaction).subscribe(
      (data) => {
        this.router.navigate(['/successful_transaction']);
      },
      (error) => null
    );
  }
}
