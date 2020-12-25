import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarEstudioEncuestadoComponent } from './consultar-estudio-encuestado.component';

describe('ConsultarEstudioEncuestadoComponent', () => {
  let component: ConsultarEstudioEncuestadoComponent;
  let fixture: ComponentFixture<ConsultarEstudioEncuestadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultarEstudioEncuestadoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultarEstudioEncuestadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
