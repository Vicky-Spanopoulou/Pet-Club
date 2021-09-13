import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilePropertiesComponent } from './profile-properties.component';

describe('ProfilePropertiesComponent', () => {
  let component: ProfilePropertiesComponent;
  let fixture: ComponentFixture<ProfilePropertiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfilePropertiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfilePropertiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
