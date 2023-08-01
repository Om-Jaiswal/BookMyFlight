import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditBookingsComponent } from './edit-bookings.component';

describe('EditBookingsComponent', () => {
  let component: EditBookingsComponent;
  let fixture: ComponentFixture<EditBookingsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditBookingsComponent]
    });
    fixture = TestBed.createComponent(EditBookingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
