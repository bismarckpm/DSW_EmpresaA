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
  estatus: string = '';
  estado: string = '';
  constructor(@Inject(MAT_DIALOG_DATA) public data: Estudio) { }

  ngOnInit(): void {

    this.id = this.data.id!;
    this.tipoI = this.data.tipoInstrumento!;
    this.nombreE = this.data.nombre!;
    this.fechaI = this.data.fechaInicio!;
    this.fechaF = this.data.fechaFin!;
    this.estatus = this.data.estatus!;
    this.estado = this.data.estado!;
    console.log(this.id);
    console.log(this.tipoI);
  }

}
