import { Pregunta_Estudio } from '../interfaces/pregunta_estudio';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AlertService } from './alert.service';

@Injectable({
  providedIn: 'root'
})
export class PreguntaEstudioServicioService {

  // Alerts
  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
      private httpClient: HttpClient,
      private alertService: AlertService,
      ) {}

      // Asigna la pregunta al estudio
  createPreguntaEstudio(pregunta: Pregunta_Estudio) {
    this.httpClient.post('http://45.76.60.252:8080/mercadeo-backend/api/pregunta_estudio/addPregunta_estudio', pregunta)
      .subscribe(
        response => {
          console.log('resultado de guardar pregunta_estudio' + response);
          this.alertService.success('Pregunta Agregada' , this.options);
        },
        error => {console.log('Error al guardar pregunta_estudio' + error);
                  this.alertService.error(error.mensaje, this.options);
      }
      );

  }


  getPreguntas():Observable<any> {
      return this.httpClient.get(`http://45.76.60.252:8080/pregunta_estudio`);
  }

  deletePregunta(id: number) {


    return this.httpClient.put(`http://45.76.60.252:8080/mercadeo-backend/api/pregunta_estudio/deletePreguntaEstudio/${id}`, {headers: this.httpOptions})
    .subscribe(
      response => {
        console.log('resultado de borrar exitosamente' + response);
        this.alertService.error('Eliminado exitosamente', this.options)
      },
      error => {console.log('Error al borrar pregunta' + error)
      this.alertService.error(error.mensaje, this.options)
    }
    );
  }
}
