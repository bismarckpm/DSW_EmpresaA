import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogcategoriaComponent } from './dialogcategoria.component';

describe('DialogcategoriaComponent', () => {
  let component: DialogcategoriaComponent;
  let fixture: ComponentFixture<DialogcategoriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogcategoriaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogcategoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
