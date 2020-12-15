import { TestBed } from '@angular/core/testing';

import { NivelAcademicoServicioService } from './nivel-academico-servicio.service';

describe('NivelAcademicoServicioService', () => {
  let service: NivelAcademicoServicioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NivelAcademicoServicioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
