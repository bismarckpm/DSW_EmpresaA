import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarEncuestadosEstudioComponent } from './consultar-encuestados-estudio.component';

describe('ConsultarEncuestadosEstudioComponent', () => {
  let component: ConsultarEncuestadosEstudioComponent;
  let fixture: ComponentFixture<ConsultarEncuestadosEstudioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultarEncuestadosEstudioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultarEncuestadosEstudioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
