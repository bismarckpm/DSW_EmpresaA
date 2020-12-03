import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogtipoComponent } from './dialogtipo.component';

describe('DialogtipoComponent', () => {
  let component: DialogtipoComponent;
  let fixture: ComponentFixture<DialogtipoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogtipoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogtipoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
