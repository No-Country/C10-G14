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
  // Variables
  hide = true;
  loading: boolean = false;
  destroyed$ = new Subject<void>();

  // Formulario de inicio de sesión
  loginForm: FormGroup = this.fb.group({
    email: ['', [Validators.required, this.validatorsService.emailValidator()]],
    password: ['', [Validators.required]],
  });

  // Constructor
  constructor(
    public authService: AuthService,
    private fb: NonNullableFormBuilder,
    private router: Router,
    private validatorsService: ValidatorsFormService
  ) {}

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
  onSubmit() {
    const { email, password } = this.loginForm.value;

    this.authService.login(email, password).subscribe((ok) => {
      if (ok === true) {
        this.router.navigateByUrl('/inicio');
      } else {
        Swal.fire('Error', ok.toString(), 'error');
      }
    });
  }
}
