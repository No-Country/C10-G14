import { Component, ViewChild, Input, ViewEncapsulation } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Rutina } from 'src/app/Interface/rutina';
import { ClienteService } from 'src/app/Services/cliente.service';
import { RutinaService } from 'src/app/Services/rutina.service';

@Component({
  selector: 'app-editar-ejercicio',
  templateUrl: './editar-ejercicio.component.html',
  styleUrls: ['./editar-ejercicio.component.css'],
  encapsulation: ViewEncapsulation.None,
})

export class EditarEjercicioComponent {
  @Input() dataIdRutinas:number = 0;
  displayedColumns: string[] = ['title', 'numeroSeries', 'repetition','type', 'quantity', 'unit', 'acciones'];
  dataSource = new MatTableDataSource<Rutina>();
  
  
  @ViewChild(MatPaginator) paginator!: MatPaginator
  @ViewChild(MatSort) sort!: MatSort;
  constructor(private datosRutina:RutinaService) { }

  ngOnInit(): void {
    this.obtenerRutinas(this.dataIdRutinas);
    console.log(this.dataIdRutinas);
    
  }
  obtenerRutinas(idRutina: number) {

    
    this.datosRutina.obtenerDatos().subscribe(data => {
      this.dataSource = data.usuario1.routines[idRutina].exercises;
      // console.log(this.dataSource);
    })
  }
}
