import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TermsCondComponent } from './terms-cond.component';

describe('TermsCondComponent', () => {
  let component: TermsCondComponent;
  let fixture: ComponentFixture<TermsCondComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TermsCondComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TermsCondComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
