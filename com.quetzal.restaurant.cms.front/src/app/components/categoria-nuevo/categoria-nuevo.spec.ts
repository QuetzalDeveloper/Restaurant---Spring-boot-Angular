import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoriaNuevo } from './categoria-nuevo';

describe('CategoriaNuevo', () => {
  let component: CategoriaNuevo;
  let fixture: ComponentFixture<CategoriaNuevo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoriaNuevo]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CategoriaNuevo);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
