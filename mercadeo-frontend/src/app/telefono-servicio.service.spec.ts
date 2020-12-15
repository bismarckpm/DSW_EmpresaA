import { TestBed } from '@angular/core/testing';

import { TelefonoServicioService } from './telefono-servicio.service';

describe('TelefonoServicioService', () => {
  let service: TelefonoServicioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TelefonoServicioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
