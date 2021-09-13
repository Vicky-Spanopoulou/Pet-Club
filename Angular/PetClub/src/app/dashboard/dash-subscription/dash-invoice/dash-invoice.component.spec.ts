import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashInvoiceComponent } from './dash-invoice.component';

describe('DashInvoiceComponent', () => {
  let component: DashInvoiceComponent;
  let fixture: ComponentFixture<DashInvoiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashInvoiceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashInvoiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
