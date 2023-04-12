import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RutinaComponent } from './Components/rutina/rutina.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { SharedModule } from './Shared/shared.module';
import { InicioComponent } from './Components/inicio/inicio.component';
import { VerClientesComponent } from './Components/ver-clientes/ver-clientes.component';
import { EditarRutinasComponent } from './Components/editar-rutinas/editar-rutinas.component';
import { EditarEjercicioComponent } from './Components/editar-ejercicio/editar-ejercicio.component';
import { RutinasComponent } from './Components/forms/rutinas/rutinas.component';
import { NavbarComponent } from './Components/navbar/navbar.component';
import { VerEjerciciosComponent } from './Components/ver-ejercicios/ver-ejercicios.component';
import { InterceptorService } from './auth/services/user.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    RutinaComponent,
    InicioComponent,
    VerClientesComponent,
    EditarRutinasComponent,
    EditarEjercicioComponent,
    NavbarComponent,
    VerEjerciciosComponent,
  ],
  declarations: [AppComponent, RutinaComponent, InicioComponent, VerClientesComponent, EditarRutinasComponent, EditarEjercicioComponent, RutinasComponent, NavbarComponent, VerEjerciciosComponent],


  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    SharedModule,
    HttpClientModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
