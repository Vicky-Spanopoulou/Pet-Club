import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BankPaymentComponent } from './bank-payment.component';

describe('BankPaymentComponent', () => {
  let component: BankPaymentComponent;
  let fixture: ComponentFixture<BankPaymentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BankPaymentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BankPaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
