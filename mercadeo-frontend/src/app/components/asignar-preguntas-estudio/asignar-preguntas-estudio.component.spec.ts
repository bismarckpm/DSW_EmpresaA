import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AsignarPreguntasEstudioComponent } from './asignar-preguntas-estudio.component';

describe('AsignarPreguntasEstudioComponent', () => {
  let component: AsignarPreguntasEstudioComponent;
  let fixture: ComponentFixture<AsignarPreguntasEstudioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AsignarPreguntasEstudioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AsignarPreguntasEstudioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
