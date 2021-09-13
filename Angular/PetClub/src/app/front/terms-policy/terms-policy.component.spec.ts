import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TermsPolicyComponent } from './terms-policy.component';

describe('TermsPolicyComponent', () => {
  let component: TermsPolicyComponent;
  let fixture: ComponentFixture<TermsPolicyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TermsPolicyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TermsPolicyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
