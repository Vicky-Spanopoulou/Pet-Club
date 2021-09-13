import { Component, Input, OnInit, Output, ViewChild } from '@angular/core';
import { PetService } from 'src/app/_services/pet/pet.service';
import { UserService } from 'src/app/_services/user.service';
import { PetFormComponent } from '../../forms/pet-form/pet-form.component';

@Component({
  selector: 'app-my-pets',
  templateUrl: './my-pets.component.html',
  styleUrls: ['./my-pets.component.css', '../../../../assets/css/default.css']
})
export class MyPetsComponent implements OnInit {

  @Input() pets:any[];

  @ViewChild('petForm', {static:false})
  petForm:PetFormComponent;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    
  }

  openModal(){
    this.petForm.popModal();
  }

  getAllUsersPets(): void {
    
  }

}
