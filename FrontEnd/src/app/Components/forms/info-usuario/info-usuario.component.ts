import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { PerfilComponent } from '../../perfil/perfil.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EndpointsService } from 'src/app/Services/endpoints.service';
import { MetodosService } from 'src/app/Services/metodos.service';

import { Cliente } from 'src/app/Interface/cliente';
import { User } from 'src/app/auth/interfaces/user';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
  selector: 'app-info-usuario',
  templateUrl: './info-usuario.component.html',
  styleUrls: ['./info-usuario.component.css']
})
export class InfoUsuarioComponent {
  form: FormGroup;
  idCliente:number;  
  loading:boolean = false;    
  api:string = this._endpoints.apiUrlUser;
  user:User;
  

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef: MatDialogRef<PerfilComponent>,    
    private fb : FormBuilder,
    private _endpoints:EndpointsService,
    private _metodoService: MetodosService,
    private authService: AuthService) { 
      this.user = <User>this.authService.userValue;
      console.log('Esto es el user',this.user);      
      this.form= this.fb.group({
        nombre:['',[Validators.required,Validators.minLength(1),Validators.maxLength(20)]],
        edad:['', [Validators.required, Validators.pattern(/^[1-9]\d*$/)]],      
        altura:['',[Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)]],
        peso:['',[Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)]],
        sexo:['',Validators.required]        
      })
      console.log(data);
      this.idCliente = this.data.id;

    }

    ngOnInit(): void {      
      this.buscarCliente();
    }

    buscarCliente(){
      this._endpoints.obtenerDatosId(this.idCliente, this.api).subscribe(data =>{
        this.form.patchValue({   
          
          id:data.id,
          nombre:data.name,
          peso:data.weight,
          altura:data.height,
          sex:data.sex,
          edad:data.age,
          profileImg:data.profileImg,
          indicacionMedica:data.medical_indication,
          meta: data.objective,
          
        });
        
  
      });
    }

    cancelar() {
      this.dialogRef.close();
    }


    EditInfoCliente() {
      const cliente: Cliente = {                
        name:this.form.get('nombre')?.value,
        profileImg:this.form.get('profesion')?.value,
        weight:this.form.get('sobre_mi')?.value,
        height:this.form.get('sobre_mi')?.value,
        sex:this.form.get('linkedin')?.value,
        age:this.form.get('linkedin')?.value,
        
      };     
      
      this.loading = true;
  
      this._endpoints.editarItem(this.user.id, cliente, this.api).subscribe(data => {        
        this._metodoService.mensaje('Información de Perfil editada con Exito !', 2);
      })
      this.loading = false;
      this.dialogRef.close(true);
    }
}
