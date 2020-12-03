import { TestBed } from '@angular/core/testing';

import { DataLugarServicioService } from './services/data-lugar-servicio.service';

describe('DataLugarServicioService', () => {
  let service: DataLugarServicioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DataLugarServicioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
