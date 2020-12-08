import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogpresentacionComponent } from './dialogpresentacion.component';

describe('DialogpresentacionComponent', () => {
  let component: DialogpresentacionComponent;
  let fixture: ComponentFixture<DialogpresentacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogpresentacionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogpresentacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
