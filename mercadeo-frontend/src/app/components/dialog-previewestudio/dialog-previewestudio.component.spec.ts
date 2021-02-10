import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogPreviewestudioComponent } from './dialog-previewestudio.component';

describe('DialogPreviewestudioComponent', () => {
  let component: DialogPreviewestudioComponent;
  let fixture: ComponentFixture<DialogPreviewestudioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogPreviewestudioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogPreviewestudioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
