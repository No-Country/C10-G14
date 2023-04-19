import { Component } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  NonNullableFormBuilder,
  Validators,
} from '@angular/forms';
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
    name: ['juan', [Validators.required]],
    email: ['registro@mail.com', [Validators.required, Validators.email]],
    password: ['123456789', [Validators.required, Validators.minLength(6)]],
    sex: ['MALE', [Validators.required]],
    age: [38, [Validators.required, Validators.pattern(/^[1-9]\d*$/)]],
    height: [
      180,
      [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)],
    ],
    weight: [
      80,
      [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)],
    ],
    profileImg: new FormControl(null, {
      validators: [
        (control: AbstractControl): { [key: string]: any } | null => {
          const file = control.value as File;
          const allowedTypes = ['image/jpeg', 'image/png', 'image/gif'];
          if (file && !allowedTypes.includes(file.type)) {
            return { invalidImageType: true };
          }
          return null;
        },
      ],
    }),
  });

  // Constructor
  constructor(
    public authService: AuthService,
    private fb: NonNullableFormBuilder,
    private router: Router,
    private validatorsService: ValidatorsFormService
  ) {}

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
    invalidImageType:
      'El tipo de archivo no es válido. Los formatos permitidos son: jpeg, png y gif.',
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

  onFileSelected(event: any) {
    const file = event.target.files[0];
    this.registerForm.get('profileImg')!.setValue(file);
  }

  // Envío del formulario
  register() {
    // Obtener los valores del formulario
    const formData = new FormData();
    formData.append('name', this.registerForm.get('name')!.value);
    formData.append('email', this.registerForm.get('email')!.value);
    formData.append('password', this.registerForm.get('password')!.value);
    formData.append('sex', this.registerForm.get('sex')!.value);
    formData.append('age', this.registerForm.get('age')!.value);
    formData.append('height', this.registerForm.get('height')!.value);
    formData.append('weight', this.registerForm.get('weight')!.value);

    // Obtener el archivo seleccionado del formulario
    const file = this.registerForm.get('profileImg')!.value;

    // Agregar el archivo seleccionado a formData
    if (file) {
      formData.append('profileImg', file);
    }

    // Imprimir los datos de FormData por consola
    formData.forEach((value, key) => {
      console.log(key, value);
    });

    // Llamar al método de registro con los datos del formulario y el archivo
    this.authService.register(formData).subscribe(
      (response) => {
        // Manejar la respuesta exitosa del servidor
        this.authService.setAuthToken(response.token);
        this.loading = true;
        setTimeout(() => {
          this.router.navigate(['/inicio']);
        }, 1500);
      },
      (error) => {
        // Manejar errores en caso de fallo
        if (error && error.error && error.error.message) {
          Swal.fire({
            icon: 'error',
            title: 'Error al registrar usuario',
            text: error.error.message,
          });
        }
      }
    );
  }
}
