import {
  HttpClient,
  HttpErrorResponse,
  HttpEvent,
  HttpEventType,
  HttpResponse,
} from '@angular/common/http';
import { stringify } from '@angular/compiler/src/util';
import {
  Component,
  ElementRef,
  HostListener,
  OnInit,
  ViewChild,
} from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { IAd, IReminder, IUser } from 'src/app/_models/models';
import { AdsService } from 'src/app/_services/ads/ads.service';
import { FileUploadService } from 'src/app/_services/file-image/upload-image.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: [
    './user-profile.component.css',
    '../../../assets/css/profile.css',
    '../../../assets/css/default.css',
    '../../../assets/css/containers.css',
  ],
})
export class UserProfileComponent implements OnInit {
  //Which container is going to be visible
  container_shown = 'myPets';
  //if the #nav-list is going to be visible
  navList = true;
  // public innerWidth: any;
  public innerWidth: any;

  role: string;
  user: IUser;
  ads!: any[];
  pets: any[];

  // reminders:IReminder[];
  reminders: IReminder[];

  // = [{
  //   id:1,
  //   info:"Prepei na to thimitho",
  //   done:true
  // },{
  //   id:2,
  //   info:"Prepei na to thimitho",
  //   done:false
  // }]

  constructor(
    private http: HttpClient,
    private userService: UserService,
    private el: ElementRef,
    private tokenStorage: TokenStorageService,
    private myAds: AdsService,
  ) {
    this.user = this.tokenStorage.getUser();
    this.getUser();
  }

  ngOnInit(): void {
    this.upload();
    
    this.role = this.user.role;
    this.pets = this.user.pets;
    this.ads = this.user.ads;
    this.reminders = this.user.reminders;
    this.innerWidth = window.innerWidth;
    this.fileInfos = this.userService.getFiles(
      this.user.username,
      this.user.id
    );
  }

  onChange(): void {}

  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    this.innerWidth = window.innerWidth;
    if (this.innerWidth < 660) {
      this.navList = false;
    } else {
      this.navList = true;
    }
  }

  setContainer(container: string, event: any): void {
    let elem = document.querySelectorAll('.selected');
    elem.forEach((element) => {
      element.classList.remove('selected');
    });

    event.target.classList.add('selected');
    this.container_shown = container;
  }

  toggleNavList() {
    if (this.innerWidth < 660) {
      this.navList = !this.navList;
    }
  }

  //Profile image
  selectedFiles?: FileList;
  currentFile?: File;
  progress = 0;
  message = '';

  fileInfos?: Observable<any>;

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
    this.upload();
    location.reload();
  }

  upload(): void {
    this.progress = 0;

    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);

      if (file) {
        this.currentFile = file;

        this.userService
          .upload(this.currentFile, this.user.username, this.user.id)
          .subscribe(
            (event: any) => {
              if (event.type === HttpEventType.UploadProgress) {
                this.progress = Math.round((100 * event.loaded) / event.total);
              } else if (event instanceof HttpResponse) {
                this.message = event.body.message;
                this.fileInfos = this.userService.getFiles(
                  this.user.username,
                  this.user.id
                );
              }
            },
            (err: any) => {
              console.log(err);
              this.progress = 0;

              if (err.error && err.error.message) {
                this.message = err.error.message;
              } else {
                this.message = 'Could not upload the file!';
              }

              this.currentFile = undefined;
            }
          );
      }

      this.selectedFiles = undefined;
    }
  }

  getUser(): void {
    this.userService
      .getUserByUsername(this.user.username)
      .subscribe((event: any) => {
        this.user = event;
        this.tokenStorage.saveUser(event);
      });
  }
}
