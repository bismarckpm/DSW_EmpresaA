import { DatePipe } from '@angular/common';
import { Component, Inject, OnInit, Output } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Estudio } from 'src/app/interfaces/estudio';


@Component({
  selector: 'app-dialogconsultarestudio',
  templateUrl: './dialogconsultarestudio.component.html',
  styleUrls: ['./dialogconsultarestudio.component.css']
})
export class DialogconsultarestudioComponent implements OnInit {

  id: number = 0;
  nombreE: string = '';
  fechaI: string = '';
  fechaF: string = '';
  status: string = '';
  estado: string = '';
  fechaFn = new Date();
  fechaIn = new Date();
  constructor(@Inject(MAT_DIALOG_DATA) public data: Estudio,
              public datepipe: DatePipe ) { }

  ngOnInit(): void {
   console.log("Data"+ this.data.fechaInicio + ' ' + this.data.fechaFin )
   this.id = this.data.id!;
   this.nombreE = this.data.nombre;
   this.fechaIn = this.data.fechaInicio;
   this.fechaFn = this.data.fechaFin!;
   this.status = this.data.estatus;
   this.estado = this.data.estado;
   /* this.fechaI = this.datepipe.transform(this.fechaIn, 'yyyy-MM-dd')!;
   this.fechaF = this.datepipe.transform(this.fechaFn, 'yyyy-MM-dd')!; */

   console.log(this.fechaIn);
   console.log(this.fechaFn);
   console.log(this.fechaI);
   console.log(this.fechaF);
   console.log(this.id);
  }

}
