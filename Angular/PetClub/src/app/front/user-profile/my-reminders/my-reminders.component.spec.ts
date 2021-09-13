import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyRemindersComponent } from './my-reminders.component';

describe('MyRemindersComponent', () => {
  let component: MyRemindersComponent;
  let fixture: ComponentFixture<MyRemindersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyRemindersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyRemindersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
