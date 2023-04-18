import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { User } from '../interfaces/user';
import { BehaviorSubject, Observable, tap, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { Role } from '../interfaces/role';

@Injectable({ providedIn: 'root' })
export class UserService {
  private baseUrl: string = environment.baseUrl;
  private _user!: User;
  private userRolesSubject = new BehaviorSubject<Role[]>([]);

  constructor(private http: HttpClient, private authService: AuthService) {
    const user = this.authService.userValue;
    console.log('SOY EL USUARIO DESDE EL ADMIN', user);
  }

  getAll() {
    return this.http.get<User[]>(`${this.baseUrl}/users`);
  }

  /*   getAll(): Observable<User[]> {
    // Obtén el token de autorización del usuario. Supongamos que está almacenado en el servicio authService
    const user = this.authService.userValue;

    // Verifica si el usuario está autenticado y tiene un token válido
    if (user && user.token) {
      // Crea una cabecera HTTP con el token de autorización
      const headers = new HttpHeaders({
        Authorization: `Bearer ${user.token}`,
      });

      // Realiza la solicitud HTTP con la cabecera de autorización
      return this.http.get<User[]>(`${this.baseUrl}/users`, { headers });
    } else {
      // Si el usuario no está autenticado o no tiene un token válido, devuelve un error o realiza alguna otra acción
      // en consecuencia, por ejemplo, redirigir a la página de inicio de sesión.
      // Aquí puedes personalizar el comportamiento según tus necesidades.
      return throwError('Usuario no autenticado');
    }
  } */

  getById(id: string) {
    return this.http.get<User>(`${this.baseUrl}/users/${id}`);
  }

  getUser(): Observable<User> {
    return new Observable<User>((observer) => {
      const authToken = this.authService.getAuthToken();

      if (authToken) {
        const decodedToken = this.authService.decodeAuthToken(authToken);

        if (typeof decodedToken === 'object' && decodedToken !== null) {
          this._user = decodedToken as User;
          observer.next(this._user);
          observer.complete();
        } else {
          observer.error('El token decodificado no es del tipo esperado');
        }
      } else {
        observer.error('No se encontró un token de autenticación');
      }
    }).pipe(tap((user: User) => {}));
  }
}
