
import { AfterViewChecked, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ChatMessageDto } from 'src/app/_models/chatMessageDto';
import { IUser } from 'src/app/_models/models';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { WebSocketService } from 'src/app/_services/web-socket/web-socket.service';




@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css',
              '../../../assets/css/dashboardCss/Defaultdashboard.css']
})
export class ChatComponent implements OnInit , AfterViewChecked{
  loggedInUser:IUser;
  @ViewChild('scrollMe') private myScrollContainer: ElementRef;

  constructor(public webSocketService: WebSocketService,private tokenStorageService: TokenStorageService, private router:Router) { 
    this.tokenStorageService.autoLogin();
    this.loggedInUser = tokenStorageService.getUser();
    if(this.loggedInUser.role !== 'ROLE_ADMIN'){
      this.router.navigate(['home']);
    }
  }

  ngOnInit(): void {
    // if(this.user.role!=="ROLE_ADMIN"){
    //   this.router.navigate(['error'])
    // }
    this.webSocketService.openWebSocket();
    this.scrollToBottom();
  }

  ngAfterViewChecked() {        
    this.scrollToBottom();        
}

 
  ngOnDestroy(): void {
    this.webSocketService.closeWebSocket();
  }

  sendMessage(sendForm: NgForm) {
    const chatMessageDto = new ChatMessageDto(this.loggedInUser.username, sendForm.value.message);
    this.webSocketService.sendMessage(chatMessageDto);
    sendForm.controls.message.reset();
  }

  scrollToBottom(): void {
    try {
        this.myScrollContainer.nativeElement.scrollTop = this.myScrollContainer.nativeElement.scrollHeight;
    } catch(err) { }                 
}

}
