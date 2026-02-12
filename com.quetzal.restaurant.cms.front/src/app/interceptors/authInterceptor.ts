import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Auth } from '../services/auth';
import { ERROR_CATALOG } from '../config/ERROR_CATALOG';
import { catchError, throwError } from 'rxjs';
import Swal from 'sweetalert2';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(Auth);
  const userId = authService.getUserId();

  // Clonamos la petición para añadir el userId
  // Lo enviamos en los Headers, que es el estándar de industria
  if (userId) {
    const clonedRequest = req.clone({
      setHeaders: {
        'userId': userId // Nombre del header que esperará tu Spring Boot
      }
    });
    return next(clonedRequest).pipe(
      catchError((error: HttpErrorResponse) => {
        let meessage = ERROR_CATALOG['DEFAULT'];

        if (error.error && error.error.code) {
          // Buscamos el código que mandó Spring Boot en nuestro catálogo
          meessage = ERROR_CATALOG[error.error.code] || ERROR_CATALOG['DEFAULT'];
        }

        // Aquí puedes usar un servicio de alertas (como SweetAlert2 o un Toast)
        Swal.fire('Error', meessage, 'error');

        // Lanzamos el error con el mensaje traducido
        return throwError(() => meessage);
      })
    );
  }

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      let mensajeAmigable = ERROR_CATALOG['DEFAULT'];

      if (error.error && error.error.code) {
        // Buscamos el código que mandó Spring Boot en nuestro catálogo
        mensajeAmigable = ERROR_CATALOG[error.error.code] || ERROR_CATALOG['DEFAULT'];
      }

      // Aquí puedes usar un servicio de alertas (como SweetAlert2 o un Toast)
      Swal.fire('Error', mensajeAmigable, 'error');

      // Lanzamos el error con el mensaje traducido
      return throwError(() => mensajeAmigable);
    })
  );
};
