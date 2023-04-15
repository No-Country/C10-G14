import { Component } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { Subject, takeUntil } from 'rxjs';
import { Router } from '@angular/router';
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
  constructor(
    public authService: AuthService,
    private formBuilder: NonNullableFormBuilder,
    private router: Router,
    private validatorsService: ValidatorsFormService
  ) {}

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]], // Campo de email con validadores
      password: ['', [Validators.required, Validators.minLength(6)]], // Campo de password con validadores
    });
  }
  // Obtiene el campo de email
  get email() {
    return this.loginForm.get('email');
  }

  // Obtiene el campo de contraseña
  get password() {
    return this.loginForm.get('password');
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

  // Envío del formulario
  login(): void {
    this.authService.login(this.loginForm.value).subscribe(
      (response) => {
        // Manejar la respuesta del servidor en caso de éxito
        this.authService.setAuthToken(response.token);
        // console.log('Inicio de sesión exitoso:', response);
        this.router.navigateByUrl('/inicio');
      },
      (error) => {
        // Manejar errores en caso de fallo
        // console.error('Error de inicio de sesión:', error);
        if (error && error.error && error.error.message) {
          Swal.fire({
            icon: 'error',
            title: 'Error desconocido',
            text: error.error.message,
          });
        } else {
          Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Nombre de usuario o contraseña incorrectos',
          });
        }
      }
    );
  }
}
