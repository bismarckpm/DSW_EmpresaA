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
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';


@Component({
  selector: 'app-asignar-preguntas-estudio',
  templateUrl: './asignar-preguntas-estudio.component.html',
  styleUrls: ['./asignar-preguntas-estudio.component.css']
})
export class AsignarPreguntasEstudioComponent implements OnInit {
  isWait=false;
  idA = 5;
  idR = 2;
  cat = '';
  estId = 0;
  idP = 0;
  categorias: string[] =  ['Abierta', 'Verdadero o Falso', 'Seleccion Simple', 'Seleccion Multiple', 'Escala'];
  preguntas: GetPregunta_Estudio[] = [];
  estudios: Estudio[] = [];
  pre: Pregunta_Estudio[] = [];
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  constructor(private estudio: EstudioService, private pregunta: PreguntaEncuestaServiceService,
              private preguntaE: PreguntaEstudioServicioService,
              private route: ActivatedRoute,
              public dialog: MatDialog,
              private navegacion: Router,
              private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.estId = this.route.snapshot.params['idEstudio'];

    this.busquedaPreguntas();
  }

  busquedaPreguntas() {
     this.isWait=true;
     this.pregunta.listarPreguntas(this.estId).subscribe(
      (pregunta: GetPregunta_Estudio[]) => {
         this.preguntas =  pregunta;
         console.log(this.preguntas);
         this.isWait=false;

         if(this.preguntas.length === 0){
          this._snackBar.open('No hay preguntas asignadas a este Estudio', undefined, {
            duration: 3000,
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition,
          });
         }
      }
    );
  }

  eliminarPregunta(id: number) {
    this.preguntaE.deletePregunta(id);

    setTimeout(() => {
      this.busquedaPreguntas();
      }, 1000);
  }

  openDialogAP(): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.data = {
      id: this.estId,
    },
    dialogConfig.closeOnNavigation = true;

    const dialogRef = this.dialog.open(DialogAsignarPreguntaComponent, dialogConfig);

    this.navegacion.events.subscribe(() => {
      this.dialog.closeAll();
    });

    dialogRef.afterClosed().subscribe(result => {
        console.log('Dialog closed');
      });
  }

  atras() {
    this.navegacion.navigate(['consultarestudios']);
  }
}
