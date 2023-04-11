import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Rutina } from 'src/app/Interface/rutina';
import { EjercicioService } from 'src/app/Services/ejercicio.service';
@Component({
  selector: 'app-rutinas',
  templateUrl: './rutinas.component.html',
  styleUrls: ['./rutinas.component.css']
})

export class RutinasComponent implements OnInit {
  form: FormGroup
  Ejercicios: any
  
  constructor(
    public dialogRef: MatDialogRef<RutinasComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Rutina,
    public EjerciciosS: EjercicioService,
    private fb: FormBuilder
  ) {
    this.form = fb.group({
      title:[''],
      quantity:[''],
      repetitions:[''],
      series:[''],
      
    })
    
  }
  ngOnInit(): void {
    this.EjerciciosS.obtenerDatos().subscribe((data) => {
     
      this.Ejercicios = data      
     
      console.log(this.Ejercicios)
    });
  }
  
  onNoClick(): void {
    this.dialogRef.close();
  }
  
}
