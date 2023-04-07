import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Rutina } from 'src/app/Interface/rutina';
@Component({
  selector: 'app-rutinas',
  templateUrl: './rutinas.component.html',
  styleUrls: ['./rutinas.component.css']
})

export class RutinasComponent  {
  
  
  constructor(
    public dialogRef: MatDialogRef<RutinasComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Rutina,
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
  
}
