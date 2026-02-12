import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { LoginCredentials, LoginResponse } from '../interfaces/LoginRequest';
import { environment } from '../../environments/environment';
import { API_ENDPOINTS } from '../config/API_ENDPOINTS';

@Injectable({
  providedIn: 'root',
})
export class Auth {
  private http = inject(HttpClient);

  // Hacemos la petición real
  login(credentials: LoginCredentials): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(environment.apiUrlUser + API_ENDPOINTS.AUTH.LOGIN, credentials).pipe(
      tap(res => this.saveSession(res)) // Guardamos datos si la petición es exitosa
    );
  }

  // Guardamos la info vital en el navegador
  private saveSession(data: LoginResponse): void {
    localStorage.setItem('userId', data.userId);
    localStorage.setItem('roleName', data.roleName);
    localStorage.setItem('userName', data.userName);
    // Convertimos el array de permisos a texto para guardarlo
    localStorage.setItem('permissions', JSON.stringify(data.content));
  }

  // Métodos útiles para recuperar la info en otros componentes
  getUserId(): string | null {
    return localStorage.getItem('userId');
  }

  hasPermission(permission: string): boolean {
    const permissions = JSON.parse(localStorage.getItem('permissions') || '[]');
    return permissions.includes(permission);
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('userId'); // Devuelve true si existe el userId
  }

  logout(): void {
    localStorage.clear(); // Borra todo al salir
  }
}
