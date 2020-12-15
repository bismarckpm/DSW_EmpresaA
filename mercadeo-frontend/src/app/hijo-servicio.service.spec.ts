import { TestBed } from '@angular/core/testing';

import { HijoServicioService } from './hijo-servicio.service';

describe('HijoServicioService', () => {
  let service: HijoServicioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HijoServicioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
