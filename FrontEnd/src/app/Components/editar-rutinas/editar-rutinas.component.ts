import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormControl } from '@angular/forms';
import { RutinasComponent } from '../forms/rutinas/rutinas.component';
import { MatDialog } from '@angular/material/dialog';
import { EndpointsService } from 'src/app/Services/endpoints.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-editar-rutinas',
  templateUrl: './editar-rutinas.component.html',
  styleUrls: ['./editar-rutinas.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class EditarRutinasComponent implements OnInit  {  
  infoCliente:any;
  idCliente: number;
  api:string = this.datosRutina.apiUrlUser; 
  selected = new FormControl(0);
  planCliente: any ;
  
  
  constructor(private datosRutina: EndpointsService,
  private aRoute: ActivatedRoute) { 
    this.idCliente = Number(this.aRoute.snapshot.paramMap.get('id')) ; //obtenemos id de url
    
    
  }
  ngOnInit(): void {
    this.obtenerRutinas();  
}
//Metodo que hace un get al servidor para traer la info de un cliente por id
obtenerRutinas() {  
  this.datosRutina.obtenerDatosId(this.idCliente, this.api).subscribe(data => {    
    this.infoCliente = data;
    this.planCliente = data.routines;   
    
  })
}

}


