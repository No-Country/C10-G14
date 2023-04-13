import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Ejercicio } from 'src/app/Interface/ejercicio';
import { EndpointsService } from 'src/app/Services/endpoints.service';

@Component({
  selector: 'app-ver-ejercicios',
  templateUrl: './ver-ejercicios.component.html',
  styleUrls: ['./ver-ejercicios.component.css']
})
export class VerEjerciciosComponent {
  displayedColumns: string[] = ['nombre', 'tipo', 'unidad', 'acciones'];
  dataSource = new MatTableDataSource<Ejercicio>();
  api:string = this._endPointsService.apiUrlEjercicio;

  @ViewChild(MatPaginator) paginator!: MatPaginator
  @ViewChild(MatSort) sort!: MatSort;
  
  constructor(private _endPointsService:EndpointsService,    
    private router: Router
    ) { }

  ngOnInit(): void {
    this.obtenerEjercicios();
    
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    if(this.dataSource.data.length > 0) {
      this.paginator._intl.itemsPerPageLabel = 'Items por pagina'
    }
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  obtenerEjercicios() {    
    this._endPointsService.obtenerDatos(this.api).subscribe(data => {
      this.dataSource.data = data;
      console.log(data);
    })
  }

}
