import { TestBed } from '@angular/core/testing';

import { NivelEconomicoServicioService } from './nivel-economico-servicio.service';

describe('NivelEconomicoServicioService', () => {
  let service: NivelEconomicoServicioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NivelEconomicoServicioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
