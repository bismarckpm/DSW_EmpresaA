import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogoGestionarUserComponent } from './dialogo-gestionar-user.component';

describe('DialogoGestionarUserComponent', () => {
  let component: DialogoGestionarUserComponent;
  let fixture: ComponentFixture<DialogoGestionarUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogoGestionarUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogoGestionarUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
