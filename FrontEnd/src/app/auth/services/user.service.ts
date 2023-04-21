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
    
  }

  getAll() {
    return this.http.get<User[]>(`${this.baseUrl}/users`);
  }

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
