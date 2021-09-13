import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetExamsComponent } from './pet-exams.component';

describe('PetExamsComponent', () => {
  let component: PetExamsComponent;
  let fixture: ComponentFixture<PetExamsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PetExamsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PetExamsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
