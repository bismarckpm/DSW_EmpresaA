import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistraPreguntaComponent } from './registra-pregunta.component';

describe('RegistraPreguntaComponent', () => {
  let component: RegistraPreguntaComponent;
  let fixture: ComponentFixture<RegistraPreguntaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistraPreguntaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistraPreguntaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
