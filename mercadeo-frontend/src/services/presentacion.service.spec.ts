import { TestBed } from '@angular/core/testing';

import { PresentacionService } from 'src/app/services/presentacion.service';

describe('PresentacionService', () => {
  let service: PresentacionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PresentacionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
