import { _DisposeViewRepeaterStrategy } from '@angular/cdk/collections';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Respuesta_Pregunta } from 'src/app/interfaces/respuesta_pregunta';
import { RespuestapreguntaService } from 'src/app/services/respuestapregunta.service';
import { ConsultarespuestaComponent } from '../../consultarespuesta/consultarespuesta.component';

@Component({
  selector: 'app-dialogopcion',
  templateUrl: './dialogopcion.component.html',
  styleUrls: ['./dialogopcion.component.css']
})
export class DialogopcionComponent implements OnInit {

  actualizarRespuestaForm: any;
  respuesta: any;

  constructor(
    public dialogRef: MatDialogRef<ConsultarespuestaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Respuesta_Pregunta,
    public _respuestaService: RespuestapreguntaService,
    private fb: FormBuilder,
  ) { }

buildForm(): void {
    this.actualizarRespuestaForm = this.fb.group({
     nombreRespuesta: ["",
     Validators.compose([
       Validators.required]),
     ],
    });
  }

  ngOnInit(): void {
    this.getRespuesta();
    this.buildForm();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  getRespuesta(){
    const id = this.data.id;
    this._respuestaService.obtenerRespuesta(id).subscribe(
      response => {
        this.respuesta = response;
      }
    );
  }


  guardar(){
    console.log(this.respuesta);
    const actR = {
      id: this.respuesta._id,
      nombre: this.actualizarRespuestaForm.get("nombreRespuesta").value,
      estado: 'A',
      preguntaEncuestaDto: this.respuesta._preguntaEncuesta._id
    }
    console.log(actR);
    this._respuestaService.actualizarRespuesta(actR).subscribe(
      response => {
        console.log('Actualización exitosa');
      }, error => {
        console.log(<any>error);
      }
    )
    location.reload();
  }

}
