import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditasolicitudComponent } from './editasolicitud.component';

describe('EditasolicitudComponent', () => {
  let component: EditasolicitudComponent;
  let fixture: ComponentFixture<EditasolicitudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditasolicitudComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditasolicitudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});