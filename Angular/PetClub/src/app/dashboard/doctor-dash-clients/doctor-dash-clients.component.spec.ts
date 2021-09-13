import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorDashClientsComponent } from './doctor-dash-clients.component';

describe('DoctorDashClientsComponent', () => {
  let component: DoctorDashClientsComponent;
  let fixture: ComponentFixture<DoctorDashClientsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DoctorDashClientsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DoctorDashClientsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
