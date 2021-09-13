import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashSubscriptionComponent } from './dash-subscription.component';

describe('DashSubscriptionComponent', () => {
  let component: DashSubscriptionComponent;
  let fixture: ComponentFixture<DashSubscriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashSubscriptionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashSubscriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
