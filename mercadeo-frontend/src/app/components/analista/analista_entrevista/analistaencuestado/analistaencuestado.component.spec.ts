import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalistaencuestadoComponent } from './analistaencuestado.component';

describe('AnalistaencuestadoComponent', () => {
  let component: AnalistaencuestadoComponent;
  let fixture: ComponentFixture<AnalistaencuestadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnalistaencuestadoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalistaencuestadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
