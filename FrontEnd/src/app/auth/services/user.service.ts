import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl: string = environment.baseUrl;
  constructor(private http: HttpClient, private authService: AuthService) {}

  getUsersByRole(role: string): Observable<any[]> {
    const authToken = this.authService.getAuthToken(); // Obtiene el token del usuario autenticado desde el AuthService
    const headers = new HttpHeaders({
      Authorization: `Bearer ${authToken}`, // Agrega el token de autenticación en los headers de la petición
    });

    // Realiza la petición HTTP para obtener los usuarios por rol con el token de autenticación en los headers
    return this.http.get<any[]>(`${this.baseUrl}/users/role/${role}`, {
      headers,
    });
  }
}
