import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EconomistComponent } from './economist.component';

describe('EconomistComponent', () => {
  let component: EconomistComponent;
  let fixture: ComponentFixture<EconomistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EconomistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EconomistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
