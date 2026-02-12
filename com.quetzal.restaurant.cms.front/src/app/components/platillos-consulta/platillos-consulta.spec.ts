import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlatillosConsulta } from './platillos-consulta';

describe('PlatillosConsulta', () => {
  let component: PlatillosConsulta;
  let fixture: ComponentFixture<PlatillosConsulta>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PlatillosConsulta]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PlatillosConsulta);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
