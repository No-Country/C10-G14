import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VerClientesComponent } from './Components/ver-clientes/ver-clientes.component';
import { EditarRutinasComponent } from './Components/editar-rutinas/editar-rutinas.component';
import { InicioComponent } from './Components/inicio/inicio.component';
import { VerEjerciciosComponent } from './Components/ver-ejercicios/ver-ejercicios.component';

const routes: Routes = [
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then((m) => m.AuthModule),
  }, 
  {
    path: 'clientes',
    component:VerClientesComponent,
  },
  {
    path: 'inicio',
    component:InicioComponent,
  },
  {
    path: 'editar-rutinas/:id',
    component:EditarRutinasComponent,
  },
  {
    path: 'ejercicios',
    component:VerEjerciciosComponent,
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'auth/login',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
