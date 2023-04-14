import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VerClientesComponent } from './Components/ver-clientes/ver-clientes.component';
import { EditarRutinasComponent } from './Components/editar-rutinas/editar-rutinas.component';
import { InicioComponent } from './Components/inicio/inicio.component';
import { VerEjerciciosComponent } from './Components/ver-ejercicios/ver-ejercicios.component';
import { PageErrorComponent } from './Shared/page-error/page-error.component';
import { PageUserComponent } from './Shared/page-user/page-user.component';

const routes: Routes = [
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then((m) => m.AuthModule),
  },
  {
    path: 'clientes',
    component: VerClientesComponent,
  },
  {
    path: 'inicio',
    component: InicioComponent,
  },
  {
    path: 'editar-rutinas',
    component: EditarRutinasComponent,
  },
  {
    path: 'ejercicios',
    component: VerEjerciciosComponent,
  },
  {
    path: '',
    redirectTo: 'auth/login',
    pathMatch: 'full',
  },
  {
    path: 'user',
    component: PageUserComponent,
  },
  {
    path: '**',
    component: PageErrorComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
