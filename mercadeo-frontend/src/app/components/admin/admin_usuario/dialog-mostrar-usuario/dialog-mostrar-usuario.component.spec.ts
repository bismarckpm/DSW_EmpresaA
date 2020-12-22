import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogMostrarUsuarioComponent } from './dialog-mostrar-usuario.component';

describe('DialogMostrarUsuarioComponent', () => {
  let component: DialogMostrarUsuarioComponent;
  let fixture: ComponentFixture<DialogMostrarUsuarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogMostrarUsuarioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogMostrarUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
