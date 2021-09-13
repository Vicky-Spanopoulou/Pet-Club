import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.component.html',
  styleUrls: ['./about-us.component.css','../../../assets/css/default.css']
})
export class AboutUsComponent implements OnInit {

  constructor(private tokenStorage:TokenStorageService) { 
    this.tokenStorage.autoLogin();
  }

  ngOnInit(): void {
  }

}
