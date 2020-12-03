import { TestBed } from '@angular/core/testing';

import { PreguntaEstudioServicioService } from './pregunta-estudio-servicio.service';

describe('PreguntaEstudioServicioService', () => {
  let service: PreguntaEstudioServicioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PreguntaEstudioServicioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
