import { AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { MapComponent } from 'src/app/map/map.component';
import { IBusiness, IEmail, IRating, IUser } from 'src/app/_models/models';
import { BusinessService } from 'src/app/_services/business/business.service';
import { EmailService } from 'src/app/_services/email/email.service';
import { RatingService } from 'src/app/_services/rating/rating.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-business-profile',
  templateUrl: './business-profile.component.html',
  styleUrls: ['./business-profile.component.css', '../../../assets/css/default.css', '../../../assets/css/containers.css']
})
export class BusinessProfileComponent implements OnInit, OnDestroy, AfterViewInit{
  private routeSub: Subscription;
  business:IBusiness={
    id: 0,
    name: "",
    email: "",
    phone: "",
    description: "",
    text: "",
    imgLogo: "",
    imgBackground: "",
    facebook: "",
    instagram: "",
    pageUrl: "",
    businessCategory: {
        id: 0,
        name: ""
    },
    address: {
        id: 0,
        street: "",
        longitude: "39.11035094005105",
        latitude: "23.263987890625017",
        postalCode: "",
        city: {
            id: 0,
            name: "",
            latitude: "",
            longitude: ""
        }
    },
    status: "",
    avgRating:0
};
  // averageRating:number|null=null;
  owner:IUser;
  visitor:IUser;

  // userRating:IRating;
  rating:IRating={
    id: null,
    ratingNumber: 0,
    userId: 0,
    businessId: 0
  }

  contactForm:IEmail={
    sender:'',
    receiver:'',
    subject:'',
    message:''
  }


  showDescription:boolean = true;
  isSuccessful:boolean = false;

  @ViewChild('ourCompany',{static:false})
  ourCompany:ElementRef;

  @ViewChild('contactUs',{static:false})
  contactUs:ElementRef;

  @ViewChild('mapComponent',{static:false})
  map:MapComponent;

  constructor(
    private route: ActivatedRoute,
    private businessService: BusinessService,
    private userService: UserService,
    private ratingService: RatingService,
    private router: Router,
    private tokenStorageServide:TokenStorageService,
    private emailService:EmailService
    ) 
    {
      this.tokenStorageServide.autoLogin();
   
    this.contactForm.receiver = this.business.email;
   }

  ngOnInit(): void {
    this.visitor = this.tokenStorageServide.getUser();
    this.rating.userId = this.visitor.id;
    this.routeSub = this.route.params.subscribe(params => {
      this.getBusinessById(params['id']);
      this.rating.businessId = params['id'];
    });
    this.getRating();
    this.contactForm.receiver = this.business.email;
    
  }

  ngAfterViewInit():void{
  }


  rateUs(rate:number){
    this.rating.ratingNumber = rate;
    if(this.rating.id === null){
      this.ratingService.createRating(this.rating).subscribe(
        // (data)=>this.rating.id = data.id,
        (data)=>null,
        (err) => null
      );
    }
    else{
      this.ratingService.editRating(this.rating).subscribe(
        (data)=> null,
        (err)=> null
      );
    }
  }

  sendEmail(){
    this.emailService.sendEmail(this.contactForm).subscribe(
      (data) => null,
      (err) => null
    );
    this.showTheDescription();
  }
  
  changeMenu(event:any){
    if(event.currentTarget.id ==='ourCompany-tab'){
      this.showTheDescription();
    }
    else{
      this.showTheContactForm();
    }
  }

  showTheDescription(){
    this.ourCompany.nativeElement.classList.remove('unselected');
    this.ourCompany.nativeElement.classList.add('selected');
    this.contactUs.nativeElement.classList.remove('selected');
    this.contactUs.nativeElement.classList.add('unselected');
    this.showDescription = true;
  }
  showTheContactForm(){
    this.ourCompany.nativeElement.classList.remove('selected');
    this.ourCompany.nativeElement.classList.add('unselected');
    this.contactUs.nativeElement.classList.remove('unselected');
    this.contactUs.nativeElement.classList.add('selected');
    this.showDescription = false;
  }

  getBusinessById(id:number){
    this.businessService.getBusinessesById(id).subscribe(
      (data:IBusiness) => 
      {
        this.business = data;
        this.getUserByBusinessId(this.business.id);
      },
      (error)=>{ this.router.navigate(['/error']);}
    );
  }

  getRating():void{
    this.ratingService.getByUserAndBusiness(this.visitor.id, this.rating.businessId).subscribe(
      (data:any)=> {
        this.rating.id= data.id;
        this.rating.ratingNumber = data.ratingNumber;
        this.rating.userId = data.user.id;
      },
      (err) => this.rating.ratingNumber = 0
    );
  }

  getUserByBusinessId(id:number){
    this.userService.getUserByBusinessId(id).subscribe(
      (data:IUser) => {
        this.owner = data;
        this.map.addBusinessMarker(this.business);
        this.map.moveMapToLocation({latitude:this.business.address.latitude ,longitude:this.business.address.longitude} ,16);
        this.rating.userId = this.visitor.id;
        this.rating.businessId = this.business.id;
        this.contactForm.sender = this.visitor.email;
        this.contactForm.receiver = this.business.email;
      }
    );
  }

  


  ngOnDestroy():void {
    this.routeSub.unsubscribe();
  }
}
