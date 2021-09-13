import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashAdsComponent } from './dash-ads.component';

describe('DashAdsComponent', () => {
  let component: DashAdsComponent;
  let fixture: ComponentFixture<DashAdsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashAdsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashAdsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
