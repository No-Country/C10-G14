import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EndpointsService {
  private myAppUrl: string;
  apiUrlUser: string;
  apiUrlRutine: string;
  apiUrlUserRutine: string;
  // apiUrlHabilidades:string;
  // apiUrlProyectos:string;
  // apiUrlPersona: string;

  constructor(private http: HttpClient) {
    this.myAppUrl = 'https://api.worldfit.site/api/v1/';
    this.apiUrlUser = 'users';
    this.apiUrlRutine = 'routines';
    this.apiUrlUserRutine = 'users/routine';
  }

  //--------------Endpoints-----------------------------------

  //Obtener usuarios por roles
  public UsersRoles(role: string): Observable<any> {
    return this.http.get(this.myAppUrl + 'users/role/' + `${role}`);
  }

  //Obtener datos
  public obtenerDatos(apiUrlItem: string): Observable<any> {
    return this.http.get(this.myAppUrl + apiUrlItem);
  }

  //Obtener datos por id
  public obtenerDatosId(id: number, apiUrlItem: string): Observable<any> {
    return this.http.get(this.myAppUrl + apiUrlItem + `/${id}`);
  }
  //Eliminar
  public borrarItem(id: number, apiUrlItem: string): Observable<void> {
    return this.http.delete<void>(this.myAppUrl + apiUrlItem + `/${id}`);
  }
  //Crear
  public NuevoItem(objeto: any, apiUrlItem: string): Observable<any> {
    return this.http.post<any>(this.myAppUrl + apiUrlItem, objeto);
  }

  //Editar
  public editarItem(
    id: number,
    objeto: any,
    apiUrlItem: string
  ): Observable<void> {
    return this.http.put<void>(this.myAppUrl + apiUrlItem + `/${id}`, objeto);
  }
}
