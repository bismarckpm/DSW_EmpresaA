import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogAsignarPreguntaComponent } from './dialog-asignar-pregunta.component';

describe('DialogAsignarPreguntaComponent', () => {
  let component: DialogAsignarPreguntaComponent;
  let fixture: ComponentFixture<DialogAsignarPreguntaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogAsignarPreguntaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogAsignarPreguntaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
