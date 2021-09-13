import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FrontNavbarComponent } from './front-navbar.component';

describe('FrontNavbarComponent', () => {
  let component: FrontNavbarComponent;
  let fixture: ComponentFixture<FrontNavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FrontNavbarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FrontNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
