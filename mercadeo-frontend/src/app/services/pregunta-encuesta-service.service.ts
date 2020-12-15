import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PreguntaEncuestaServiceService {

  constructor(private httpClient: HttpClient) { }


  onCategoriaPregunta(tipo: string): Observable<any> {
    return this.httpClient.get(`http://localhost:3000/pregunta_encuesta?tipoPregunta=${tipo}`);
  }
}
