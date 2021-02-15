import { Inject, ViewChild } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Estudio } from 'src/app/interfaces/estudio';
import { EstudioService } from 'src/app/services/estudio.service';
import { PreguntaEncuestaServiceService } from 'src/app/services/pregunta-encuesta-service.service';
import { isThisTypeNode } from 'typescript';
import { DialogCrearestudiorecomendadoComponent } from '../dialog-crearestudiorecomendado/dialog-crearestudiorecomendado.component';
import { RecomendacionEstudiosComponent } from '../recomendacion-estudios/recomendacion-estudios.component';

@Component({
  selector: 'app-dialog-previewestudio',
  templateUrl: './dialog-previewestudio.component.html',
  styleUrls: ['./dialog-previewestudio.component.css']
})
export class DialogPreviewestudioComponent implements OnInit {



  estudioRecomendado: any; 

  //Preguntas
  preguntasEstudioRecomendado:any;
  page = 10;
  pageSize = 5; 
  


  constructor(
    public dialogRef: MatDialogRef<RecomendacionEstudiosComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any,
    private _estudioService: EstudioService,
    private _preguntaEncuestaService: PreguntaEncuestaServiceService,
    private navegacion: Router,
    private dialog: MatDialog
    
  ) {

  }
  ngOnInit(): void {

    console.log(this.data);
    this.obtenerEstudioRecomendado(this.data.idEstudio);
    this.obtenerPreguntasEstudioRecomendado(this.data.idEstudio);


  }

  obtenerEstudioRecomendado(idEstudio: any){
    this._estudioService.getEstudio(idEstudio).subscribe(
      response => {
        this.estudioRecomendado = response.objeto; 
        console.log(this.estudioRecomendado);
      }, error => {
        console.log(<any>error);
      }
    );
  }

  obtenerPreguntasEstudioRecomendado(idEstudio: any){
    this._preguntaEncuestaService.listarPreguntas(idEstudio).subscribe(
      response => {
        this.preguntasEstudioRecomendado = response.objeto; 
        console.log(this.preguntasEstudioRecomendado);
      }, error => {
        console.log(<any>error);
      }
    )
  }

  closeDialog(){
    this.dialogRef.close();
  }

  crearEstudioRecomendado(idEstudio: number, idSolicitud: number){

    const dialogEst = this.dialog.open(DialogCrearestudiorecomendadoComponent,
      {
        width: '35rem',
        height: '20rem',
        data: { idEstudio: idEstudio, idSolicitud:idSolicitud}
      });

      dialogEst.afterClosed().subscribe(
        result => {
          console.log("El dialogo se cerró");
          this.dialogRef.close();
        }
      );
        
      

  }


}
