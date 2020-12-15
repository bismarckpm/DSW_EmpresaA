import { TestBed } from '@angular/core/testing';

import { DataEncuestadoServicioService } from './services/data-encuestado-servicio.service';

describe('DataUsuarioServicioService', () => {
  let service: DataEncuestadoServicioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DataEncuestadoServicioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
