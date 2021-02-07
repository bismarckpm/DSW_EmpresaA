import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogCrearestudiorecomendadoComponent } from './dialog-crearestudiorecomendado.component';

describe('DialogCrearestudiorecomendadoComponent', () => {
  let component: DialogCrearestudiorecomendadoComponent;
  let fixture: ComponentFixture<DialogCrearestudiorecomendadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogCrearestudiorecomendadoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogCrearestudiorecomendadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
