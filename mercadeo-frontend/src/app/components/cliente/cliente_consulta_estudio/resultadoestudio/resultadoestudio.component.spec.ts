import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultadoestudioComponent } from './resultadoestudio.component';

describe('ResultadoestudioComponent', () => {
  let component: ResultadoestudioComponent;
  let fixture: ComponentFixture<ResultadoestudioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResultadoestudioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResultadoestudioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
