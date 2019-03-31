import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemMngComponent } from './item-mng.component';

describe('ItemMngComponent', () => {
  let component: ItemMngComponent;
  let fixture: ComponentFixture<ItemMngComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItemMngComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemMngComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
