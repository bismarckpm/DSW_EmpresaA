import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VistasolicitudComponent } from './vistasolicitud.component';

describe('VistasolicitudComponent', () => {
  let component: VistasolicitudComponent;
  let fixture: ComponentFixture<VistasolicitudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VistasolicitudComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VistasolicitudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
