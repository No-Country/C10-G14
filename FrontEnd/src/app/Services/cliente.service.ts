import { Injectable } from '@angular/core';
import { Cliente } from '../Interface/cliente';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private myAppUrl: string = './assets/Datos/Clientes.Json';
  

  constructor(private http: HttpClient) { }

  getclientes():Observable<any> {
    return this.http.get('./assets/Datos/Clientes.Json');
  }
 
  getcliente(id: number): Observable<any> {
    return this.http.get('./assets/Datos/Clientes.Json');
  }

  deletecliente(id: number): Observable<void> {
    return this.http.delete<void>('./assets/Datos/Clientes.Json');
  }

  addcliente(cliente: Cliente): Observable<any> {
    return this.http.post('./assets/Datos/Clientes.Json', cliente);
  }

  updatecliente(id: number, cliente: Cliente): Observable<void> {
    return this.http.put<void>('./assets/Datos/Clientes.Json', cliente);
  }
}


