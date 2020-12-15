import { TestBed } from '@angular/core/testing';

import { OcupacionServicioService } from './ocupacion-servicio.service';

describe('OcupacionServicioService', () => {
  let service: OcupacionServicioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OcupacionServicioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
