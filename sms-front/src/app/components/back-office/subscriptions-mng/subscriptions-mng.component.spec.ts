import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscriptionsMngComponent } from './subscriptions-mng.component';

describe('SubscriptionsMngComponent', () => {
  let component: SubscriptionsMngComponent;
  let fixture: ComponentFixture<SubscriptionsMngComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubscriptionsMngComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubscriptionsMngComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
