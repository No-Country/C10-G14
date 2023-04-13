import { EventEmitter, Injectable, Output } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MetodosService {
  
  @Output() obtenerInfoCliente: EventEmitter<any> = new EventEmitter();

  constructor() { }

  
}
