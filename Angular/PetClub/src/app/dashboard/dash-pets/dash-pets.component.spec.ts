import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashPetsComponent } from './dash-pets.component';

describe('DashPetsComponent', () => {
  let component: DashPetsComponent;
  let fixture: ComponentFixture<DashPetsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashPetsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashPetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
