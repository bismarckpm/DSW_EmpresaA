import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogConsultaSolicitudComponent } from './dialog-consulta-solicitud.component';

describe('DialogConsultaSolicitudComponent', () => {
  let component: DialogConsultaSolicitudComponent;
  let fixture: ComponentFixture<DialogConsultaSolicitudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogConsultaSolicitudComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogConsultaSolicitudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
