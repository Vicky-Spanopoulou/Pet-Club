import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, Input, OnInit, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { Observable } from 'rxjs';
import { ICity, IInvoiceDetails, IUser } from 'src/app/_models/models';
import { CategoriesService } from 'src/app/_services/categories/categories.service';
import { InvoiceDetailsService } from 'src/app/_services/invoice-details/invoice-details.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-dash-user-form',
  templateUrl: './dash-user-form.component.html',
  styleUrls: [
    './dash-user-form.component.css',
    '../../../../assets/css/dashboardCss/DashboardForms.css',
  ],
})
export class DashUserFormComponent implements OnInit,OnChanges {
  roleOp: string = '';
  roleOption: any[];
  showInvoice: boolean = false;
  editedUser: any;
  cityCategories: any[];
  isSuccessful: boolean = false;
  selectedFiles?: FileList;
  progress = 0;
  message = '';
  fileInfos?: Observable<any>;

  @Input() visibleForm = true;

  @Input() user: any;
  @Input() dtoUser: any;

  @Input() imgUrl:string;
  passForm:any = {
    username:null,
    newPassword:null
  }

  editInvoiceDetails: any = {
    id: null,
    street: null,
    city: null,
    companyName: null,
    vatNumber: null,
    financialService: null,
    phoneDoctor: null,
    email: null,
  };

  @Input() dtoInvoiceDetails: any = {
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

  @Output() notify = new EventEmitter<any>();

  constructor(
    private serviceCategories: CategoriesService,
    private userService: UserService,
    private inoiceService: InvoiceDetailsService
  ) {}
  ngOnChanges() {
  
  }

  ngOnInit(): void {
    this.getAllCityCategories();
    this.getAllRoles();
    if(!this.dtoInvoiceDetails){
      this.showInvoice = false;
    }else{
      this.showInvoice = true;
    }
  }



  getAllCityCategories() {
    this.serviceCategories.getAllCities().subscribe((acualyCities: any[]) => {
      this.cityCategories = acualyCities.sort((a, b) => (a.name > b.name)?1:-1);
    });
  }

  getAllRoles() {
    this.serviceCategories.getAllRoles().subscribe((aculyRoles: any[]) => {
      this.roleOption = aculyRoles;
    });
  }



  submitUserForm(): void {
    if (this.dtoUser.id === null) {
      this.userService.addNewUser(this.dtoUser).subscribe(
        (user: IUser) => {
          this.dtoUser.id = user.id;
          if (this.selectedFiles) {
            this.upload();
          }
          this.notify.emit();
        },
        (error) => null
      );
      window.scrollTo(0, 0);
    } else {
      this.userService.editUserDetails(this.dtoUser).subscribe(
        (data) => {
        },
        (error) => null
      );
      this.notify.emit();
      this.upload();
      window.scrollTo(0, 0);
    }
  }

  submitInvoiceUserForm() {
    this.editInvoiceDetails.id = this.dtoInvoiceDetails.id;
    this.editInvoiceDetails.street = this.dtoInvoiceDetails.address.street;
    this.editInvoiceDetails.city = this.dtoInvoiceDetails.address.city.name;
    this.editInvoiceDetails.companyName = this.dtoInvoiceDetails.companyName;
    this.editInvoiceDetails.companyEmail = this.dtoInvoiceDetails.companyEmail;
    this.editInvoiceDetails.vatNumber = this.dtoInvoiceDetails.vatNumber;
    this.editInvoiceDetails.financialService = this.dtoInvoiceDetails.financialService;
    this.editInvoiceDetails.phone = this.dtoInvoiceDetails.phone;
    this.inoiceService.editInvoiceDetails(this.editInvoiceDetails).subscribe(
      (data) => {
        this.notify.emit();
      },
      (error) => null
    );
    this.visibleForm = false; 
    window.scrollTo(0, 0);

  }

  deleteUser(): void {
    let answer = confirm('Are you sure change paid state on this transtaction?');
    if (answer && this.dtoUser.id != null) {
      this.userService.deleteUserById(this.dtoUser.id).subscribe((deletedUser) => {
      });
      this.notify.emit();
    }
    window.scrollTo(0, 0);
  }

  selectFile(event: any): void {
    if (event.target.files) {
      let reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      this.selectedFiles = event.target.files;
      reader.onload = (e: any) => {
        this.imgUrl = e.target.result;
      };
    }
  }

  upload(): void {
    this.progress = 0;
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles[0];
      if (file) {
        this.userService
          .upload(file, this.dtoUser.username, this.dtoUser.id)
          .subscribe(
            (event: any) => {
              if (event.type === HttpEventType.UploadProgress) {
                this.progress = Math.round(100 * event.loaded / event.total);
              } else if (event instanceof HttpResponse) {
                this.message = event.body.message;
                this.fileInfos = this.userService.getFiles(
                  this.dtoUser.username,
                  this.dtoUser.id
                );
              }
            },
            (err: any) => {
              this.progress = 0;
              if (err.error && err.error.message) {
                this.message = err.error.message;
              } else {
                this.message = 'Could not upload the file!';
              }
            }
          );
      }
    }
  }



  submitPassword(){
    this.passForm.username = this.dtoUser.username;
    this.userService.updatePasswordAdmin(this.passForm.username,this.passForm.newPassword).subscribe(
      (data) => {
        this.notify.emit();
      },
      (error) => null
    );
  }
}