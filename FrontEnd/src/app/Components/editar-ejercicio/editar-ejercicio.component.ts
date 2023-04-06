import { Component, ViewChild, Input } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Rutina } from 'src/app/Interface/rutina';
import { ClienteService } from 'src/app/Services/cliente.service';
import { RutinaService } from 'src/app/Services/rutina.service';
import { RutinasComponent } from '../forms/rutinas/rutinas.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-editar-ejercicio',
  templateUrl: './editar-ejercicio.component.html',
  styleUrls: ['./editar-ejercicio.component.css']
})

export class EditarEjercicioComponent {
  @Input() dataIdRutinas:number = 0;
  displayedColumns: string[] = ['title', 'numeroSeries', 'repetition', 'quantity', 'unit','type' , 'acciones'];
  dataSource = new MatTableDataSource<Rutina>();
  
  
  @ViewChild(MatPaginator) paginator!: MatPaginator
  @ViewChild(MatSort) sort!: MatSort;
  constructor(private datosRutina:RutinaService,
    public dialog: MatDialog) { }

  ngOnInit(): void {
    this.obtenerRutinas(this.dataIdRutinas);
    
    
  }
  obtenerRutinas(idRutina: number) {

    
    this.datosRutina.obtenerDatos().subscribe(data => {
      this.dataSource = data.usuario1.routines[idRutina].exercises;
      // console.log(this.dataSource);
    })
  }
  openDialog() {
    const dialogRef = this.dialog.open(RutinasComponent,{
      width: '100vw',
      disableClose: true
    });

    
  }
}

