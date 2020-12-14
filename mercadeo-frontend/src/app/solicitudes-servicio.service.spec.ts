import { TestBed } from '@angular/core/testing';

import { SolicitudesServicioService } from './solicitudes-servicio.service';

describe('SolicitudesServicioService', () => {
  let service: SolicitudesServicioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SolicitudesServicioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
