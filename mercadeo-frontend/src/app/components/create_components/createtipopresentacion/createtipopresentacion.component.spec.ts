import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatetipopresentacionComponent } from './createtipopresentacion.component';

describe('CreatetipopresentacionComponent', () => {
  let component: CreatetipopresentacionComponent;
  let fixture: ComponentFixture<CreatetipopresentacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatetipopresentacionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatetipopresentacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
