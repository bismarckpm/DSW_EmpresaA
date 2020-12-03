import { TestBed } from '@angular/core/testing';

import { LugarServicioService } from './services/lugar-servicio.service';

describe('LugarServicioService', () => {
  let service: LugarServicioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LugarServicioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
