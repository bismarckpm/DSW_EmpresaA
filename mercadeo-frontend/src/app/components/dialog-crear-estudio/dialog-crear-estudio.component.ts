import { EstudioService } from 'src/app/services/estudio.service';
import { GetEstudio } from 'src/app/interfaces/estudio';
import { Solicitud_Estudio } from './../../interfaces/solicitud_estudio';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogConfig, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';


@Component({
  selector: 'app-dialog-crear-estudio',
  templateUrl: './dialog-crear-estudio.component.html',
  styleUrls: ['./dialog-crear-estudio.component.css']
})
export class DialogCrearEstudioComponent implements OnInit {

  idSol = 0;
  estudios: GetEstudio[] = [];
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  constructor(@Inject(MAT_DIALOG_DATA) public data: Solicitud_Estudio,
              private navegacion: Router,
              private estudiosR: EstudioService,
              public dialogRef: MatDialogRef<DialogCrearEstudioComponent>,
              private _snackBar: MatSnackBar ) { }

  ngOnInit(): void {
    this.idSol = this.data.id!;
    console.log(this.idSol);

    this._snackBar.open('Verificando si existen plantillas', undefined, {
      duration: 5000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });


    this.estudiosR.getPlantilla(this.idSol).subscribe(
      (estudios: any) => {
        this.estudios = estudios.objeto;
        console.log(this.estudios);
        console.log("cantidad de estudios recomendados " + this.estudios.length);

        if(this.estudios.length > 0){
          this._snackBar.open('Existen plantillas en el sistema', undefined, {
          duration: 1000,
          horizontalPosition: this.horizontalPosition,
          verticalPosition: this.verticalPosition,
      });
      } else{
          this._snackBar.open('No existen plantillas', undefined, {
            duration: 1000,
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition,
      });
      }
      }
    );



  }

  crearEstudioN() {
    this.navegacion.navigate(['crearestudio', this.idSol]);
    this.dialogRef.close();
  }

  crearEstudioP() {
    this.navegacion.navigate(['recomendacionEstudio', this.idSol]);
    this.dialogRef.close();
  }



}
