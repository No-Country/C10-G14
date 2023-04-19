import { Component } from '@angular/core';

@Component({
  selector: 'app-cronometro',
  templateUrl: './cronometro.component.html',
  styleUrls: ['./cronometro.component.css']
})
export class CronometroComponent {
  contador: number = 0;
  milisegundos: number = 0;
  intervalo: any = null;

  empezar() {
    if (!this.intervalo) {
      this.intervalo = setInterval(() => {
        this.milisegundos++;
        if (this.milisegundos == 100) {
          this.milisegundos = 0;
          this.contador++;
        }
      }, 10);
    }
  }

  parar() {
    clearInterval(this.intervalo);
    this.intervalo = null;
  }

  reiniciar() {
    this.contador = 0;
    this.milisegundos = 0;
    clearInterval(this.intervalo);
    this.intervalo = null;
  }
}

