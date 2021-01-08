import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogSolicitudesComponent } from './dialog-solicitudes.component';

describe('DialogSolicitudesComponent', () => {
  let component: DialogSolicitudesComponent;
  let fixture: ComponentFixture<DialogSolicitudesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogSolicitudesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogSolicitudesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
