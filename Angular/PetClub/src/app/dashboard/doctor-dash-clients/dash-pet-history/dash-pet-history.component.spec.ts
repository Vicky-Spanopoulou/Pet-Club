import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashPetHistoryComponent } from './dash-pet-history.component';

describe('DashPetHistoryComponent', () => {
  let component: DashPetHistoryComponent;
  let fixture: ComponentFixture<DashPetHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashPetHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashPetHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
