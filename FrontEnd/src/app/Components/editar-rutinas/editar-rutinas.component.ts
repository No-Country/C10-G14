import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { Rutina } from 'src/app/Interface/rutina';
import { RutinaService } from 'src/app/Services/rutina.service';

@Component({
  selector: 'app-editar-rutinas',
  templateUrl: './editar-rutinas.component.html',
  styleUrls: ['./editar-rutinas.component.css']
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
      console.log(this.dataSource);
    })
  }
  

}
