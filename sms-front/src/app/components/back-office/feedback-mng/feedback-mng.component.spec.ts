import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackMngComponent } from './feedback-mng.component';

describe('FeedbackMngComponent', () => {
  let component: FeedbackMngComponent;
  let fixture: ComponentFixture<FeedbackMngComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FeedbackMngComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FeedbackMngComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
