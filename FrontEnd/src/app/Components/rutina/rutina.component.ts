import { Component, OnInit } from '@angular/core';
import { RutinaService } from 'src/app/Service/rutina.service';

@Component({
  selector: 'app-rutina',
  templateUrl: './rutina.component.html',
  styleUrls: ['./rutina.component.css']
})
export class RutinaComponent implements OnInit  {
  //miRutina:any;
  series:any;
  numero:number | undefined;
  
constructor(private datosRutina: RutinaService) { 
  

   }
  ngOnInit(): void {
   // this.datosRutina.obtenerDatos().subscribe(data =>{
      //console.log(data);
    //  this.miRutina=data;
     
    //});
    //this.numero = this.miRutina.dia1[0].series;
   // console.log(this.numero);
    this.series =  [...Array(8)];
    
   

  }

}  
