import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogProductoTipoPresentacionComponent } from './dialog-producto-tipo-presentacion.component';

describe('DialogProductoTipoPresentacionComponent', () => {
  let component: DialogProductoTipoPresentacionComponent;
  let fixture: ComponentFixture<DialogProductoTipoPresentacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogProductoTipoPresentacionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogProductoTipoPresentacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
