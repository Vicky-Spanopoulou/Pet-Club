import { Component, OnInit } from '@angular/core';
import { BusinessService } from 'src/app/_services/business/business.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-vertical-nav',
  templateUrl: './vertical-nav.component.html',
  styleUrls: ['./vertical-nav.component.css',
              '../../../assets/css/dashboardCss/Defaultdashboard.css']
})
export class VerticalNavComponent implements OnInit {

  unchecked:any;
  user:any;
  constructor(private businessService:BusinessService, private tokenSTorageService:TokenStorageService) {
    this.user = tokenSTorageService.getUser();
   }

  ngOnInit(): void {
    this.getTotalUncheckedBusiness();
  }


  getTotalUncheckedBusiness(){
    this.businessService.getTotalUncheckedBusiness().subscribe((actualyUnchecked:any) => {
      this.unchecked = actualyUnchecked;

    })
  }
}
