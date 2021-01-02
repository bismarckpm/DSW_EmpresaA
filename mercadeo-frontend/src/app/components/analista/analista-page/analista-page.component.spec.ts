import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalistaPageComponent } from './analista-page.component';

describe('AnalistaPageComponent', () => {
  let component: AnalistaPageComponent;
  let fixture: ComponentFixture<AnalistaPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnalistaPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalistaPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
