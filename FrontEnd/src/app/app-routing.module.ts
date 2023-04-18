import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VerClientesComponent } from './Components/ver-clientes/ver-clientes.component';
import { EditarRutinasComponent } from './Components/editar-rutinas/editar-rutinas.component';
import { InicioComponent } from './Components/inicio/inicio.component';
import { VerEjerciciosComponent } from './Components/ver-ejercicios/ver-ejercicios.component';
import { PerfilComponent } from './Components/perfil/perfil.component';
import { PageErrorComponent } from './page-error/page-error.component';
import { AuthGuard } from './guards/auth.guard';
import { Role } from './auth/interfaces/role';
import { PageUserComponent } from './page-user/page-user.component';

const routes: Routes = [
  {
    path: '',
    component: PageUserComponent,
  },
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then((m) => m.AuthModule),
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'auth/login',
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
    path: 'editar-rutinas/:id',
    component: EditarRutinasComponent,
  },
  {
    path: 'ejercicios',
    component: VerEjerciciosComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
