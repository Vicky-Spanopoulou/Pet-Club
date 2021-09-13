import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashPetFormComponent } from './dash-pet-form.component';

describe('DashPetFormComponent', () => {
  let component: DashPetFormComponent;
  let fixture: ComponentFixture<DashPetFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashPetFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashPetFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
