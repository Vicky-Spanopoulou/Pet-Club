import { Component, OnInit } from '@angular/core';
import { IUser } from 'src/app/_models/models';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-security-properties',
  templateUrl: './security-properties.component.html',
  styleUrls: [
    './security-properties.component.css',
    '../../../../assets/css/default.css',
  ],
})
export class SecurityPropertiesComponent implements OnInit {
  editMailForm: any = {
    id: null,
    username: '',
    email: '',
    password: '',
  };

  editPassForm: any = {
    username: '',
    oldPass: '',
    newPass: '',
  };

  oldMail: string;

  confirmMail: string;

  confirmPass: string;

  loggedUser: IUser;

  userToDelete: IUser;

  password: string;

  constructor(
    private tokenService: TokenStorageService,
    private userService: UserService
  ) {
    this.loggedUser = this.tokenService.getUser();
  }

  ngOnInit(): void {
    this.oldMail = this.loggedUser.email;
  }

  toggleEmailForm() {
    let invisible = document.getElementsByClassName('mail-invisible');
    let inactive = document.getElementsByClassName('mail-inactive');
    for (let i = 0; i < invisible.length; i++) {
      invisible[i].classList.toggle('hidden');
    }
    for (let i = 0; i < inactive.length; i++) {
      inactive[i].classList.toggle('disabled-input');
    }
  }

  togglePassForm() {
    let invisible = document.getElementsByClassName('pass-invisible');
    let inactive = document.getElementsByClassName('pass-inactive');
    for (let i = 0; i < invisible.length; i++) {
      invisible[i].classList.toggle('hidden');
    }
    for (let i = 0; i < inactive.length; i++) {
      inactive[i].classList.toggle('disabled-input');
    }
  }

  setMailForm(): void {
    this.oldMail = this.loggedUser.email;
    this.toggleEmailForm();
  }

  editEmail(): void {
    this.editMailForm.id = this.loggedUser.id;
    this.editMailForm.username = this.loggedUser.username;

    // update email
    if (this.editMailForm.email === this.confirmMail) {
      this.userService.updateEmail(this.editMailForm).subscribe(
        (data) => null,
        (error) => null
      );
      this.toggleEmailForm();
    } else {
      alert('emails do not match');
    }
  }

  editPassword(): void {
    this.editPassForm.username = this.loggedUser.username;

    //update password
    if (this.editPassForm.newPass === this.confirmPass) {
      this.userService.updatePassword(this.editPassForm).subscribe(
        (data) => null,
        (error) => null
      );
      this.togglePassForm();
    } else {
      alert('passwords do not match');
    }
  }

  deleteAccount(userToDelete: IUser): void {
    
    userToDelete = this.loggedUser;

    let answer = confirm('Do you want permanently delete your account?');
    if (answer && userToDelete.id != null) {
      this.userService.deleteUserById(userToDelete.id).subscribe((deletedUser) => {
        this.tokenService.signOut();
        window.location.reload();
      });
    }
  }
}
