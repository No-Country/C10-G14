import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormControl } from '@angular/forms';
import { RutinaService } from 'src/app/Services/rutina.service';
import { RutinasComponent } from '../forms/rutinas/rutinas.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-editar-rutinas',
  templateUrl: './editar-rutinas.component.html',
  styleUrls: ['./editar-rutinas.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class EditarRutinasComponent implements OnInit  {
  
  rutinasLista:any;
 
  selected = new FormControl(0);
  dataSource: any;
  
  constructor(private datosRutina: RutinaService) { }
  ngOnInit(): void {
    this.datosRutina.obtenerDatos().subscribe((data) => {
    
      this.rutinasLista = data.usuario1.routines;      
    
      
    });
    this.obtenerRutinas(1)
  }
 
  obtenerRutinas(idRutina: number) {

    
    this.datosRutina.obtenerDatos().subscribe(data => {
      this.dataSource = data.usuario1.routines[idRutina].exercises;
      // console.log(this.dataSource);
    })
  }
  
  
}
