import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogoPoblacionComponent } from './dialogo-poblacion.component';

describe('DialogoPoblacionComponent', () => {
  let component: DialogoPoblacionComponent;
  let fixture: ComponentFixture<DialogoPoblacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogoPoblacionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogoPoblacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
