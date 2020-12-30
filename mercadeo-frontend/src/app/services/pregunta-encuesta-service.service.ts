import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PreguntaEncuestaServiceService {

  constructor(private httpClient: HttpClient) { }


  onCategoriaPregunta(tipo: string): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/pregunta_encuesta?tipoPregunta=${tipo}`);
  }

  getPreguntas(id: number): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/respuesta/preguntas/${id}`);
  }
}
