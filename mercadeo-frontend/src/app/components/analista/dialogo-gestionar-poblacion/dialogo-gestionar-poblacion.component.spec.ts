import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogoGestionarPoblacionComponent } from './dialogo-gestionar-poblacion.component';

describe('DialogoGestionarPoblacionComponent', () => {
  let component: DialogoGestionarPoblacionComponent;
  let fixture: ComponentFixture<DialogoGestionarPoblacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogoGestionarPoblacionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogoGestionarPoblacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
