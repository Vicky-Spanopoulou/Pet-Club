import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashUserFormComponent } from './dash-user-form.component';

describe('DashUserFormComponent', () => {
  let component: DashUserFormComponent;
  let fixture: ComponentFixture<DashUserFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashUserFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashUserFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
