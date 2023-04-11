import { Component } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { ValidatorsFormService } from '../../services/validators-form.service';
import { FormErrors } from '../../interfaces/form-errors.interface';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  // Variables
  hide = true;
  loading: boolean = false;
  destroyed$ = new Subject<void>();

  // Formulario de registro de usuario
  registerForm: FormGroup = this.fb.group({
    name: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]],
    sex: ['MALE', [Validators.required]],
  });

  // Constructor
  constructor(
    public authService: AuthService,
    private fb: NonNullableFormBuilder,
    private router: Router,
    private validatorsService: ValidatorsFormService
  ) {}

  // Obtiene el campo de nombre
  get name() {
    return this.registerForm.get('name');
  }

  // Obtiene el campo de email
  get email() {
    return this.registerForm.get('email');
  }

  // Obtiene el campo de contraseña
  get password() {
    return this.registerForm.get('password');
  }

  get sex() {
    return this.registerForm.get('sex');
  }

  // Comprueba si un campo del formulario es inválido
  isFieldInvalid(field: string): boolean {
    const control = this.registerForm.get(field)!;
    return (
      control?.hasError('required') ||
      control?.hasError('invalidName') ||
      control?.hasError('invalidEmail') ||
      (control?.hasError('invalidPassword') && control?.touched) ||
      (control?.hasError('invalidConfirmPassword') && control?.touched)
    );
  }

  // Errores de formulario
  formErrors: FormErrors = {
    required: 'Este campo es obligatorio',
    invalidName: 'El nombre no es válido',
    invalidEmail: 'El email no es válido',
    invalidPassword:
      'La contraseña debe tener al menos 6 caracteres y contener mayúsculas, minúsculas, números y caracteres especiales.',
  };

  // Obtiene el mensaje de error para un campo del formulario
  getErrorMessage(field: string): string {
    const control = this.registerForm.get(field);
    if (control?.touched) {
      if (control?.errors) {
        const errorKey = Object.keys(control.errors)[0];
        return this.formErrors[errorKey];
      }
    }
    return '';
  }

  // Envío del formulario
  register() {
    // Llama al método de registro en el servicio AuthService
    this.authService.registerUser(this.registerForm.value).subscribe(
      (response) => {
        // Manejar la respuesta exitosa del servidor
        this.authService.setAuthToken(response.token);
        console.log('Usuario registrado con éxito:', response);
        this.router.navigateByUrl('/inicio');
        // ... realizar acciones adicionales después del registro exitoso ...
      },
      (error) => {
        // Manejar errores de registro
        console.error('Error al registrar usuario:', error);
        // ... realizar acciones adicionales en caso de error ...
      }
    );
  }
  // onSubmit() {
  //   const { name, email, password } = this.registerForm.value;
  //   this.authService.register(name, email, password).subscribe((ok) => {
  //     if (ok === true) {
  //       Swal.fire('Exito!', ok.toString(), 'success');
  //       this.router.navigateByUrl('/inicio');
  //     } else {
  //       Swal.fire('Error', ok.toString(), 'error');
  //     }
  //   });
  // }
}
