import { AfterViewInit, Component, OnInit, ViewChildren } from '@angular/core';

@Component({
  selector: 'app-slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.css','../../../assets/css/default.css']
})

export class SliderComponent implements OnInit, AfterViewInit {

  private slideIndex : number;
  @ViewChildren('slides') slides: any;
  @ViewChildren('dots') dots: any;

  constructor() {
    this.slideIndex = 1;
  }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    this.showSlides(this.slideIndex);
  }

  public plusSlides(num: number): void {
    this.showSlides(this.slideIndex += num);
  }

  public currentSlide(num: number): void {
    this.showSlides(this.slideIndex = num);
  }


  public showSlides(n: number): void {

    if (n > this.slides.length) { this.slideIndex = 1 }
    if (n < 1) { this.slideIndex = this.slides.length }

    this.slides.toArray().forEach((slide: any) => {
      slide.nativeElement.style.display = 'none';
    });

    this.dots.toArray().forEach((dot: any) => {
      dot.nativeElement.className.replace(' active', '');
    });

    this.slides.toArray()[this.slideIndex - 1].nativeElement.style.display = 'block';
    this.dots.toArray()[this.slideIndex - 1].nativeElement.className += ' active';
  }


}
