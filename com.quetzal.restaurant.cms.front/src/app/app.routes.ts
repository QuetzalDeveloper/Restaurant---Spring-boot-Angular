import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { NotFound } from './components/not-found/not-found';
import { MainLayout } from './components/main-layout/main-layout';
import { authGuard } from './guards/auth-guard';
import { PlatillosConsulta } from './components/platillos-consulta/platillos-consulta';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' }, // Si entras a la raíz, vas al login
  { path: 'login', component: Login },

  // Aquí irán tus módulos futuros (Platillos, Personal, etc.)
  // Por ahora, cualquier ruta que no sea "login" caerá en el Not Found

  // Rutas con Menú Lateral (Layout)
  {
    path: '',
    component: MainLayout, // Este componente tendrá el menú y el header
    canActivate: [authGuard],
    children: [
      { path: 'platillos', component: PlatillosConsulta },
      { path: 'usuarios', component: NotFound },
      { path: 'ordenes', component: NotFound },
      { path: 'ventas', component: NotFound },
      { path: '404', component: NotFound },
      { path: '', redirectTo: 'platillos', pathMatch: 'full' }
    ]
  },

  { path: '404', component: NotFound },
  { path: '**', redirectTo: 'platillos' }
];
