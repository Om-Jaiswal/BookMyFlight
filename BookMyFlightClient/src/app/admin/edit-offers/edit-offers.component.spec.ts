import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditOffersComponent } from './edit-offers.component';

describe('EditOffersComponent', () => {
  let component: EditOffersComponent;
  let fixture: ComponentFixture<EditOffersComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditOffersComponent]
    });
    fixture = TestBed.createComponent(EditOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
