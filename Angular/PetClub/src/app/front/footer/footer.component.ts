import { Component, OnInit } from '@angular/core';
import { DialogLayoutDisplay, ToastNotificationInitializer } from '@costlydeveloper/ngx-awesome-popup';
import { IEmail } from 'src/app/_models/models';
import { EmailService } from 'src/app/_services/email/email.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css','../../../assets/css/default.css']
})
export class FooterComponent implements OnInit {

  emailForm:IEmail={
    sender:null,
    receiver:null,
    subject:null,
    message:""
  }

  errorMsg = "Subscribed Successfully"

  constructor(private emailService:EmailService) {

   }

  ngOnInit(): void {
  }

  toastNotification() {
    const newToastNotification = new ToastNotificationInitializer();
    newToastNotification.setTitle('Success!');
    newToastNotification.setMessage(this.errorMsg);

    // Choose layout color type
    newToastNotification.setConfig({
        LayoutType: DialogLayoutDisplay.SUCCESS // SUCCESS | INFO | NONE | DANGER | WARNING
    });

    // Simply open the toast
    newToastNotification.openToastNotification$();
}

  onSubmit():void{
    this.emailService.newsletterRegister(this.emailForm).subscribe(
      (data) => this.toastNotification(),
      (error) =>null
    );
    this.emailForm.message = "";
  }
}
