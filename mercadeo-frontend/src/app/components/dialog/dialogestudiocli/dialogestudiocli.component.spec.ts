import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogestudiocliComponent } from './dialogestudiocli.component';

describe('DialogestudiocliComponent', () => {
  let component: DialogestudiocliComponent;
  let fixture: ComponentFixture<DialogestudiocliComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogestudiocliComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogestudiocliComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
