import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllSubscriptionsComponent } from './all-subscriptions.component';

describe('AllSubscriptionsComponent', () => {
  let component: AllSubscriptionsComponent;
  let fixture: ComponentFixture<AllSubscriptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllSubscriptionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllSubscriptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
