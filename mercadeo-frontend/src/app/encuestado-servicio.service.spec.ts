import { TestBed } from '@angular/core/testing';

import { EncuestadoServicioService } from './services/encuestado-servicio.service';

describe('UsuarioServicioService', () => {
  let service: EncuestadoServicioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EncuestadoServicioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
