import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditFlightsComponent } from './edit-flights.component';

describe('EditFlightsComponent', () => {
  let component: EditFlightsComponent;
  let fixture: ComponentFixture<EditFlightsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditFlightsComponent]
    });
    fixture = TestBed.createComponent(EditFlightsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
