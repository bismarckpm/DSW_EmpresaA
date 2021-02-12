import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreguntasRecomendadasComponent } from './preguntas-recomendadas.component';

describe('PreguntasRecomendadasComponent', () => {
  let component: PreguntasRecomendadasComponent;
  let fixture: ComponentFixture<PreguntasRecomendadasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreguntasRecomendadasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreguntasRecomendadasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
