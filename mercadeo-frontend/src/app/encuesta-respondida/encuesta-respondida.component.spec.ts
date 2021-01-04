import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EncuestaRespondidaComponent } from './encuesta-respondida.component';

describe('EncuestaRespondidaComponent', () => {
  let component: EncuestaRespondidaComponent;
  let fixture: ComponentFixture<EncuestaRespondidaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EncuestaRespondidaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EncuestaRespondidaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
