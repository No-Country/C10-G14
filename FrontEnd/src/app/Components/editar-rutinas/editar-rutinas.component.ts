import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormControl } from '@angular/forms';
import { RutinasComponent } from '../forms/rutinas/rutinas.component';
import { MatDialog } from '@angular/material/dialog';
import { MetodosService } from 'src/app/Services/metodos.service';
import { EndpointsService } from 'src/app/Services/endpoints.service';

@Component({
  selector: 'app-editar-rutinas',
  templateUrl: './editar-rutinas.component.html',
  styleUrls: ['./editar-rutinas.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class EditarRutinasComponent implements OnInit  {  
  infoCliente:any;
  idCliente: number = 0;
  api:string = this.datosRutina.apiUrlUser; 
  selected = new FormControl(0);
  planCliente: any ;
  
  
  constructor(private datosRutina: EndpointsService,
  private _metodosServices: MetodosService) { }
  ngOnInit(): void {
    this._metodosServices.obtenerIdClientes.subscribe(data => {
      console.log('Recibiendo data..', data);
      this.idCliente = data;
      console.log('Recibiendo data..', this.idCliente);
    })
      
    this.obtenerRutinas(4);  
}

obtenerRutinas(id:number) {  
  this.datosRutina.obtenerDatosId(id, this.api).subscribe(data => {
    
    this.infoCliente = data;
    this.planCliente = data.routines;    
    
    
  })
}

}