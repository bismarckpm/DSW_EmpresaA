import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificarEstudioComponent } from './modificar-estudio.component';

describe('ModificarEstudioComponent', () => {
  let component: ModificarEstudioComponent;
  let fixture: ComponentFixture<ModificarEstudioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModificarEstudioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModificarEstudioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
