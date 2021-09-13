import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashAdsFormComponent } from './dash-ads-form.component';

describe('DashAdsFormComponent', () => {
  let component: DashAdsFormComponent;
  let fixture: ComponentFixture<DashAdsFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashAdsFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashAdsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
