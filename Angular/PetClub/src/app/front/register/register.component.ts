import { HttpClient, HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ICity, IUser } from 'src/app/_models/models';
import { AuthService } from 'src/app/_services/auth.service';
import { CategoriesService } from 'src/app/_services/categories/categories.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { DialogLayoutDisplay,ToastNotificationInitializer } from '@costlydeveloper/ngx-awesome-popup';
import { Observable } from 'rxjs';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  rememberMe: boolean;
  selectedFiles?: FileList;
  currentFile?: File;
  progress = 0;
  message = '';
  jwtToken:any;
  fileInfos?: Observable<any>;
  
  user : IUser;
  confirmPass: string;
  cities:ICity[]; 
 

  errorMsg:string="Login Faild Check Your Username And Password";
  

  routerPath:string;

  loginForm: any = {
    username: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  loginErrorMessage = '';
  role: string = '';

  registerForm: any = {
    firstName:null,
    lastName:null,
    username: null,
    email: null,
    password: null,
    phone:null,
    img:null,
    city:"",
    street:null
  };
  isSuccessful = false;
  isSignUpFailed = false;
  registerErrorMessage = '';

  imgUrl="../../../../assets/images/pet_icon.png"

  constructor(
    private userService: UserService,
    private authService: AuthService, 
    private tokenStorage: TokenStorageService, 
    private router:Router,
    private categoryService: CategoriesService,
    private formBuilder:FormBuilder) {
    this.routerPath = router.url;
    }

  ngOnInit(): void {

    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.role = this.tokenStorage.getUser().role;
    }
    this.getAllCities();
    this.tokenStorage.autoLogin();
    if(this.isLoggedIn){
      this.router.navigate(['home']);
    }
  }


 


  toastNotification() {
    const newToastNotification = new ToastNotificationInitializer();
    newToastNotification.setTitle('Warning!');
    newToastNotification.setMessage(this.errorMsg);

    // Choose layout color type
    newToastNotification.setConfig({
        LayoutType: DialogLayoutDisplay.DANGER // SUCCESS | INFO | NONE | DANGER | WARNING
    });

    // Simply open the toast
    newToastNotification.openToastNotification$();
}


  selectFile(event:any):void{
    if(event.target.files){
      let reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      this.selectedFiles=event.target.files;
      reader.onload = (e:any) => {
        this.imgUrl = e.target.result;
      }
    }
  }

  getAllCities():void{
    this.categoryService.getAllCities().subscribe((actualCities: ICity[]) => {
      this.cities = actualCities;
    });
  }

  submitLogin():void{
    const { username, password } = this.loginForm;

    this.authService.login(username, password).subscribe(
      (data: HttpResponse<any>) => {
        this.jwtToken = data.headers.get('Jwt-Token');
        if (this.jwtToken) {
          this.tokenStorage.saveToken(this.jwtToken);
          localStorage.setItem('auth-token',this.jwtToken)
        }
        this.tokenStorage.saveUser(data.body);
        
        localStorage.setItem('auth-user',JSON.stringify(data.body));
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.role = this.tokenStorage.getUser().role;
        this.router.navigate(['home']);
        if(this.rememberMe) {
          localStorage.setItem('rememberMe', 'yes');         
          if (this.jwtToken) {
            localStorage.setItem("auth-token",this.jwtToken);          
          }
        }
      },
      err => {
       
         this.loginErrorMessage = err.error.message;
         this.toastNotification();
        this.isLoginFailed = true;
      }
    );
  }

  submitRegister():void{
    const { firstName, lastName, username, email, password,phone,img,city,street} =this.registerForm;
    this.authService.register(firstName, lastName, username, email, password,phone,img,city,street).subscribe(
      data => {
        this.user=data.body;
      
      
        if(this.selectedFiles){this.upload();
        }
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.router.navigate(['login']);
      },
      
      err => {
       
        this.registerErrorMessage = err.error.message;
        this.toastNotification();
        this.isSignUpFailed = true;
      }
    );
   
    if(this.selectedFiles){this.upload();}
  }




  upload(): void {
    this.progress = 0;
 
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles[0];
      if (file) {
        
        this.userService.upload(file,this.user.username,this.user.id).subscribe(
          (event: any) => {
            if (event.type === HttpEventType.UploadProgress) {
              this.progress = Math.round(100 * event.loaded / event.total);
            } else if (event instanceof HttpResponse) {
              this.message = event.body.message;
              this.fileInfos = this.userService.getFiles(this.user.username ,this.user.id);
            }
          },
          (err: any) => {
            this.progress = 0;

            if (err.error && err.error.message) {
              this.message = err.error.message;
            } else {
              this.message = 'Could not upload the file!';
            }

            this.currentFile = undefined;
          });

      }

      this.selectedFiles = undefined;
    }
}
  reloadPage(): void {
    window.location.reload();
  }  
}

