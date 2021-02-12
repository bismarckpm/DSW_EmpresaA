import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogopcionComponent } from './dialogopcion.component';

describe('DialogopcionComponent', () => {
  let component: DialogopcionComponent;
  let fixture: ComponentFixture<DialogopcionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogopcionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogopcionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
