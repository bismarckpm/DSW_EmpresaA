import { Component, Inject, OnInit, Pipe } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { first, tap } from 'rxjs/operators';
import { Estudio } from 'src/app/interfaces/estudio';
import { GetPregunta_Estudio } from 'src/app/interfaces/pregunta_estudio';
import { PreguntaEncuestaServiceService } from 'src/app/services/pregunta-encuesta-service.service';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';


@Component({
  selector: 'app-dialog-asignar-pregunta',
  templateUrl: './dialog-asignar-pregunta.component.html',
  styleUrls: ['./dialog-asignar-pregunta.component.css']
})
export class DialogAsignarPreguntaComponent implements OnInit {

  idEst = 0;
  preguntas: GetPregunta_Estudio[] = [];
  preguntasG: GetPregunta_Estudio[] = [];
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  constructor(@Inject(MAT_DIALOG_DATA) public data: Estudio,
              private navegacion: Router,
              private pregunta: PreguntaEncuestaServiceService,
              public dialogRef: MatDialogRef<DialogAsignarPreguntaComponent>,
              private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.idEst = this.data.id!;
    console.log(this.idEst);

    this._snackBar.open('Verificando si existen preguntas recomendadas', undefined, {
      duration: 10000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });

    this.pregunta.getPreguntasRecomendadas(this.idEst).subscribe(
       (pregunta) => {
          this.preguntas =  pregunta.objeto;
          console.log(this.preguntas.length);

          if(this.preguntas.length > 0){
            this._snackBar.open('Existen preguntas recomendadas', undefined, {
            duration: 1000,
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition,
        });
        } else{
            this._snackBar.open('No se encuentro ninguna pregunta recomendada', undefined, {
              duration: 1000,
              horizontalPosition: this.horizontalPosition,
              verticalPosition: this.verticalPosition,
        });
        }
       }
     );

    this.pregunta.getPreguntasGenerales(this.idEst).subscribe(
      (preguntag) => {
         this.preguntasG =  preguntag.objeto;
         console.log(this.preguntasG.length);
      }
    );

    /* if ((this.preguntas.length === 0) && (this.preguntasG.length === 0)) {
      this._snackBar.open('No existen preguntas para agregar', undefined, {
        duration: 1000,
        horizontalPosition: this.horizontalPosition,
        verticalPosition: this.verticalPosition,
    });
  } */

  }

  busquedaPreguntaG() {
    this.dialogRef.close();
    this.navegacion.navigate(['preguntasgenerales', this.idEst]);
  }

  busquedaPreguntaR(){
    this.navegacion.navigate(['preguntasrecomendadas', this.idEst]);
    this.dialogRef.close();
  }
}
