import { TestBed } from '@angular/core/testing';

import { RolServicioService } from './rol-servicio.service';

describe('RolServicioService', () => {
  let service: RolServicioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RolServicioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
