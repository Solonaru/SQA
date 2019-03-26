import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerRelationsComponent } from './customer-relations.component';

describe('CustomerRelationsComponent', () => {
  let component: CustomerRelationsComponent;
  let fixture: ComponentFixture<CustomerRelationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerRelationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerRelationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
