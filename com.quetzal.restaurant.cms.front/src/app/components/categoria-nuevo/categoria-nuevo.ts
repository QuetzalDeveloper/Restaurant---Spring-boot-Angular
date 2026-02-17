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
    id:0,
    name:'',
    active: true
  }

  cerrar() {
    this.close.emit();
  }

  guardar() {
    if (this.nuevaCategoria.name) {
      this.save.emit(this.nuevaCategoria);
      this.resetForm();
    }
  }

  resetForm() {
    this.nuevaCategoria = { id: 0, name: '', active: true };
  }

}
