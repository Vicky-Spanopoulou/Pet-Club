import { Component, OnInit, ViewChild, ElementRef, Input, HostListener } from '@angular/core';
import { IAd, IBusiness } from '../_models/models';

declare let H: any;

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
})
export class MapComponent implements OnInit {
  private platform: any;
  private map: any;
  private ui:any;
  private geocodingService:any;
  private streetPosition:any;

  @ViewChild('map', { static: false })
  private mapElement: ElementRef;

  @Input()
  public lat: any;

  @Input()
  public lng: any;

  @Input()
  public zoom: any;

  @Input()
  public width: any;

  @Input()
  public height: any;

  @Input()
  autoMarker:string;

  // @Input()
  // public businesses:IBusiness[];

  constructor() {
    
  }

  ngOnInit(): void {
    // Creates the platform for the map
    this.platform = new H.service.Platform({
      apikey: 'd6EndoN23ZdsY6Aa0r29VadjDTeVx-PACRR7ZIhcq8E',
    });

    this.geocodingService = this.platform.getGeocodingService();
    
    
    
  }

  public ngAfterViewInit() {
    //Creates the map and import it to the platform
    let defaultLayers = this.platform.createDefaultLayers();
    this.map = new H.Map(
      this.mapElement.nativeElement,
      defaultLayers.vector.normal.map,
      {
        zoom: this.zoom,
        center: { lat: this.lat, lng: this.lng },
      }
    );
    let mapEvents = new H.mapevents.MapEvents(this.map);
    let behavior = new H.mapevents.Behavior(mapEvents);
    this.ui = H.ui.UI.createDefault(this.map, defaultLayers);


    if(this.autoMarker !==undefined && this.autoMarker ==='true'){
      this.addMarker({latitude:this.lat ,longitude:this.lng});
    }
  }

  // Adds event listener to window
  // If window is resized then the map will resize too.
  @HostListener('window:resize', ['$event'])
  resize(event: any) {
    this.map.getViewPort().resize();
  }


  // Adds a marker on the map
  public addMarker(position: any) {
    let marker = new H.map.Marker({
      lat: position.latitude,
      lng: position.longitude,
    });
    this.map.addObject(marker);
  }

  public addBusinessMarker(business:IBusiness){
    let marker = new H.map.Marker({ 
      lat: business.address.latitude,     //sets the marker's position
      lng: business.address.longitude 
    });
    let image = business.imgLogo ?business.imgLogo :'localhost:4200/assets/images/defaultBusinessFind.jpg'
    // creates the marker's bubble html
    marker.setData(
    ` <a href='http://localhost:4200/business_profile/${business.id}' style="text-decoration:none;">
    <div style='width:200px; height:125px;'>
    <h2 style='text-align:center; background-color:white; color:var(--first-color)'>${business.name}</h2>
    <img src=${image} height='100' width='100' style='display:block;margin:auto;' >
    </div>
    </a>`
    );
    // creates the event listener. If you click the marker, it's shows up
    marker.addEventListener(
      "tap",
      (event:any) => {
        let bubble = new H.ui.InfoBubble(event.target.getGeometry(), {
          content: event.target.getData(),
        });
        this.ui.addBubble(bubble);
      },false);
    // adds the marker on the map
    this.map.addObject(marker);
  }


  public addAdMarker(ad:IAd){
    let marker = new H.map.Marker({ 
      lat: ad.address.latitude,     //sets the marker's position
      lng: ad.address.longitude 
    });

    // creates the marker's bubble html
    let bgColor = ad.adCategory === 'LOST'? 'red':'green';
    let image = ad.image ?ad.image :'assets/images/animals-lost-pet.jpg';
        marker.setData(
    ` <a href="http://localhost:4200/ad_profile/${ad.id}" style="text-decoration:none;">
    <div style='width:200px; height:125px;'>
    <h2 style='text-align:center; text-decoration:none; background-color:${bgColor}; color:white'>${ad.petName}</h2>
    <img src=${image} height='100':ad.image" width='100' style='display:block;margin:auto;' >
    </div>
    </a>`
    );
    // creates the event listener. If you click the marker, it's shows up
    marker.addEventListener(
      "tap",
      (event:any) => {
        let bubble = new H.ui.InfoBubble(event.target.getGeometry(), {
          content: event.target.getData(),
        });
        this.ui.addBubble(bubble);
      },false);
    // adds the marker on the map
    this.map.addObject(marker);
 
  }





  // Remove all the objects(maps/schemas etch.) from the map
  public cleanMarkers() {
    this.map.removeObjects(this.map.getObjects());
  }

  // Moves the map to Location
  public moveMapToLocation(location: any, zoom: number) {
    this.map.setCenter({
      lat: location.latitude,
      lng: location.longitude,
    });
    this.map.setZoom(zoom);
  }


  public geocodeAddress(query:string):any{
    
    this.geocodingService.geocode(
      {
        "searchtext":query
      },
      (success:any):any =>{
        this.streetPosition= success.Response.View[0].Result[0].Location.DisplayPosition;
        
      },
      (error:any) =>{
        null;
      }
      );
      return this.streetPosition;
  }

  public getPositionAt(x:number,y:number){
    return this.map.screenToGeo(x,y);
  }




}
