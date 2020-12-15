import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Respuesta_Pregunta } from 'src/app/models/respuesta_pregunta';
import { RespuestapreguntaService } from 'src/app/services/respuestapregunta.service';
import { ConsultarespuestaComponent } from '../../consultarespuesta/consultarespuesta.component';

@Component({
  selector: 'app-dialogopcion',
  templateUrl: './dialogopcion.component.html',
  styleUrls: ['./dialogopcion.component.css']
})
export class DialogopcionComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<ConsultarespuestaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Respuesta_Pregunta,
    public _respuestaService: RespuestapreguntaService,
  ) { }

  respuesta: Respuesta_Pregunta = {
    id: 0,
    nombre: '',
    estado: '',
    preguntaEncuestaDto: 0
  }

  ngOnInit(): void {
    this.getRespuesta();
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


  guardar(respuesta: Respuesta_Pregunta){
    console.log(respuesta);
    this._respuestaService.actualizarRespuesta(respuesta).subscribe(
      response => {
        console.log('Actualización exitosa');
      }, error => {
        console.log(<any>error);
      }
    )
    location.reload();
  }

}
