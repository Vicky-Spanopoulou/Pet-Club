import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashPagesFormComponent } from './dash-pages-form.component';

describe('DashPagesFormComponent', () => {
  let component: DashPagesFormComponent;
  let fixture: ComponentFixture<DashPagesFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashPagesFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashPagesFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
