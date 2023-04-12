import { EventEmitter, Injectable, Output } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MetodosService {
  @Output() obtenerIdClientes: EventEmitter<any> = new EventEmitter();

  constructor() { }
}
