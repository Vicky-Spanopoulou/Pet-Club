import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IUser } from 'src/app/_models/models';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-terms-policy',
  templateUrl: './terms-policy.component.html',
  styleUrls: [
    './terms-policy.component.css',
    '../../../assets/css/default.css',
  ],
})
export class TermsPolicyComponent implements OnInit {
  loggedInUser: IUser;
  constructor(
    private tokenStorage: TokenStorageService,
    private router: Router
  ) {
    this.tokenStorage.autoLogin();
    this.loggedInUser = this.tokenStorage.getUser();
  }

  ngOnInit(): void {}
}
