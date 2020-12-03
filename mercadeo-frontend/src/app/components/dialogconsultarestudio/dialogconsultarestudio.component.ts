import { DatePipe } from '@angular/common';
import { Component, Inject, OnInit, Output } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Estudio } from 'src/app/models/estudio';


@Component({
  selector: 'app-dialogconsultarestudio',
  templateUrl: './dialogconsultarestudio.component.html',
  styleUrls: ['./dialogconsultarestudio.component.css']
})
export class DialogconsultarestudioComponent implements OnInit {

  id: number = 0;
  tipoI: string = '';
  nombreE: string = '';
  fechaI: string = '';
  fechaF: string = '';
  status: string = '';
  estado: string = '';
  fechaFn!: Date;
  fechaIn!: Date;
  constructor(@Inject(MAT_DIALOG_DATA) public data: Estudio,
                  public datepipe: DatePipe ) { }

  ngOnInit(): void {
   console.log("Data"+ this.data.fechaInicio + " " + this.data.fechaFinal )
    this.id = this.data.id!;
    this.tipoI = this.data.tipoInstrumento!;
    this.nombreE = this.data.nombre!;
    this.fechaIn = this.data.fechaInicio!;
    this.fechaFn = this.data.fechaFinal!;
    this.status = this.data.status!;
    this.estado = this.data.estado!;

    console.log(this.fechaIn);
    console.log(this.fechaFn);
    console.log(this.id);
    console.log(this.tipoI);
  }

}
