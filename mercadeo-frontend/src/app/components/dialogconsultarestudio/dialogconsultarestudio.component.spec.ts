import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogconsultarestudioComponent } from './dialogconsultarestudio.component';

describe('DialogconsultarestudioComponent', () => {
  let component: DialogconsultarestudioComponent;
  let fixture: ComponentFixture<DialogconsultarestudioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogconsultarestudioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogconsultarestudioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
