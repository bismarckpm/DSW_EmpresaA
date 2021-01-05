import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogCrearEstudioComponent } from './dialog-crear-estudio.component';

describe('DialogCrearEstudioComponent', () => {
  let component: DialogCrearEstudioComponent;
  let fixture: ComponentFixture<DialogCrearEstudioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogCrearEstudioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogCrearEstudioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
