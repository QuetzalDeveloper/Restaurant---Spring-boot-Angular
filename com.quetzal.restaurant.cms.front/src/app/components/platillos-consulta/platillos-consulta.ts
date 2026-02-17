import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CategoriaCombo } from '../../interfaces/CategoryService';
import { PlatillosNuevos } from '../platillos-nuevos/platillos-nuevos';
import { CategoriaNuevo } from '../categoria-nuevo/categoria-nuevo';
import { MenuService } from '../../services/menu.service';
import Swal from 'sweetalert2';
import { ERROR_CATALOG } from '../../config/ERROR_CATALOG';

@Component({
  selector: 'app-platillos-consulta',
  standalone: true,
  imports: [CommonModule, FormsModule, PlatillosNuevos, CategoriaNuevo],
  templateUrl: './platillos-consulta.html',
  styleUrl: './platillos-consulta.css',
})
export class PlatillosConsulta implements OnInit {
  //Datos select
  listaCategorias: CategoriaCombo[] = [];
  categoriaSeleccionadaId: number = 0;
  //Nuevo/editar platillo form
  showNewDessertForm: boolean = false;
  platilloSeleccionado: any = null;
  //Nueva categoria form
  showNewCategoryForm: boolean = false;

  constructor(private menuService: MenuService, private cdr: ChangeDetectorRef) {}

  //Al iniciar o abrir la pagina
  ngOnInit(): void {
    this.getCategory();
  }

  // Logica select
  getCategory() : void {
    this.menuService.getCategories().subscribe({
      next: (data) => {
        this.listaCategorias = data;
        this.cdr.detectChanges();
      },
      error: (err) => console.error('Error al cargar categorías', err)
    });
  }

  onSeleccionChange(): void {
    console.log('ID seleccionado:', this.categoriaSeleccionadaId);
    // Aquí filtrarías tu tabla de platillos
  }

  //Logica de nuevo platillo
  newDessert(datos: any): void {
    if (datos.id) {
      // CASO EDICIÓN: El objeto ya existe en la DB
      console.log('Enviando PUT al servidor para el ID:', datos.id);
    } else {
      // CASO NUEVO: No tiene ID aún
      console.log('Enviando POST al servidor...');
    }
    this.showNewDessertForm = false;
  }

  //Abrir modal de platillos
  prepararNuevo() {
    this.platilloSeleccionado = null; // Limpiamos selección previa
    this.showNewDessertForm = true;
  }

  //Abrir modal de edicion
  prepararEdicion(platillo: any) {
    this.platilloSeleccionado = platillo; // Pasamos los datos del platillo
    this.showNewDessertForm = true;
  }

  obtenerNombreCategoria(idCategoria: number): string {
    const categoria = this.listaCategorias.find(cat => cat.id === idCategoria);
    return categoria ? categoria.name : 'Sin Categoría';
  }

  //Logica de nueva categoria
  newCategory(datos: any) : void {
    this.showNewCategoryForm = true;
    this.showNewCategoryForm = false;
    this.menuService.upsertCategory(datos).subscribe({
      next: (response) => {
        Swal.fire("Éxito", "Se ha guardado con éxito", 'success');
        this.getCategory();
      },
      error: (err) => {
        Swal.fire("Error", ERROR_CATALOG[err.error.code], 'error');
      }
    });

  }

  // Datos de ejemplo de platillos (simulando respuesta del servicio)
  platillos: any[] = [
    { id: 1, nombre: 'Ensalada César', descripcion: 'Lechuga fresca con aderezo', categoria: 1, precio: 120.00, estado: true },
    { id: 2, nombre: 'Pizza Kids', descripcion: 'Mini pizza de pepperoni', categoria: 2, precio: 85.00, estado: true },
    // ... más platillos
  ];

  // Variables para la paginación
  totalRegistros: number = 50;
  paginaActual: number = 1;
  registrosPorPagina: number = 10;

  editarPlatillo(id: number) {
    console.log('Editando platillo:', id);
  }

  eliminarPlatillo(id: number) {
    if(confirm('¿Estás seguro de eliminar este platillo?')) {
      console.log('Eliminando platillo:', id);
    }
  }

  cambiarPagina(nuevaPagina: number) {
    this.paginaActual = nuevaPagina;
    // Llamada al servicio con la nueva página
  }

}
