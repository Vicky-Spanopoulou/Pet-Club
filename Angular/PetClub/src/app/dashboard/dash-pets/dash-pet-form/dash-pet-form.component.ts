import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Editor, schema } from 'ngx-editor';
import { Observable } from 'rxjs';
import { IPet, IPetCategory, IUser } from 'src/app/_models/models';
import { CategoriesService } from 'src/app/_services/categories/categories.service';
import { PetService } from 'src/app/_services/pet/pet.service';
import { UserService } from 'src/app/_services/user.service';
import { toHTML, toDoc } from 'ngx-editor'; 

@Component({
  selector: 'app-dash-pet-form',
  templateUrl: './dash-pet-form.component.html',
  styleUrls: [
    './dash-pet-form.component.css',
    '../../../../assets/css/dashboardCss/DashboardForms.css',
  ],
})
export class DashPetFormComponent implements OnInit {
  editor: Editor;
  searchOwner: string;
  editOnwer: any = {
    id: null,
    email: null
  };
  hideSearchOwers: boolean = false;
  petCategoryName: string;
  petCategories: any[];
  sexCategories: any[];

  @Input() externalPet: any;

  selectedOwner: any = {
    id: 0,
    username: '',
    firstName: '',
    lastName: '',
    email: '',
    role: '',
    phone: '',
    img: '',
    pets: [],
  };

  owners!: any[];

  filterOwners: any[] = this.owners;

  isSuccessful: boolean = false;
  selectedFiles?: FileList;
  progress = 0;
  message = '';
  fileInfos?: Observable<any>;

  @Output() notify = new EventEmitter<any>();


  constructor(
    private userService: UserService,
    private serviceCategories: CategoriesService,
    private petService: PetService
  ) {
    this.editor = new Editor();    
  }

  ngOnInit(): void {
    this.getAllUsers();
    this.getPetCategories();
    this.getSexCategories();
    if(this.externalPet.id != null) {
      this.getUserByPetId();
    }
  }

  handleUsers() {
    this.hideSearchOwers = true;
    //filtering
    this.filterOwners = this.owners;
    if (this.searchOwner) {
      this.filterOwners = this.filterOwners.filter(
        (x) =>
          x.email.toUpperCase().includes(this.searchOwner.toUpperCase()) ||
          x.firstName.toUpperCase().includes(this.searchOwner.toUpperCase()) ||
          x.lastName.toUpperCase().includes(this.searchOwner.toUpperCase())
      );
    }
  }

  showOwners() {
    this.hideSearchOwers = true;
  }

  hideOwners() {
    this.hideSearchOwers = false;
  }

  selectOwner(owner: any) {
    this.editOnwer = owner;
    this.externalPet.user = this.editOnwer.id;
    this.hideSearchOwers = false;
  }

  getAllUsers() {
    this.userService.getAllUsers().subscribe((actualUsers: IUser[]) => {
      this.owners = actualUsers;
      this.filterOwners = this.owners;
    });
  }

  getUserByPetId() {
    this.userService
      .getUserByPetId(this.externalPet.id)
      .subscribe((actualUser: IUser) => {
        if (actualUser) {
          this.editOnwer = actualUser;
        }
      });
  }

  getPetCategories() {
    this.serviceCategories
      .getAllPetCategories()
      .subscribe((acualPetCategories: any[]) => {
        this.petCategories = acualPetCategories;
      });
  }

  getSexCategories() {
    this.serviceCategories.getAllPetSexes().subscribe((acualSex: any[]) => {
      this.sexCategories = acualSex;
    });
  }


  submitPetForm(): void {
    
    if(this.externalPet.behavior){
      this.externalPet.behavior = toHTML(this.externalPet.behavior, schema);
    }

    if (this.externalPet.id === null) {
      this.petService.createPet(this.externalPet).subscribe(
        (pet: IPet) => {
          this.externalPet.id = pet.id;
          //upload image
          if (this.selectedFiles) {
            this.upload();
          }
          this.notify.emit();
        },
        (error) => null
      );
      //close pet form
      window.scrollTo(0, 0);
    } else {
      this.petService.editPet(this.externalPet).subscribe(
        (pet) => {
        },
        (error) => null
      );
      this.upload();
      this.notify.emit();
      //close pet form
      window.scrollTo(0, 0);     
    }
  }

  deletePet(petToDelete: any): void {
    petToDelete.id = this.externalPet.id;
    let id = petToDelete.id;
    let answer = confirm('Do you want to delete this pet?');
    if (answer && id != null) {
      this.petService.deletePetById(id)
      .subscribe((deletedPet) => {
        this.notify.emit();
      });
    }
    window.scrollTo(0, 0);
  }

  selectFile(event: any): void {
    if (event.target.files) {
      let reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      this.selectedFiles = event.target.files;
      reader.onload = (e: any) => {
        this.externalPet.image = e.target.result;
      };
    }
  }

  upload(): void {
    this.progress = 0;
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles[0];
      if (file) {
        this.petService
          .upload(file, this.externalPet.username, this.externalPet.id)
          .subscribe(
            (event: any) => {
              if (event.type === HttpEventType.UploadProgress) {
                this.progress = Math.round((100 * event.loaded) / event.total);
              } else if (event instanceof HttpResponse) {
                this.message = event.body.message;
                this.fileInfos = this.petService.getFiles(
                  this.externalPet.username,
                  this.externalPet.id
                );
              }
            },
            (err: any) => {
              this.progress = 0;
              if (err.error && err.error.message) {
                this.message = err.error.message;
              } else {
                this.message = 'Could not upload the file!';
              }
            }
          );
      }
    }
  }

}
