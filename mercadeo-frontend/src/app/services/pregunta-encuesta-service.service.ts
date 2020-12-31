import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PreguntaEncuestaServiceService {

  constructor(private httpClient: HttpClient) { }


  listarPreguntas(id: number): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/estudio/mostrarPregunta_estudio/${id}`);
  }
  getPreguntas(id: number): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/respuesta/preguntas/${id}`);
  }
}
