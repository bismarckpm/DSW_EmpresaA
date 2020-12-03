import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSubcategoriaComponent } from './create-subcategoria.component';

describe('CreateSubcategoriaComponent', () => {
  let component: CreateSubcategoriaComponent;
  let fixture: ComponentFixture<CreateSubcategoriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateSubcategoriaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateSubcategoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
