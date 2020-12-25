import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePresentacionComponent } from './create-presentacion.component';

describe('CreatePresentacionComponent', () => {
  let component: CreatePresentacionComponent;
  let fixture: ComponentFixture<CreatePresentacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatePresentacionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatePresentacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
