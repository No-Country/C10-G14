import { MatTableDataSource } from '@angular/material/table';
import { Cliente } from 'src/app/Interface/cliente';
import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { ClienteService } from 'src/app/Services/cliente.service';






@Component({
  selector: 'app-ver-clientes',
  templateUrl: './ver-clientes.component.html',
  styleUrls: ['./ver-clientes.component.css']
})
export class VerClientesComponent {
  displayedColumns: string[] = ['nombre', 'edad', 'altura', 'sexo', 'peso', 'acciones'];
  dataSource = new MatTableDataSource<Cliente>();
  
  
  @ViewChild(MatPaginator) paginator!: MatPaginator
  @ViewChild(MatSort) sort!: MatSort;
  
  constructor(private _clienteService:ClienteService) { }

  ngOnInit(): void {
    this.obtenerClientes();
    
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

  obtenerClientes() {

    
    this._clienteService.getclientes().subscribe(data => {
      this.dataSource.data = data;
      console.log(data);
    })
  }
  

}