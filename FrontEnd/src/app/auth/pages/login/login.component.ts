/* import { Component } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { Subject, first, takeUntil } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { ValidatorsFormService } from '../../services/validators-form.service';
import { FormErrors } from '../../interfaces/form-errors.interface';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginForm!: FormGroup; // Declaración del FormGroup
  error: string = '';
  submitted = false;
  loading: boolean = false;

  constructor(
    public authService: AuthService,
    private formBuilder: NonNullableFormBuilder,
    // private validatorsService: ValidatorsFormService
    private route: ActivatedRoute,
    private router: Router
  ) {
    // Redirigir a la página de inicio si ya se ha iniciado sesión
    if (this.authService.userValue) {
      this.router.navigate(['/home']);
    }
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['juan@juan.com', [Validators.required, Validators.email]], // Campo de email con validadores
      password: ['pruebas3', [Validators.required, Validators.minLength(8)]], // Campo de password con validadores
    });
  }

  // Comprueba si un campo del formulario es inválido
  isFieldInvalid(field: string): boolean {
    const control = this.loginForm.get(field)!;
    return (
      control?.hasError('required') ||
      control?.hasError('invalidEmail') ||
      (control?.hasError('invalidPassword') && control?.touched)
    );
  }

  // Errores de formulario
  formErrors: FormErrors = {
    required: 'Este campo es obligatorio',
    invalidEmail: 'El email no es válido',
    invalidPassword:
      'La contraseña debe tener al menos 8 caracteres, incluyendo una letra mayúscula, una letra minúscula, un número y un carácter especial',
  };

  // Obtiene el mensaje de error para un campo del formulario
  getErrorMessage(field: string): string {
    const control = this.loginForm.get(field);
    if (control?.touched) {
      if (control?.errors) {
        const errorKey = Object.keys(control.errors)[0];
        return this.formErrors[errorKey];
      }
    }

    return '';
  }

  login() {
    this.submitted = true;

    // Detener simel formulario es invalido
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.authService
      .login(this.loginForm.value)
      .pipe(first())
      .subscribe({
        next: () => {
          // obtener la URL de retorno de los parámetros de consulta o la página de inicio predeterminada
          const returnUrl =
            this.route.snapshot.queryParams['returnUrl'] || '/home';
          this.loading = true;
          this.router.navigateByUrl(returnUrl);
        },
        error: (error) => {
          this.error = error;
          this.loading = false;
          const Toast = Swal.mixin({
            toast: true,
            position: 'center',
            showConfirmButton: false,
            timer: 2500,
          });
          Toast.fire({
            icon: 'error',
            title: this.error,
          });
        },
      });
  }
}
 */

import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import Swal from 'sweetalert2';
import { AuthService } from '../../services/auth.service';

@Component({ templateUrl: 'login.component.html' })
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  loading = false;
  submitted = false;
  error = '';

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService
  ) {
    // redirect to home if already logged in
    if (this.authService.userValue) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]], // Campo de email con validadores
      password: ['', [Validators.required, Validators.minLength(8)]], // Campo de password con validadores
    });
  }

  onSubmit() {
    this.submitted = true;

    // Detener simel formulario es
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.authService
      .login(this.loginForm.value)
      .pipe(first())
      .subscribe({
        next: () => {
          // get return url from query parameters or default to home page
          const returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
          this.router.navigateByUrl(returnUrl);
        },
        error: (error) => {
          this.error = error;
          this.loading = false;

          const Toast = Swal.mixin({
            toast: true,
            position: 'center',
            showConfirmButton: false,
            timer: 2500,
          });
          Toast.fire({
            icon: 'error',
            title: this.error,
          });
        },
      });
  }
}
