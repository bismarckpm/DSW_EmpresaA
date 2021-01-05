import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecomendarEstudiosComponent } from './recomendar-estudios.component';

describe('RecomendarEstudiosComponent', () => {
  let component: RecomendarEstudiosComponent;
  let fixture: ComponentFixture<RecomendarEstudiosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecomendarEstudiosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecomendarEstudiosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
