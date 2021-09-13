import { StringMap } from '@angular/compiler/src/compiler_facade_interface';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { IPet, IUser } from 'src/app/_models/models';
import { PetService } from 'src/app/_services/pet/pet.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';
import { DashPetHistoryComponent } from './dash-pet-history/dash-pet-history.component';

@Component({
  selector: 'app-doctor-dash-clients',
  templateUrl: './doctor-dash-clients.component.html',
  styleUrls: ['./doctor-dash-clients.component.css',
              '../dash-users/dash-users.component.css',
              '../../../assets/css/dashboardCss/Defaultdashboard.css']
})
export class DoctorDashClientsComponent implements OnInit {

  petHistoryForm:boolean = false;
  petForExam:boolean=false;
  searchClient:String;
  editPet:any;
  pets:IPet[] =[];
  error:any={isError:false,errorMessage:''};
  isValidDate:any;
  doctor:IUser;
  owner:any;
  clients:any[];
  filterClients:any[];
  findNewClient:string;
  newClient:any;
  newPets:any[];

  @ViewChild('examForm',{static:false})
  examFormEdit:DashPetHistoryComponent;


  petForm:any={
    id:null,
    name:null,
    behavior: null,
    examinations:null,
    image:null
};
  

  filterPets:IPet[] = this.pets;
  constructor(private petService: PetService,
              private tokenSTorageService:TokenStorageService,
              private router:Router,
              private userService:UserService) {
                this.tokenSTorageService.autoLogin();
    this.doctor = tokenSTorageService.getUser();
      if(this.doctor.role !== 'ROLE_DOCTOR'){
        this.router.navigate(['home']);
      }
   }

  ngOnInit(): void {
    this.getClients();
  }
  fillePet(pet:any){
    this.petHistoryForm = true;
    this.petForm.id = pet.id;
    this.petForm.name = pet.name;
    this.petForm.behavior = pet.behavior;
  }

  handleUsers() {
    //filtering
    this.filterClients = JSON.parse(JSON.stringify(this.clients));
    if (this.searchClient) {
      this.filterClients = this.filterClients.filter(
        (x) =>
          x.email.toUpperCase().includes(this.searchClient.toUpperCase())
      );
    }
  }


  getClients(){
    this.userService.getUserClientsByUserId(this.doctor.id).subscribe((actualyClients:IUser[]) =>{
      this.clients = actualyClients;
      this.filterClients = actualyClients;
    });
  }

  toggleSearchCustomer(){
    let searchCustomer = document.getElementById('search-owner');
    let addCustomer = document.getElementById('add-customer');
    searchCustomer?.classList.toggle('hidden');
    addCustomer?.classList.toggle('hidden');
  }
  setEditPet(pet:any){
    this.petForExam = false; 
    this.petHistoryForm = true;
    this.petForm.id = pet.id;
    this.petForm.name = pet.name;
    this.petForm.breed = pet.breed;
    this.petForm.behavior = pet.behavior;
    this.petForm.image = pet.image;
    this.petForm.ownerId = this.doctor.id;


    if(this.examFormEdit){
      this.examFormEdit.loadExams();
    }
    

  }

  getAllUsersByEmail(email:any){
    this.petForExam = true;
    this.userService.getUserByUserEmail(email).subscribe((actualyUser:IUser) =>{
      this.newClient = actualyUser;
      this.newPets = actualyUser.pets;
    });
  }
  closeNewPetList(){
    this.petForExam = false;
  }
  onCloseForm(){
    this.petHistoryForm = false;
  }


  updateTable() {
    this.getClients();
    this.petHistoryForm = false;
  }
 
}
