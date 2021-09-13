import { Component, Input, OnInit } from '@angular/core';
import { IPet, IPetCategory, IUser } from 'src/app/_models/models';
import { PetService } from 'src/app/_services/pet/pet.service';
import { UserService } from 'src/app/_services/user.service';
import { toHTML, toDoc } from 'ngx-editor'; 
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-dash-pets',
  templateUrl: './dash-pets.component.html',
  styleUrls: [
    './dash-pets.component.css',
    '../dash-users/dash-users.component.css',
    '../../../assets/css/dashboardCss/Defaultdashboard.css',
  ],
})
export class DashPetsComponent implements OnInit {
  editedPet: boolean = false;

  selectedPet: any;

  searchPets: string;

  pets: IPet[] = [];

  filterPets: IPet[] = [];

  owners: IUser[];
  filterOnwers: IUser[];

  loggedInUser:IUser;

  petOwner: any;

  petForm: any = {
    id: null,
    name: null,
    breed: null,
    dob: null,
    chipNumber: null,
    color: null,
    behavior: null,
    sex: null,
    image: null,
    petCategory: null,
    user: null,
    username: null
  };

  constructor(
    private petService: PetService,
    private userService: UserService,
    private tokenSTorageService: TokenStorageService,
    private router: Router
  ) {
    this.tokenSTorageService.autoLogin();
    this.loggedInUser = tokenSTorageService.getUser();
    if(this.loggedInUser.role !== 'ROLE_ADMIN'){
      this.router.navigate(['home']);
    }
  }

  ngOnInit(): void {
    this.getAllUsers();
  }

  ngOnChanges(): void {
    this.updateTable();
  }

  setEditPet(pet: any, owner: any) {
    this.editedPet = true;
    this.selectedPet = pet;
    (this.petForm.id = pet.id),
      (this.petForm.name = pet.name),
      (this.petForm.breed = pet.breed),
      (this.petForm.dob = pet.dob),
      (this.petForm.chipNumber = pet.chipNumber),
      (this.petForm.color = pet.color),
      (this.petForm.behavior = toDoc(pet.behavior)),
      (this.petForm.sex = pet.sex),
      (this.petForm.image = pet.image),
      (this.petForm.user = pet.user),
      (this.petForm.petCategory = pet.petCategory),
      (this.petForm.user = owner.id),
      (this.petForm.username = owner.username);
  }

  handlePets() {   
    //filter by name
    this.filterOnwers = JSON.parse(JSON.stringify(this.owners));

      this.filterOnwers = this.filterOnwers.filter(
        x =>{
          if(x.email.toUpperCase().includes(this.searchPets.toUpperCase()) || 
          this.ownerHasPetWithName(x,this.searchPets) ){
            return x;
          }
          return null;
        }
      );
 

  }

  ownerHasPetWithName(user:IUser,name:string):boolean{

    let result:boolean = false;
   user.pets.forEach((x) => {
        if(x.name.toUpperCase() === name.toUpperCase() ){
          result = true;
        }
   })
    return result;
  }

  addPet() {
    this.editedPet = true;
    this.petForm = {
      id: null,
      name: null,
      breed: null,
      dob: null,
      chipNumber: null,
      color: null,
      behavior: null,
      sex: null,
      image: null,
      petCategory: null,
      user: null,
      username: null
    };
  }

  onCloseForm() {
    this.editedPet = false;
  }

  getAllUsers() {
    this.userService.getAllUsers().subscribe((actualUsers: IUser[]) => {
      this.owners = actualUsers;
      this.filterOnwers = actualUsers;
      for (const owner of this.owners) {
        for (const pet of owner.pets) {
            this.pets.push(pet);
        }
      }
    });
  }

  updateTable() {
    this.getAllUsers();
    this.editedPet = false;
  }

}
