import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogmarcaComponent } from './dialogmarca.component';

describe('DialogmarcaComponent', () => {
  let component: DialogmarcaComponent;
  let fixture: ComponentFixture<DialogmarcaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogmarcaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogmarcaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
