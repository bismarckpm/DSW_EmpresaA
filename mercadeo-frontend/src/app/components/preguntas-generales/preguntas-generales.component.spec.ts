import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreguntasGeneralesComponent } from './preguntas-generales.component';

describe('PreguntasGeneralesComponent', () => {
  let component: PreguntasGeneralesComponent;
  let fixture: ComponentFixture<PreguntasGeneralesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreguntasGeneralesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreguntasGeneralesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
