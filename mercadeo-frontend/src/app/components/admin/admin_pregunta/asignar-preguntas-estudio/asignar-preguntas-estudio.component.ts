import { GetPregunta_Estudio, Pregunta_Estudio } from '../../../../interfaces/pregunta_estudio';
import { PreguntaEstudioServicioService } from '../../../../services/pregunta-estudio-servicio.service';
import { PreguntaEncuestaServiceService } from '../../../../services/pregunta-encuesta-service.service';

import { EstudioService } from 'src/app/services/estudio.service';
import { Estudio } from '../../../../interfaces/estudio';
import { Component, OnInit } from '@angular/core';
import { Pregunta_Encuesta } from 'src/app/interfaces/pregunta_encuesta';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DialogAsignarPreguntaComponent } from 'src/app/components/dialog-asignar-pregunta/dialog-asignar-pregunta.component';


@Component({
  selector: 'app-asignar-preguntas-estudio',
  templateUrl: './asignar-preguntas-estudio.component.html',
  styleUrls: ['./asignar-preguntas-estudio.component.css']
})
export class AsignarPreguntasEstudioComponent implements OnInit {

  idA = 5;
  idR = 2;
  cat = '';
  estId = 0;
  idP = 0;
  categorias: string[] =  ['Abierta', 'Verdadero o Falso', 'Seleccion Simple', 'Seleccion Multiple', 'Escala'];
  preguntas: GetPregunta_Estudio[] = []; /// almacena las preguntas del tipo seleccionado
  estudios: Estudio[] = [];
  pre: Pregunta_Estudio[] = [];

  // aca estan todas las preguntas
  constructor(private estudio: EstudioService, private pregunta: PreguntaEncuestaServiceService,
              private preguntaE: PreguntaEstudioServicioService,
              private route: ActivatedRoute,
              public dialog: MatDialog,
              private navegacion: Router) { }

  ngOnInit(): void {
    this.estId = this.route.snapshot.params['idEstudio'];
    /* this.preguntaE.getPreguntas().subscribe(
      (pr: Pregunta_Estudio[]) => {
        this.pre = pr;
         this.idP = this.pre.slice(-1)[0].id!;
        console.log(this.idP);
      }
    ); */

    /* this.estudio.getEstudios(this.idA).subscribe(
      (estudios: Estudio[]) => {
        this.estudios = estudios;
      }
    ); */


  }

  busquedaPreguntas() {
     this.pregunta.listarPreguntas(this.estId).subscribe(
      (pregunta: GetPregunta_Estudio[]) => {
         this.preguntas =  pregunta;
         console.log(this.preguntas);
      }
    );
  }

 // agregarPreguntaEstudio(id: number) {

    /* let estudio = new Estudio(this.estId);
    let preEnc = new Pregunta_Encuesta(id); */

    /* const pre: Pregunta_Estudio = {
      estudioDto: this.estId,
      preguntaEncuestaDto: id};

    this.preguntaE.createPreguntaEstudio(pre); */

  //}

  openDialogAP(): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.data = {
      id: this.estId,
    };

    const dialogRef = this.dialog.open(DialogAsignarPreguntaComponent, dialogConfig);

    this.navegacion.events
    .subscribe(() => {
      dialogRef.close();
    });

    dialogRef.afterClosed().subscribe(result => {
        console.log('Dialog closed');
      });
  }
}
