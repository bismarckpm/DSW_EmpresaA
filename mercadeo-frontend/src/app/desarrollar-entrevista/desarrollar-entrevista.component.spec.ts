import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DesarrollarEntrevistaComponent } from './desarrollar-entrevista.component';

describe('DesarrollarEntrevistaComponent', () => {
  let component: DesarrollarEntrevistaComponent;
  let fixture: ComponentFixture<DesarrollarEntrevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DesarrollarEntrevistaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DesarrollarEntrevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
