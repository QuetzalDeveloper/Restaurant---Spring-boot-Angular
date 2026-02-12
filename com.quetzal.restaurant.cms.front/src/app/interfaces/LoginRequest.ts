export interface LoginResponse {
  content: string[];       // Lista de permisos (CREATE_USER, etc.)
  role: number;            // ID del rol
  roleName: string;        // Nombre del rol (ROLE_SUPER_USER)
  userId: string;          // UUID del usuario
  userName: string;
}

export interface LoginCredentials {
  user: string;
  password: string;
}
