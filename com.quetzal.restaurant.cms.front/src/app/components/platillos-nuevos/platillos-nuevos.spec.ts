import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlatillosNuevos } from './platillos-nuevos';

describe('PlatillosNuevos', () => {
  let component: PlatillosNuevos;
  let fixture: ComponentFixture<PlatillosNuevos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PlatillosNuevos]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PlatillosNuevos);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
