import { Component, Input, OnInit } from '@angular/core';
import { IPet, IPetCategory, IUser } from 'src/app/_models/models';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { Editor, schema } from 'ngx-editor';
import { CategoriesService } from 'src/app/_services/categories/categories.service';
import { PetService } from 'src/app/_services/pet/pet.service';
import { toHTML, toDoc } from 'ngx-editor'; 
import { HttpEventType, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-pet-form',
  templateUrl: './pet-form.component.html',
  styleUrls: [
    './pet-form.component.css',
    '../../../../assets/css/forms.css',
    '../../../../assets/css/default.css',
  ],
})
export class PetFormComponent implements OnInit {
  editor: Editor;
  behaviorJson: any;
  @Input() externaIPet: IPet;

  imgUrl: string = '../../../../assets/images/pet_icon.png';
  modal: boolean = false;

  user: IUser;

  petCategories: IPetCategory[];

  sexCategories: string[];

  petForm: any = {
    id: null,
    name: null,
    breed: null,
    dob: null,
    chipNumber: null,
    color: null,
    behavior: null,
    sex: "OTHER",
    image: null,
    petCategory: null,
  };
 

  isSuccessful: boolean = false;
  selectedFiles?: FileList;
  progress = 0;
  message = '';
  fileInfos?: Observable<any>;

  constructor(
    private tokenStorage: TokenStorageService,
    private petCatService: CategoriesService,
    private petService: PetService,
    private userService: UserService
  ) {
    this.editor = new Editor();
  }

  ngOnInit(): void {

    this.user = this.tokenStorage.getUser();
    this.petForm.user = this.user.id;

    if (this.externaIPet) {
      this.fillThePetForEdit();
    }

    this.getAllPetCategories();
    this.getAllGenderCategories();
    
    
  }

 

  selectFile(event: any): void {
    if (event.target.files) {
      let reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      this.selectedFiles=event.target.files;
      reader.onload = (e: any) => {
        this.imgUrl = e.target.result;
      };
    }
  }

  getAllPetCategories() {
    this.petCatService
      .getAllPetCategories()
      .subscribe((actualPetCat: IPetCategory[]) => {
        this.petCategories = actualPetCat;
      });
  }

  getAllGenderCategories() {
    this.petCatService.getAllPetSexes().subscribe((actualPetSexes: any[]) => {
      this.sexCategories = actualPetSexes;
    });
  }

  submitPetForm(): void {
    if(this.behaviorJson){
      this.petForm.behavior = toHTML(this.behaviorJson, schema);
    }
    

    if (this.petForm.id === null) {
      //save new
      this.petService.createPet(this.petForm).subscribe(
        (data:IPet) =>{
        this.petForm.id = data.id;
        this.upload();
        this.getUser();
      },
        (error) => null
      );
      
    } else {
      //update update existing entry
      this.petService.editPet(this.petForm).subscribe(
        (data) => {
            this.getUser();
        },
        (error) => null
      );
      this.upload();
    }
  }

  upload():void{
    this.progress=0;
    if(this.selectedFiles){
      const file:File|null =this.selectedFiles[0];
      if(file){
        this.petService.upload(file,this.user.username,this.petForm.id).subscribe(
          (event:any)=>{
            if(event.type=== HttpEventType.UploadProgress){
              this.progress = Math.round(100 * event.loaded / event.total);
            }else if (event instanceof HttpResponse){
              this.message = event.body.message;
              this.fileInfos = this.petService.getFiles(this.user.username,this.petForm.id);
            }
          },
          (err:any)=>{
            this.progress=0;
            if (err.error && err.error.message) {
              this.message = err.error.message;
            } else {
              this.message = 'Could not upload the file!';
            }
          });
      }
    }

  }



  fillThePetForEdit(): void {
    (this.petForm.id = this.externaIPet.id),
    (this.petForm.name = this.externaIPet.name),
    (this.petForm.breed = this.externaIPet.breed),
    (this.petForm.dob = this.externaIPet.dob),
    (this.petForm.chipNumber = this.externaIPet.chipNumber),
    (this.petForm.color = this.externaIPet.color),
    (this.petForm.behavior = this.externaIPet.behavior),
    (this.behaviorJson = toDoc(this.externaIPet.behavior)),
    (this.petForm.sex = this.externaIPet.sex),
    (this.petForm.image = this.externaIPet.image),
    (this.imgUrl = this.externaIPet.image),
    (this.petForm.petCategory = this.externaIPet.petCategory.name);
  }

  reloadPage(): void {
    window.location.reload();
  }

  getUser(): void {
    this.userService
      .getUserByUsername(this.user.username)
      .subscribe((event: any) => {
        this.user = event;
        this.tokenStorage.saveUser(event);
      });
      this.closeModal();
  }

  popModal() {
    this.modal = true;
  }

  closeModal() {
    this.modal = false;
  }

}
