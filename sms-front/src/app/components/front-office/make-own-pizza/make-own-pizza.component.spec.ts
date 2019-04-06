import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MakeOwnPizzaComponent } from './make-own-pizza.component';

describe('MakeOwnPizzaComponent', () => {
  let component: MakeOwnPizzaComponent;
  let fixture: ComponentFixture<MakeOwnPizzaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MakeOwnPizzaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MakeOwnPizzaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
