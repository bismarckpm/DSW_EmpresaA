import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RolServicioService {

  constructor(private httpClient: HttpClient) { }

  onCargarRoles(): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/rol`);
  }

  onCargarRol(Id: number): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/rol?id=${Id}`);
  }
}
