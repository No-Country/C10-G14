import { Component } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { Subject, takeUntil } from 'rxjs';
import { HotToastService } from '@ngneat/hot-toast';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { ValidatorsFormService } from '../../services/validators-form.service';
import { FormErrors } from '../../interfaces/form-errors.interface';

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
    password: [
      '',
      [Validators.required, this.validatorsService.passwordStrengthValidator()],
    ],
  });

  // Constructor
  constructor(
    public authService: AuthService,
    private fb: NonNullableFormBuilder,
    private toast: HotToastService,
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

  // Inicio de sesión
  private login(email: string, password: string): void {
    this.authService
      .login(email, password)
      .pipe(
        this.toast.observe({
          success: 'Inicio de sesión correcto',
          loading: 'Entrando...',
          error: ({ message }) => `Ha ocurrido un error: ${message} `,
        }),
        takeUntil(this.destroyed$)
      )
      .subscribe(() => {
        this.loginForm.reset();
        this.loading = true;
        // this.router.navigate(['#']);
      });
  }

  // Envío del formulario
  onSubmit(): void {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      return;
    }

    const email = this.loginForm.value.email;
    const password = this.loginForm.value.password;
    //TODO: Eliminar el console.log
    console.log('Datos del formulario:', email, password);
    this.login(email, password);
  }

  // Se ejecuta cuando se destruye el componente
  ngOnDestroy() {
    this.destroyed$.next();
    this.destroyed$.complete();
  }
}
