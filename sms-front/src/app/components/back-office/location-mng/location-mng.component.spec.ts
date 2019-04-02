import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LocationMngComponent } from './location-mng.component';

describe('LocationMngComponent', () => {
  let component: LocationMngComponent;
  let fixture: ComponentFixture<LocationMngComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LocationMngComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LocationMngComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
