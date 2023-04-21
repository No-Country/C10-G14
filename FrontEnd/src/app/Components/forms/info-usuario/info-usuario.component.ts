import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { PerfilComponent } from '../../perfil/perfil.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EndpointsService } from 'src/app/Services/endpoints.service';
import { MetodosService } from 'src/app/Services/metodos.service';

import { User } from 'src/app/auth/interfaces/user';
import { AuthService } from 'src/app/auth/services/auth.service';
import { UserService } from 'src/app/auth/services/user.service';
import { first } from 'rxjs';

@Component({
  selector: 'app-info-usuario',
  templateUrl: './info-usuario.component.html',
  styleUrls: ['./info-usuario.component.css'],
})
export class InfoUsuarioComponent {
  form: FormGroup;
  api: string = this._endpoints.apiUrlUser;
  opcionesSexo: string[] = ['male', 'female'];
  objetivo!: string;
  medico!: string;
  previewImageUrl = '../../../../assets/img/image-placeholder.png';
  user$!: User;
  userProfile!: User;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef: MatDialogRef<PerfilComponent>,
    private fb: FormBuilder,
    private _endpoints: EndpointsService,
    private _metodoService: MetodosService,
    private authService: AuthService,
    private userService: UserService
  ) {
    this.user$ = <User>this.authService.userValue;
    this.form = this.fb.group({
      nombre: [
        '',
        [
          Validators.required,
          Validators.minLength(1),
          Validators.maxLength(20),
        ],
      ],
      meta: ['', [Validators.minLength(1), Validators.maxLength(20)]],
      indicacionMedica: [
        '',
        [Validators.minLength(1), Validators.maxLength(20)],
      ],
      sexo: [''],
      edad: ['', [Validators.required, Validators.pattern(/^[1-9]\d*$/)]],
      altura: [
        '',
        [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)],
      ],
      peso: [
        '',
        [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)],
      ],
      profileImg: [null],
    });

    this.user$ = <User>this.authService.userValue;
  }

  ngOnInit(): void {
    this.buscarCliente();
    this.notNull(this.data.objective, this.objetivo);
    this.notNull(this.data.medical_indication, this.medico);

    this.userService
      .getById(this.user$.id)
      .pipe(first())
      .subscribe((user) => {
        this.userProfile = user;
        if (this.userProfile && this.userProfile.profileImg) {
          this.previewImageUrl = `https://${this.userProfile.profileImg}`;
        }
      });
  }

  buscarCliente() {
    this.form.patchValue({
      id: this.data.id,
      nombre: this.data.name,
      peso: this.data.weight,
      altura: this.data.height,
      sexo: this.data.sex,
      edad: this.data.age,
      profileImg: this.data.profileImg,
      indicacionMedica: this.medico,
      meta: this.objetivo,
    });
  }

  cancelar() {
    this.dialogRef.close();
  }

  reloadPage() {
    location.reload();
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    this.form.get('profileImg')!.setValue(file);
    const reader = new FileReader();
    reader.onload = (e: any) => {
      this.previewImageUrl = e.target.result;
    };
    reader.readAsDataURL(file);
  }

  notNull(data: string, variable: string) {
    if (data !== null) {
      variable = data;
    }
  }

  EditInfoCliente() {
    const formData = new FormData();
    formData.append('name', this.form.get('nombre')?.value);
    formData.append(
      'medical_indication',
      this.form.get('indicacionMedica')?.value
    );
    formData.append('objective', this.form.get('meta')?.value);
    formData.append('sex', this.form.get('sexo')?.value);
    formData.append('age', this.form.get('edad')?.value);
    formData.append('height', this.form.get('altura')?.value);
    formData.append('weight', this.form.get('peso')?.value);

    // Obtener el archivo seleccionado del formulario
    const file = this.form.get('profileImg')?.value;

    // Agregar el archivo seleccionado a formData
    if (file) {
      formData.append('profileImg', file);
    }

    // Imprimir los datos de FormData por consola
    formData.forEach((value, key) => {});

    // Llamar al método de editar usuario
    this._endpoints
      .editarItem(this.user$.id, formData, this.api)
      .subscribe((data) => {
        this._metodoService.mensaje(
          'Información de Perfil editada con Exito !',
          3
        );
        this.reloadPage();
      });
    this.dialogRef.close(true);
  }
}
