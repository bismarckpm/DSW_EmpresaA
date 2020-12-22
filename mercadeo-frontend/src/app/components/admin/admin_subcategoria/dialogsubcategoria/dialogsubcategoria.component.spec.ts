import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogsubcategoriaComponent } from './dialogsubcategoria.component';

describe('DialogsubcategoriaComponent', () => {
  let component: DialogsubcategoriaComponent;
  let fixture: ComponentFixture<DialogsubcategoriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogsubcategoriaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogsubcategoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
