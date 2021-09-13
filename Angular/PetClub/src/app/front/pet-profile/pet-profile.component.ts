import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { IExamRecord, IPet, IUser } from 'src/app/_models/models';
import { CategoriesService } from 'src/app/_services/categories/categories.service';
import { PetService } from 'src/app/_services/pet/pet.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { PetFormComponent } from '../forms/pet-form/pet-form.component';


@Component({
  selector: 'app-pet-profile',
  templateUrl: './pet-profile.component.html',
  styleUrls: ['./pet-profile.component.css', 
  '../../../assets/css/default.css', 
  '../../../assets/css/profile.css', 
  '../../../assets/css/containers.css']
})
export class PetProfileComponent implements OnInit, OnDestroy {
  private routeSub: Subscription;
  user:IUser;
  userIsTheOwner:boolean = false;
  exams:IExamRecord[];
  pet:IPet={
    id:0,
    name:'',
    breed:"",
    dob:new Date(),
    chipNumber:0,
    color: "",
    behavior: "",
    sex: '',
    image: "",
    petCategory:{
      id:0,
      name:''
    },
    examinations:[]
}

imgUrl="../../../../../assets/images/pet_icon.png"

@ViewChild('petForm',{static:false})
petForm:PetFormComponent;


  examinationCategories:string[]=['Vacinations','Vermilugation', 'Other Treatments', 'Diagnostic Tests'];
  
  examinationCategory:string='All';

  showEditMenu:boolean=false;

  constructor(
    private router:Router,
    private route: ActivatedRoute,
    private categoriesService: CategoriesService,
    private petService: PetService,
    private tokenStorageService: TokenStorageService
    ) 
    {
      this.tokenStorageService.autoLogin();
     }

  ngOnInit(): void {
    this.user=this.tokenStorageService.getUser();
    this.routeSub = this.route.params.subscribe(params => {
      this.getPet(params['id']);

      this.getExamCategories();
    });
  }



  toggleMenu():void{
    this.showEditMenu = !this.showEditMenu;
    this.petForm.fillThePetForEdit
  }

  editPet():void{
    this.toggleMenu();
    this.petForm.popModal();
    this.petForm.externaIPet = this.pet;
    this.petForm.fillThePetForEdit();
  }

  getExamCategories():void{
    this.categoriesService.getAllExamCategories().subscribe(
      (data:any[]) => 
      {
        this.examinationCategories = data.sort((a, b) => (a > b)?1:-1);
      });
  }

  getPet(id:number):void{
    this.petService.getPetById(id).subscribe(
      (data:IPet) => {
        this.pet=data;
        this.imgUrl = data.image;
        
        this.user.pets.forEach(pet => {
          pet.id == this.pet.id?this.userIsTheOwner = true: false
          
        });
        if(!this.userIsTheOwner){
          this.router.navigate(['error']);
        }
      },
      (error)=>{ this.router.navigate(['error']);}
    );
  }

  deletePet():void{
    let answer = confirm('Are you sure that you want to delete this pet?');
    if(answer){
      this.petService.deletePetById(this.pet.id).subscribe(
        (data)=> this.router.navigate(['home']),
        (error) => null
      );
      
    }
  }

  ngOnDestroy():void{
    this.routeSub.unsubscribe();
  }
}
