import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaMuestraSinResponderComponent } from './consulta-muestra-sin-responder.component';

describe('ConsultaMuestraSinResponderComponent', () => {
  let component: ConsultaMuestraSinResponderComponent;
  let fixture: ComponentFixture<ConsultaMuestraSinResponderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultaMuestraSinResponderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultaMuestraSinResponderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
