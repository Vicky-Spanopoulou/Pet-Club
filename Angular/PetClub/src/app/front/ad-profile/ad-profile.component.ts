import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { MapComponent } from 'src/app/map/map.component';
import { IAd, IPet, IUser } from 'src/app/_models/models';
import { AdsService } from 'src/app/_services/ads/ads.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-ad-profile',
  templateUrl: './ad-profile.component.html',
  styleUrls: ['./ad-profile.component.css','../../../assets/css/default.css','../../../assets/css/profile.css',
  '../../../assets/css/containers.css','../../../assets/css/ribbon.css']
})

export class AdProfileComponent implements OnInit, OnDestroy {
  private routeSub: Subscription;
  adObject:IAd;
  mockupAd:IAd ={
            id:0,
            petName:"",
            chipNumber:0,
            sex:"",
            adCategory:"",
            someWords:"",
            descriptions:"",
            postDate: new Date('2021-07-05T03:24:00'),
            image:"",
            user: 0,
            petCategory: {
                id:0,
                name:""
            },
            address:{
                id: 0,
                street: "",
                latitude: "39.11035094005105",
                longitude: "23.263987890625017",
                postalCode: "",
                city: {
                    id: 2,
                    name: "",
                    latitude: "",
                    longitude: ""
                }
            },
            lostDate:new Date('2021-05-15T03:24:00')
        }


  adUser:any = {
    id:0,
    firstName:"",
    lastName:"",
    phone:"",
    email:""
  }

  imgUrl="../../../../../assets/images/pet_icon.png"

  menuShown:boolean = false;
  userIsTheOwner:boolean;
  user:IUser;

  @ViewChild('mapComponent',{static:false})
  map:MapComponent;

  @ViewChild('adForm',{static:false})
  formComp:any;


  constructor(
    private router:Router,
    private route: ActivatedRoute,
    private userService : UserService,
    private adService: AdsService,
    private tokenStorageService: TokenStorageService
    ) { 
    this.tokenStorageService.autoLogin();
    this.adObject = this.mockupAd;
    this.user = this.tokenStorageService.getUser();
  }

  ngOnInit(): void {
    this.routeSub = this.route.params.subscribe(params => {
      this.getAdById(params['id']);
      this.getUserByAd(params['id']);
      this.imgUrl = this.adObject.image;
    });
    
  }

  ngAfterViewInit(){
  }

  ngOnDestroy():void{
    this.routeSub.unsubscribe();
  }

  toggleMenuShown():void{
    this.menuShown = !this.menuShown;
  }
  deleteTheAd():void{
    let answer = confirm('Are you sure that you want to delete this ad?');
    if(answer){
      this.adService.deleteAdById(this.adObject.id).subscribe(
        (data)=> this.router.navigate(['home']),
        (error) => null
      );
      
    }

  }

  editTheAd():void{
    //Call the popUp form and after update reload page
    this.formComp.popModal();
    this.formComp.externalAd = this.adObject;
    this.formComp.fillTheFormWithExternalData();
    this.toggleMenuShown();
  }

  getAdById(id:number){
    this.adService.getAdById(id).subscribe(
      (data:IAd) => {
        this.adObject = data;
        this.imgUrl = data.image;
      },
      (error)=>{ this.router.navigate(['/error']);}
    );
  }

  getUserByAd(id:number){
    this.userService.getUserByAd(id).subscribe(
      (data:IUser) => {
        this.adUser = data;
        this.map.addAdMarker(this.adObject);
        this.map.moveMapToLocation({latitude:this.adObject.address.latitude ,longitude:this.adObject.address.longitude} ,16)
        if(this.user.id == this.adUser.id){
          this.userIsTheOwner = true;
        }
      }
    );
  }
}
