import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RespuestapreguntaComponent } from './respuestapregunta.component';

describe('RespuestapreguntaComponent', () => {
  let component: RespuestapreguntaComponent;
  let fixture: ComponentFixture<RespuestapreguntaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RespuestapreguntaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RespuestapreguntaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
