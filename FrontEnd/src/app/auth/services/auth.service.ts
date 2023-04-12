import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import jwtDecode from 'jwt-decode';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';
import { Credentials } from '../interfaces/auth.interfaces';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl: string = environment.baseUrl;
  private authTokenKey = 'authToken';

  constructor(private http: HttpClient) {}

  registerUser(creds: Credentials): Observable<any> {
    const body = creds;
    return this.http.post(`${this.baseUrl}/auth/register`, body);
  }

  login(creds: Credentials): Observable<any> {
    const body = creds;
    return this.http.post(`${this.baseUrl}/auth/authenticate`, body);
  }

  setAuthToken(token: string): void {
    localStorage.setItem(this.authTokenKey, token);
  }

  getAuthToken(): string | null {
    return localStorage.getItem(this.authTokenKey);
  }

  // Método para obtener la información del usuario a partir del token JWT
  decodeAuthToken(authToken: string): any {
    const decodedToken = jwtDecode(authToken);
    return decodedToken;
  }

  removeAuthToken(): void {
    localStorage.removeItem(this.authTokenKey);
  }

  logout() {
    localStorage.clear();
  }
}
