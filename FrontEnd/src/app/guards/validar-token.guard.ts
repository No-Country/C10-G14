import { Injectable } from '@angular/core';
import { AuthService } from '../auth/services/auth.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class ValidarTokenGuard {
  constructor(private authService: AuthService, private router: Router) {}
}
