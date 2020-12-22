import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarEstudioAnalistaComponent } from './consultar-estudio-analista.component';

describe('ConsultarEstudioAnalistaComponent', () => {
  let component: ConsultarEstudioAnalistaComponent;
  let fixture: ComponentFixture<ConsultarEstudioAnalistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultarEstudioAnalistaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultarEstudioAnalistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
