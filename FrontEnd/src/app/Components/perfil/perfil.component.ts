import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { EndpointsService } from 'src/app/Services/endpoints.service';
import { MetodosService } from 'src/app/Services/metodos.service';
import { InfoUsuarioComponent } from '../forms/info-usuario/info-usuario.component';
import { AuthService } from 'src/app/auth/services/auth.service';
import { User } from 'src/app/auth/interfaces/user';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent {
  api:string = this._endPointsService.apiUrlUser;
  infoUsuario: any;
  loading:boolean = false;
  user:User;

  constructor(private _endPointsService:EndpointsService,
    private _metodos: MetodosService,    
    private router: Router,
    public dialog: MatDialog,
    private authService: AuthService
    
    ) {
      this.user = <User>this.authService.userValue;
      console.log('Esto es el user',this.user);
     }

  ngOnInit(): void {
    this.obtenerUsuario();
    
  }

  obtenerUsuario() {  
    this.loading = true;  
    this._endPointsService.obtenerDatosId(this.user.id, this.api).subscribe(data => {
      this.loading = false;
      this.infoUsuario = data;
      console.log('aca asigno la informacion que tengo que pasar',data);
    })
  }

  EditUsuario(data:any){        
    const dialogRef = this.dialog.open(InfoUsuarioComponent, {      
      width:"650px",
      disableClose: true,
      data
      
    });

    dialogRef.afterClosed().subscribe(result => {      
      if(result){
        setTimeout(() => {this.obtenerUsuario();}, 5000)
      }           
    });
  }

}
