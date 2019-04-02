import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JobMngComponent } from './job-mng.component';

describe('JobMngComponent', () => {
  let component: JobMngComponent;
  let fixture: ComponentFixture<JobMngComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JobMngComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JobMngComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
