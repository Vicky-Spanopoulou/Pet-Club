import { Component, Input, OnChanges, OnInit } from '@angular/core';
import { Editor, schema } from 'ngx-editor';
import { IReminder, IUser } from 'src/app/_models/models';
import { toHTML, toDoc } from 'ngx-editor';
import { ReminderService } from 'src/app/_services/reminder/reminder.service';
import { UserService } from 'src/app/_services/user.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-reminder-form',
  templateUrl: './reminder-form.component.html',
  styleUrls: [
    './reminder-form.component.css',
    '../../../../assets/css/forms.css',
    '../../../../assets/css/default.css',
  ],
})
export class ReminderFormComponent implements OnInit, OnChanges {
  modal: boolean = false;

  editor: Editor;

  isSuccessful: boolean = false;

  user: IUser;

  @Input() editReminder: IReminder;

  infoJson: any;

  reminderForm: any = {
    id: null,
    info: '',
    done: false,
    userId: null,
  };

  constructor(
    private tokenStorage: TokenStorageService,
    private reminderService: ReminderService,
    private userService: UserService
  ) {
    this.editor = new Editor();
  }

  ngOnInit(): void {
    this.user = this.tokenStorage.getUser();
  }

  ngOnChanges(): void {
    if (this.editReminder) {
      this.reminderForm.id = this.editReminder.id;
      this.infoJson = toDoc(this.editReminder.info);
      this.reminderForm.done = this.editReminder.done;
      this.reminderForm.userId = this.user.id;
    }
  }

  popModal() {
    this.modal = true;
  }

  closeModal() {
    this.modal = false;
  }

  submitReminderForm(): void {
    if (this.infoJson != null) {
      this.reminderForm.info = toHTML(this.infoJson, schema);
    }

    this.reminderForm.userId = this.user.id;


    if (this.reminderForm.id === null) {
      //save new
      this.reminderService.createReminder(this.reminderForm).subscribe(
        (data) => {
          this.getUser();
          this.closeModal();
        },
        (error) => null
      );
    } else {
      this.reminderService.editReminder(this.reminderForm).subscribe(
        (data) => {
          this.getUser();
          this.closeModal();
      },
        (error) => null
      );
    }
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
}
