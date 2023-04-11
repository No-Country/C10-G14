import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';

import { map, of, catchError, Observable, tap } from 'rxjs';
import {
  AuthResponse,
  Credentials,
  Usuario,
} from '../interfaces/auth.interfaces';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl: string = environment.baseUrl;
  private authTokenKey = 'authToken';
  private _usuario!: Usuario;

  get usuario() {
    return { ...this._usuario };
  }

  constructor(private http: HttpClient) {}

  register(name: Usuario, email: Usuario, password: Usuario): Observable<any> {
    const URL = `${this.baseUrl}/auth/new`;
    const body = { name, email, password };
    return this.http.post<AuthResponse>(URL, body).pipe(
      tap(({ ok, token }) => {
        if (ok) {
          localStorage.setItem('token', token!);
        }
      }),
      map(({ ok }) => ok),
      catchError((err) => of(err.error.msg))
    );
  }

  registerUser(creds: Credentials): Observable<any> {
    const body = creds;
    return this.http.post(`${this.baseUrl}/auth/register`, body);
  }

  login(creds: Credentials): Observable<any> {
    const body = creds;
    return this.http.post(`${this.baseUrl}/auth/authenticate`, body);
  }

  getToken(creds: Credentials) {
    return localStorage.getItem('token');
  }

  setAuthToken(token: string): void {
    localStorage.setItem(this.authTokenKey, token);
  }

  getAuthToken(): string | null {
    return localStorage.getItem(this.authTokenKey);
  }

  removeAuthToken(): void {
    localStorage.removeItem(this.authTokenKey);
  }

  getUserInfo(authToken: string): Observable<any> {
    const headers = new HttpHeaders({
      Authorization: `Bearer ${authToken}`, // Agregar el token de autenticación en los headers de la petición
    });

    return this.http.get(`${this.baseUrl}/user-info`, { headers }); // Cambiar la URL y la estructura de la petición según tu API
  }

  // validateToken(): Observable<boolean> {
  //   const URL = `${this.baseUrl}/auth/renew`;
  //   const headers = new HttpHeaders().set(
  //     'x-token',
  //     localStorage.getItem('token') || ''
  //   );
  //   return this.http.get<AuthResponse>(URL, { headers }).pipe(
  //     map((resp) => {
  //       localStorage.setItem('token', resp.token!);
  //       this._usuario = {
  //         name: resp.name!,
  //         uid: resp.uid!,
  //         email: resp.email!,
  //       };
  //       return resp.ok;
  //     }),
  //     catchError((err) => of(false))
  //   );
  // }

  logout() {
    localStorage.clear();
  }
}
