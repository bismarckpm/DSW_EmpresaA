import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardproductoComponent } from './dashboardproducto.component';

describe('DashboardproductoComponent', () => {
  let component: DashboardproductoComponent;
  let fixture: ComponentFixture<DashboardproductoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashboardproductoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardproductoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
