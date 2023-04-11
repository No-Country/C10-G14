import { Component, ViewEncapsulation } from '@angular/core';
import { AuthService } from 'src/app/auth/services/auth.service';
import { UserService } from 'src/app/auth/services/user.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class InicioComponent {
  user: any;
  users: any[] = [];
  constructor(
    private authService: AuthService,
    private userService: UserService
  ) {}

  ngOnInit() {
    const token = this.authService.getAuthToken(); // Utiliza el método getAuthToken() del servicio para obtener el token
    if (token) {
      this.user = this.authService.decodeAuthToken(token); // Utiliza el método getUserInfo() del servicio para decodificar el token
      console.log('EL USUARIO', this.user);
    }

    this.getUsersByRole('user'); // Llama a la función para obtener usuarios por rol al inicializar el componente
  }
  getUsersByRole(role: string) {
    this.userService.getUsersByRole(role).subscribe(
      (response) => {
        console.log(`Usuarios con rol ${role}:`, response);
        this.users = response;
      },
      (error) => {
        console.error(`Error al obtener usuarios por rol ${role}:`, error);
      }
    );
  }
}
