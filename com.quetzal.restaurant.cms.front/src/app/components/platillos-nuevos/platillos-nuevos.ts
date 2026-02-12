import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-platillos-nuevos',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './platillos-nuevos.html',
  styleUrl: './platillos-nuevos.css',
})
export class PlatillosNuevos implements OnChanges{
  @Input() isOpen = false;
  @Input() categorias: any[] = [];
  @Input() platilloAEditar: any = null;
  @Output() close = new EventEmitter<void>();
  @Output() save = new EventEmitter<any>();

  titulo = 'Nuevo Platillo';

  nuevoPlatillo = {
    id: null,
    nombre: '',
    descripcion: '',
    precio: null,
    categoria: 0,
    activo: true
  };

  ngOnChanges(changes: SimpleChanges) {
    // Si llega un platillo para editar, rellenamos el formulario
    if (changes['platilloAEditar'] && this.platilloAEditar) {
      this.nuevoPlatillo = { ...this.platilloAEditar }; // Clonamos el objeto
      this.titulo = 'Editar Platillo';
    } else if (!this.platilloAEditar) {
      this.resetForm();
      this.titulo = 'Nuevo Platillo';
    }
  }

  cerrar() {
    this.close.emit();
    setTimeout(() => {
      this.platilloAEditar = null;
      this.resetForm();
    }, 300);
  }

  guardar() {
    this.save.emit(this.nuevoPlatillo);
    if (this.nuevoPlatillo.nombre && this.nuevoPlatillo.precio! > 0 && this.nuevoPlatillo.categoria > 0) {
      this.save.emit(this.nuevoPlatillo);
      this.resetForm(); // Limpia el formulario después de guardar
    }
  }

  resetForm() {
    this.nuevoPlatillo = {id:null, nombre: '', descripcion: '', precio: null, categoria: 0, activo: true };
  }
}
