import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IUser } from 'src/app/_models/models';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-success-page',
  templateUrl: './success-page.component.html',
  styleUrls: ['./success-page.component.css'],
})
export class SuccessPageComponent implements OnInit {

  loggedInUser:IUser;
  constructor(
    private tokenStorage: TokenStorageService,
    private router: Router
  ) {
    this.tokenStorage.autoLogin();
    this.loggedInUser = this.tokenStorage.getUser();
  }

  ngOnInit(): void {}
}
