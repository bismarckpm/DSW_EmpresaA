import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PreguntaEncuestaServiceService {

  constructor(private httpClient: HttpClient) { }


  listarPreguntas(id: number): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/pregunta_estudio/mostrarPregunta_estudio/${id}`);
  }
  getPreguntas(idE: number, idU: number): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/respuesta/preguntas/${idE}/${idU}`);
  }

  getPreguntasGenerales(id: number): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/pregunta_estudio/preguntasGenerales/${id}`);
  }

  getPreguntasRecomendadas(id: number): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/pregunta_estudio/preguntasRecomendadas/${id}`);
  }
}
