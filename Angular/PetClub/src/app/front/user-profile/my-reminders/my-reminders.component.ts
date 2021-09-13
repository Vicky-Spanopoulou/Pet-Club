import {
  Component,
  Input,
  OnInit,
  OnChanges,
  Output,
  ViewChild,
} from '@angular/core';
import { IReminder, IUser } from 'src/app/_models/models';
import { ReminderService } from 'src/app/_services/reminder/reminder.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';
import { ReminderFormComponent } from '../../forms/reminder-form/reminder-form.component';

@Component({
  selector: 'app-my-reminders',
  templateUrl: './my-reminders.component.html',
  styleUrls: [
    './my-reminders.component.css',
    '../../../../assets/css/default.css',
  ],
})
export class MyRemindersComponent implements OnInit, OnChanges {
  @Input() reminders: IReminder[];

  filteredReminders: IReminder[];

  @Output() editRem: IReminder;

  // For Reminder menu
  showMenu = -1;

  // For Reminder form

  @ViewChild('reminderForm', { static: false })
  reminderForm: ReminderFormComponent;

  modal = false;

  user: IUser;

  info: any;

  constructor(
    private reminderService: ReminderService,
    private tokenService: TokenStorageService,
    private userService: UserService
  ) {
    this.user = tokenService.getUser();
  }

  ngOnInit(): void {
    this.getAllUserReminders();
  }

  ngOnChanges(): void {
    this.filterReminders();
    this.user = this.tokenService.getUser();
  }

  showUndoMenu(index: any) {
    if (this.showMenu === index) {
      this.showMenu = -1;
    } else {
      this.showMenu = index;
    }
  }

  openModal(): void {
    this.reminderForm.popModal();
  }

  //NOT WORKING PROPERLY YET !!!
  reminderChangeCondition(event: IReminder): void {
    let reminder = event;
    reminder.done = !reminder.done;
    this.reminderService.editReminder(reminder).subscribe(
      (data) => {
        console.log('reminder edited');
        this.getAllUserReminders();
        this.getUser();
      },
      (error) => console.log(error)
    );
    this.getAllUserReminders();
  }

  getAllUserReminders() {
    this.reminderService
      .getAllRemindersByUserId(this.user.id)
      .subscribe((actualReminders: any[]) => {
        this.reminders = actualReminders;
        this.filterReminders();
      });
    
  }

  filterReminders() {
    this.filteredReminders = this.reminders;
    this.reminders.sort((a, b) => Number(a.done) - Number(b.done));
  }

  editReminder(rem: IReminder): void {
    console.log(rem);
    this.editRem = rem;
    this.openModal();
    this.getAllUserReminders();
    this.getUser();
  }

  //delete
  onDeleteRem(rem: any): void {
    console.log(rem.id);

    let id = rem.id;
    console.log(id);

    let answer = confirm('Do you want to delete this reminder?');
    if (answer && id != null) {
      this.reminderService.deleteReminderById(id).subscribe(
        (deletedRem) => {
        // this.getAllUserReminders();
        this.modal = false;
        this.getAllUserReminders();
        this.getUser();
        // window.location.reload();
      });
    }
  }

  getUser(): void {
    this.userService
      .getUserByUsername(this.user.username)
      .subscribe((event: any) => {
        this.user = event;
        this.tokenService.saveUser(event);
      });
  }
}
