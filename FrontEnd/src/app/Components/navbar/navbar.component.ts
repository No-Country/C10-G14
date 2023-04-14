import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent {
  loading:boolean = false;
  estaLogueadoProfesor: boolean = true;

  constructor(private authService: AuthService, private router: Router) {}

  logout() {

    this.loading = true;
    setTimeout(() => {
           this.router.navigate(['/auth/login']);
        }, 1500);
  }
}
