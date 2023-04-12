import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EjercicioService {
  constructor(private http:HttpClient) { }

  obtenerDatos():Observable<any>{
    return this.http.get('./assets/Datos/Ejercicios.Json');
  }
}
