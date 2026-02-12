import { Component, inject } from '@angular/core';
import { Router } from '@angular/router'; // Para navegar tras el login
import { Auth } from '../../services/auth';
import { LoginCredentials } from '../../interfaces/LoginRequest';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  private authService = inject(Auth);
  private router = inject(Router);

  onLogin(userValue: string, passValue: string) {
    const capsula: LoginCredentials = {
      user: userValue,
      password: passValue
    };

    this.authService.login(capsula).subscribe({
      next: (response) => {
        console.log('Usuario autenticado:', response.userId);
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {

      }
    });
  }
}
