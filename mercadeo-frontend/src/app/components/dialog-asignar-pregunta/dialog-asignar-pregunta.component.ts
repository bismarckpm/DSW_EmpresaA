import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Estudio } from 'src/app/interfaces/estudio';

@Component({
  selector: 'app-dialog-asignar-pregunta',
  templateUrl: './dialog-asignar-pregunta.component.html',
  styleUrls: ['./dialog-asignar-pregunta.component.css']
})
export class DialogAsignarPreguntaComponent implements OnInit {

  idEst = 0;
  constructor(@Inject(MAT_DIALOG_DATA) public data: Estudio,
              private navegacion: Router) { }

  ngOnInit(): void {
    this.idEst = this.data.id!;
    console.log(this.idEst);
  }

  busquedaPreguntaG() {
    this.navegacion.navigate(['preguntasgenerales', this.idEst]);

  }

  busquedaPreguntaR(){
    this.navegacion.navigate(['preguntasrecomendadas', this.idEst]);
  }
}
