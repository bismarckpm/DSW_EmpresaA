import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarespuestaComponent } from './consultarespuesta.component';

describe('ConsultarespuestaComponent', () => {
  let component: ConsultarespuestaComponent;
  let fixture: ComponentFixture<ConsultarespuestaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultarespuestaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultarespuestaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
