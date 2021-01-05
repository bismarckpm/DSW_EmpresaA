import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogEstatusComponent } from './dialog-estatus.component';

describe('DialogEstatusComponent', () => {
  let component: DialogEstatusComponent;
  let fixture: ComponentFixture<DialogEstatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogEstatusComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogEstatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
