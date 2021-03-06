import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Respuesta_Pregunta } from 'src/app/interfaces/respuesta_pregunta';
import { PreguntaService } from 'src/app/services/pregunta.service';
import { RespuestapreguntaService } from '../../../../services/respuestapregunta.service';
import { DialogopcionComponent } from '../../../dialog/dialogopcion/dialogopcion.component';

@Component({
  selector: 'app-consultarespuesta',
  templateUrl: './consultarespuesta.component.html',
  styleUrls: ['./consultarespuesta.component.css']
})
export class ConsultarespuestaComponent implements OnInit {


  eliminarForm: any;

  idPregunta:any;
  respuesta: any;
  pregunta: any;
  preguntas: any;

  resp: any;

  constructor(
    private _route: ActivatedRoute,
    private _respuestaPreguntaService: RespuestapreguntaService,
    private _preguntaService: PreguntaService,
    public dialog: MatDialog,
    private fb: FormBuilder,
  ) {
    
   }

   buildForm(): void {
    this.eliminarForm = this.fb.group({
     idRespuesta: ["",
     Validators.compose([
       Validators.required]),
     ],
    }),
    {
      nombreRespuesta: ["",
       Validators.compose([
         Validators.required]),
       ],
      } 
  }

  ngOnInit(): void {
    this.getPreguntas();
    this._route.queryParams.subscribe(
      response => {
        console.log(response);
        this.idPregunta = response;
        console.log(this.idPregunta);
        this.getRespuestas(this.idPregunta.pregunta);
        this.consultaPregunta(this.idPregunta.pregunta);
      }
    );
    this.buildForm();

  }

  getRespuestas(idPregunta: number){
    this._respuestaPreguntaService.obtenerRespuestas(idPregunta).subscribe(
      response => {
        this.respuesta = response.objeto;
        console.log(this.respuesta);
      }
    )

  }

  consultaPregunta(idPregunta: number){
    this._preguntaService.consultaPregunta(idPregunta).subscribe(
      response => {
        this.pregunta = response;
      }
    )
  }


  eliminarRespuesta(respuestas: any){
    console.log(respuestas);
    const resp: Respuesta_Pregunta = {
      id: respuestas._id,
      nombre: respuestas._nombre,
      estado: respuestas._estado,
      preguntaEncuestaDto: respuestas._preguntaEncuesta._id
    }

    if(confirm("¿Estás seguro que deseas eliminar la respuesta?")){
      this._respuestaPreguntaService.eliminarRespuesta(resp).subscribe(
        response => {
          console.log(response);
          location.reload();
        }
      )
    }
    
  }


  getPreguntas(){
    this._preguntaService.getPreguntasTipo().subscribe(
      response => {
        this.preguntas = response.objeto;
        console.log(response);
      }
    )
  }


  openDialog(id: number): void {
    console.log(id);
    const dialogRef = this.dialog.open(DialogopcionComponent, {
      width: '20rem',
      data: {id: id}
    });


    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      //this.getRespuestas(this.idPregunta);
    });
  }





}
