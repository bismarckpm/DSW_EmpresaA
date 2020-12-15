import { TestBed } from '@angular/core/testing';

import { DataUsuarioServicioService } from './services/data-usuario-servicio.service';

describe('DataUsuarioServicioService', () => {
  let service: DataUsuarioServicioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DataUsuarioServicioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
