import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeEncuestadoComponent } from './home-encuestado.component';

describe('HomeEncuestadoComponent', () => {
  let component: HomeEncuestadoComponent;
  let fixture: ComponentFixture<HomeEncuestadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeEncuestadoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeEncuestadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
