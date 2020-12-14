import { TestBed } from '@angular/core/testing';

import { PreguntaEncuestaServiceService } from './pregunta-encuesta-service.service';

describe('PreguntaEncuestaServiceService', () => {
  let service: PreguntaEncuestaServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PreguntaEncuestaServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
