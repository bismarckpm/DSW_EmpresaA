import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrarsolicitudComponent } from './registrarsolicitud.component';

describe('RegistrarsolicitudComponent', () => {
  let component: RegistrarsolicitudComponent;
  let fixture: ComponentFixture<RegistrarsolicitudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrarsolicitudComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrarsolicitudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
