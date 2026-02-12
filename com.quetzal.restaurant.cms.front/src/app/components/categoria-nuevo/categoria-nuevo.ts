import { Component, Input, Output, EventEmitter } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-categoria-nuevo',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './categoria-nuevo.html',
  styleUrl: './categoria-nuevo.css',
})
export class CategoriaNuevo {
  @Input() isOpen = false;
  @Output() close = new EventEmitter<void>();
  @Output() save = new EventEmitter<any>();

  nuevaCategoria = {
    nombre:'',
    activo: true
  }

  cerrar() {
    this.close.emit();
  }

  guardar() {
    if (this.nuevaCategoria.nombre) {
      this.save.emit(this.nuevaCategoria);
      this.resetForm();
    }
  }

  resetForm() {
    this.nuevaCategoria = { nombre: '', activo: true };
  }

}
