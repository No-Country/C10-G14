import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Rutina } from 'src/app/Interface/rutina';
import { RutinaService } from 'src/app/Services/rutina.service';
import { Dialog } from '@angular/cdk/dialog';
@Component({
  selector: 'app-rutinas',
  templateUrl: './rutinas.component.html',
  styleUrls: ['./rutinas.component.css']
})
export class RutinasComponent {
  constructor(){}
 
  ngOnInit():void{}
}
