import { Component, inject } from '@angular/core';
import { Auth } from '../../services/auth';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'sideBar',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.css',
})
export class Sidebar {

  isClosed = false;

  toggleSidebar() {
    this.isClosed = !this.isClosed;
  }

  private authService = inject(Auth);
  private router = inject(Router);

  // Puedes obtener el nombre del usuario desde el localStorage
  userName = localStorage.getItem('userName') || 'Usuario';

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }


}
