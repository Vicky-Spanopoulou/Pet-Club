import { Component, OnInit } from '@angular/core';
import { ICity, IUser } from 'src/app/_models/models';
import { CategoriesService } from 'src/app/_services/categories/categories.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';
import { Observable } from 'rxjs';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-properties',
  templateUrl: './user-properties.component.html',
  styleUrls: [
    './user-properties.component.css',
    '../../../../assets/css/default.css',
  ],
})
export class UserPropertiesComponent implements OnInit {
  editForm = 'disabled-input';

  enableSaveChanges = 'display-block';

  cancelChanges = 'display-none';

  isSuccessful: boolean = true;

  loggedUser: IUser;

  imgUrl:string;
  selectedFiles?: FileList;
  progress = 0;
  message = '';
  fileInfos?: Observable<any>;
  
  cities: ICity[];

  userForm: any = {
    id: null,
    username: '',
    email: '',
    firstName: '',
    lastName: '',
    street: '',
    city: '',
    phone: null,
    address: null
  };

  user: IUser;

  constructor(
    private tokenStorageService: TokenStorageService,
    private cityService: CategoriesService,
    private userService: UserService,
    private router:Router
  ) {
    this.tokenStorageService.autoLogin();
    this.loggedUser = this.tokenStorageService.getUser();
    if(!this.loggedUser){
      this.router.navigate(['home']);
    }
  }

  ngOnInit(): void {
    this.setUserForm();
    this.getAllCities();
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
  }

  getAllCities(): void {
    this.cityService.getAllCities()
    .subscribe((actualCities: ICity[]) => {
      this.cities = actualCities;
    });
  }

  setUserForm(): void {
    this.cancelFormEdit();
    this.imgUrl = this.loggedUser.img;
  }

  cancelFormEdit():void{
    this.userForm.id = this.loggedUser.id;
    this.userForm.firstName = this.loggedUser.firstName;
    this.userForm.lastName = this.loggedUser.lastName;
    this.userForm.street = this.loggedUser.address.street;
    this.userForm.city = this.loggedUser.address.city.name;
    this.userForm.phone = this.loggedUser.phone;
    this.userForm.img = this.loggedUser.img;
  }

  canselEdit(): void {
    this.cancelFormEdit();
    this.toggleEditForm();
  }

  submitUserPropertiesForm() {

    this.userForm.id = this.loggedUser.id;
    this.userForm.address = this.loggedUser.address.id;
    this.userForm.username = this.loggedUser.username;
    this.userForm.email = this.loggedUser.email;

    this.userForm.isNotLocked = this.loggedUser.notLocked;
    this.userForm.verified = this.loggedUser.verified;
    this.userForm.role = this.loggedUser.role;
    this.userForm.active = true;
    this.userForm.sub_until = this.loggedUser.sub_until;
    this.userForm.advForEver = this.loggedUser.advForEver;

      // update existing entry
      this.userService.editUserDetails(this.userForm)
      .subscribe(
        (data) => {
          this.getUser();
        },
        (error) => null
      );
      this.toggleEditForm();
  }

  selectFile(event: any): void {
    if (event.target.files) {
      let reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      this.selectedFiles=event.target.files;
      reader.onload = (e: any) => {
        this.imgUrl = e.target.result;
      };
    }
    this.upload();
  }

  upload():void{
    this.progress=0;
    if(this.selectedFiles){
      const file:File|null =this.selectedFiles[0];
      if(file){
        this.userService.upload(file,this.loggedUser.username,this.loggedUser.id).subscribe(
          (event:any)=>{
            if(event.type=== HttpEventType.UploadProgress){
              this.progress = Math.round(100 * event.loaded / event.total);
            }else if (event instanceof HttpResponse){
              this.message = event.body.message;
              this.fileInfos = this.userService.getFiles(this.loggedUser.username,this.loggedUser.id);
            }
          },
          (err:any)=>{
            this.progress=0;
            if (err.error && err.error.message) {
              this.message = err.error.message;
            } else {
              this.message = 'Could not upload the file!';
            }
          });
      }
    }
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
