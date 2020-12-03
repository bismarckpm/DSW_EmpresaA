import { Pregunta_Estudio } from './../models/pregunta_estudio';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PreguntaEstudioServicioService {

  constructor(private httpClient: HttpClient) { }

  createPreguntaEstudio(pregunta: Pregunta_Estudio) {
    this.httpClient.post('http://localhost:8080/mercadeo-backend/api/pregunta_estudio/addPregunta_estudio', pregunta)
      .subscribe(
        response => {
          console.log('resultado de guardar pregunta_estudio' + response);
        },
        error => console.log('Error al guardar pregunta_estudio' + error)
      );

  }

  getPreguntas():Observable<any> {
      return this.httpClient.get(`http://localhost:8080/pregunta_estudio`);
  }

}
