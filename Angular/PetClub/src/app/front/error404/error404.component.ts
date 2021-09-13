import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-error404',
  templateUrl: './error404.component.html',
  styleUrls: ['./error404.component.css','../../../assets/css/default.css']
})
export class Error404Component implements OnInit {

  constructor(private tokenStorage:TokenStorageService) { 
    this.tokenStorage.autoLogin();
  }

  ngOnInit(): void {
  }

}
