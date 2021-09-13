import {
  Component,
  Input,
  OnChanges,
  OnInit,
  SimpleChanges,
  ViewChild,
} from '@angular/core';
import { Router } from '@angular/router';
import { IInvoiceDetails, IUser } from 'src/app/_models/models';
import { InvoiceDetailsService } from 'src/app/_services/invoice-details/invoice-details.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-dash-users',
  templateUrl: './dash-users.component.html',
  styleUrls: [
    './dash-users.component.css',
    '../../../assets/css/dashboardCss/Defaultdashboard.css',
    '../../../assets/css/dashboardCss/DashboardForms.css',
  ],
  providers: [UserService],
})
export class DashUsersComponent implements OnInit, OnChanges {
  searchUser: string;
  editedUser: any;
  showInvoice:boolean = false;
  userImage:string = '../../../../assets/images/defaultProfileImage.jpg';
  userForm: any = {
    id: null,
    firstName: null,
    lastName: null,
    username: null,
    email: null,
    password: null,
    phone: null,
    img: null,
    address: null,
    city: null,
    street: null,
    verified: null,
    isNotLocked: null,
    advForEver: null,
    sub_until: null,
    invoiceId:null,
    active:true,
    role: null,
  };

  invoiceForm: any = {
    id: null,
    address: {
      street: null,
      city: {
        name: null,
      },
    },
    companyName: null,
    vatNumber: null,
    financialService: null,
    phoneDoctor: null,
    email: null,
  };

  visibleForm: boolean = false;
  editForm: any;
  invoiceUserDetalils: any;
  users: IUser[];
  filterUsers: IUser[];
  loggedInUser: IUser;

  constructor(
    private userService: UserService,
    private serviceInvoice: InvoiceDetailsService,
    private tokenSTorageService: TokenStorageService,
    private router: Router
  ) {
    this.tokenSTorageService.autoLogin();
    this.loggedInUser = tokenSTorageService.getUser();
    if(this.loggedInUser.role !== 'ROLE_ADMIN'){
      this.router.navigate(['home']);
    }
  }

  ngOnChanges(): void {
    this.updateTable();
    if(this.userForm != null){
      this.showInvoice = true;
    }else{
      this.showInvoice = false;
    }
  }

  ngOnInit(): void {
    this.getAllUsers();
  }

  addUser() {
    this.visibleForm = true;
    this.userImage = '../../../../assets/images/defaultProfileImage.jpg';
    this.userForm = {
      id: null,
      firstName: null,
      lastName: null,
      username: null,
      email: null,
      password: null,
      phone: null,
      img: null,
      address: null,
      city: null,
      street: null,
      verified: null,
      isNotLocked: null,
      advForEver: null,
      sub_until: null,
      invoiceId:null,
      role: null,
    };
    this.invoiceForm = {
      id: null,
      address: {
        street: null,
        city: {
          name: null,
        },
      },
      companyName: null,
      vatNumber: null,
      financialService: null,
      phoneDoctor: null,
      email: null,
    };
  }

  fillUser(user: IUser,invoiceId:number) {
    this.visibleForm = true;
    this.userForm.id = user.id;
    this.userForm.username = user.username;
    this.userForm.firstName = user.firstName;
    this.userForm.lastName = user.lastName;
    this.userForm.password = user.password;
    this.userForm.img = user.img;
    this.userImage = user.img;
    this.userForm.address = user.address.id;
    this.userForm.street = user.address.street;
    this.userForm.city = user.address.city.name;
    this.userForm.email = user.email;
    this.userForm.verified = user.verified;
    this.userForm.isNotLocked = user.notLocked;
    this.userForm.advForEver = user.advForEver; 
    this.userForm.active = user.active; 
    this.userForm.sub_until = user.sub_until;
    this.userForm.invoiceId = user.invoiceDetails.id;
    this.userForm.phone = user.phone;
    this.userForm.role = user.role;
    if(invoiceId !== null){
      this.getInvoiceById(invoiceId);
    }
  }

  getInvoiceById(invoiceId:number) {
    this.serviceInvoice
      .getInvoiceDetailsById(invoiceId)
      .subscribe((actualyInvoiceDetails: IInvoiceDetails) => {
        this.invoiceForm = actualyInvoiceDetails;
      });
  }
  handleUsers() {
    //filtering
    this.filterUsers = JSON.parse(JSON.stringify(this.users));
    if (this.searchUser) {
      this.filterUsers = this.filterUsers.filter(
        (x) =>
          x.email.toUpperCase().includes(this.searchUser.toUpperCase()) ||
          x.firstName.toUpperCase().includes(this.searchUser.toUpperCase()) ||
          x.lastName.toUpperCase().includes(this.searchUser.toUpperCase())
      );
    }
  }

  getAllUsers() {
    this.userService.getAllUsers().subscribe((actualUsers: IUser[]) => {
      this.users = actualUsers;
      this.filterUsers = this.users;
    });
  }

  onCloseForm() {
    this.visibleForm = false;
  }

  updateTable() {
    this.getAllUsers();
    this.visibleForm = false;
  }



}
