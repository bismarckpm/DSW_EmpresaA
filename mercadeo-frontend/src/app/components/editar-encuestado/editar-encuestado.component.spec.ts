import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarEncuestadoComponent } from './editar-encuestado.component';

describe('EditarEncuestadoComponent', () => {
  let component: EditarEncuestadoComponent;
  let fixture: ComponentFixture<EditarEncuestadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditarEncuestadoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditarEncuestadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
