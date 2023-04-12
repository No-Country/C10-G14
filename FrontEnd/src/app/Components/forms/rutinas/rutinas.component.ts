import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog } from '@angular/material/dialog';
import { EjercicioR } from 'src/app/Interface/ejercicio';
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
      exerciseId:['',Validators.required],
      quantity:['',Validators.required],
      repetitions:['',Validators.required],
      series:['',Validators.required],
      
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
  addEjercicio(){
    
    const ejercicio:EjercicioR= {
      exerciseId:this.form.value.exerciseId,
      quantity:this.form.value.quantity,
      repetitions:this.form.value.repetitions,
      series:this.form.value.series
    }
    console.log(ejercicio)
  }
}
