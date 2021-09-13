import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { IAd } from 'src/app/_models/models';
import { AdFormComponent } from '../../forms/ad-form/ad-form.component';

@Component({
  selector: 'app-my-ads',
  templateUrl: './my-ads.component.html',
  styleUrls: ['./my-ads.component.css', '../../../../assets/css/default.css', '../../../../assets/css/ribbon.css']
})
export class MyAdsComponent implements OnInit {

  @Input() ads:IAd[];

  @ViewChild('adForm', {static:false})
  adForm:AdFormComponent;

  constructor() { }

  ngOnInit(): void {
  }


  
  openModal(){
    this.adForm.popModal();
  }

}
