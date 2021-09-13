import { Component, HostListener, OnInit } from '@angular/core';
import { IUser } from 'src/app/_models/models';
import { AuthService } from 'src/app/_services/auth.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-front-navbar',
  templateUrl: './front-navbar.component.html',
  styleUrls: ['./front-navbar.component.css', '../../../assets/css/default.css']
})
export class FrontNavbarComponent implements OnInit {

  menu_properties = false;
  private role: string= '';
  isLoggedIn = false;
  showExtraMenu = false;
  showAdminBoard = false;
  username?: string;
  user:IUser;

  @HostListener('window:scroll', ['$event'])
  onWindowScroll() {
    let element = document.querySelector('#main-navbar') as HTMLElement;
    let mainColorBlur =  getComputedStyle(document.documentElement)
    .getPropertyValue('--first-color-blur');
    let mainColor = getComputedStyle(document.documentElement)
    .getPropertyValue('--first-color');
    if (window.pageYOffset > element.clientHeight) {
      element.style.top = "0";
      document.documentElement.style.setProperty('--main-nav-color',mainColor);
    } else {
      element.style.top = "50px";
      document.documentElement.style.setProperty('--main-nav-color',mainColorBlur);
  }
}
  


  constructor(private authService: AuthService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {

    this.isLoggedIn = !!this.tokenStorage.getToken();

    if (this.isLoggedIn) {
      this.user = this.tokenStorage.getUser();
      this.role = this.user.role;

      this.showAdminBoard = this.role.includes('ROLE_ADMIN') || this.role.includes('ROLE_DOCTOR');

     
    }
  }

  

  logout(): void {
    this.tokenStorage.signOut();
    localStorage.removeItem("rememberMe");
    localStorage.removeItem("auth-token");
    localStorage.removeItem("auth-user");    
    location.reload();
  }
  
  toggleProperties(){
    this.menu_properties = !this.menu_properties;
  }

  




}
