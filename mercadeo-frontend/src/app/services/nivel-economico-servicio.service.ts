import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NivelEconomicoServicioService {

  constructor(private httpClient: HttpClient) { }

  onCargarNivelE(): Observable<any> {
    return this.httpClient.get(`http://localhost:3000/nivel_economico`);
  }
}
