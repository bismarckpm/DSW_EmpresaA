import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaMuestraEstudioComponent } from './consulta-muestra-estudio.component';

describe('ConsultaMuestraEstudioComponent', () => {
  let component: ConsultaMuestraEstudioComponent;
  let fixture: ComponentFixture<ConsultaMuestraEstudioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultaMuestraEstudioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultaMuestraEstudioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
