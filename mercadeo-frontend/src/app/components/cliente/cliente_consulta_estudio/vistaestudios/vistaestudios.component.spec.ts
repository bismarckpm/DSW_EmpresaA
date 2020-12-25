import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VistaestudiosComponent } from './vistaestudios.component';

describe('VistaestudiosComponent', () => {
  let component: VistaestudiosComponent;
  let fixture: ComponentFixture<VistaestudiosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VistaestudiosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VistaestudiosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
