import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DatoUsuarioComponent } from './dato-usuario.component';

describe('DatoUsuarioComponent', () => {
  let component: DatoUsuarioComponent;
  let fixture: ComponentFixture<DatoUsuarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DatoUsuarioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DatoUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
