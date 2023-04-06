import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RutinaComponent } from './Components/rutina/rutina.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { SharedModule } from './Shared/shared.module';
import { InicioComponent } from './Components/inicio/inicio.component';
import { LoginComponent } from './auth/pages/login/login.component';
import { VerClientesComponent } from './Components/ver-clientes/ver-clientes.component';
import { EditarRutinasComponent } from './Components/editar-rutinas/editar-rutinas.component';
import { EditarEjercicioComponent } from './Components/editar-ejercicio/editar-ejercicio.component';
import { RutinasComponent } from './Components/forms/rutinas/rutinas.component';

@NgModule({
  declarations: [AppComponent, RutinaComponent, InicioComponent, VerClientesComponent, EditarRutinasComponent, EditarEjercicioComponent, RutinasComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    SharedModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
