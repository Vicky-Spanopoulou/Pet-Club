import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashPagesComponent } from './dash-pages.component';

describe('DasgPagesComponent', () => {
  let component: DashPagesComponent;
  let fixture: ComponentFixture<DashPagesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashPagesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashPagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
