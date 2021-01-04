import { Solicitud_Estudio } from './../../interfaces/solicitud_estudio';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dialog-crear-estudio',
  templateUrl: './dialog-crear-estudio.component.html',
  styleUrls: ['./dialog-crear-estudio.component.css']
})
export class DialogCrearEstudioComponent implements OnInit {

  idSol = 0;
  constructor(@Inject(MAT_DIALOG_DATA) public data: Solicitud_Estudio,
              private navegacion: Router) { }

  ngOnInit(): void {
    this.idSol = this.data.id!;


  }

  crearEstudioN() {
    this.navegacion.navigate(['crearestudio', this.idSol]);
  }

  crearEstudioP() {
    this.navegacion.navigate(['recomendarEstudio', this.idSol]);
  }
}
