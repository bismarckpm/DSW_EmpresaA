import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecomendacionEstudiosComponent } from './recomendacion-estudios.component';

describe('RecomendacionEstudiosComponent', () => {
  let component: RecomendacionEstudiosComponent;
  let fixture: ComponentFixture<RecomendacionEstudiosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecomendacionEstudiosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecomendacionEstudiosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
