import { Dato_Usuario } from '../interfaces/dato_usuario';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class EncuestadoServicioService {

  id = 0;
  constructor(private httpClient: HttpClient) {
  }

  //Guadar Encuestado
  onGuardarUsuario(user: Dato_Usuario): Observable<any>{
     return this.httpClient.post<any>('http://localhost:8080/mercadeo-backend/api/dato-usuario/crear', user);

  }

  onCargarUsuarios(busqueda: string): Observable<any>{
      return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/dato-usuario/encuestados?primerNombre=${busqueda}`);
  }

  traerEncuestados(): Observable<any>{
    return this.httpClient.get('http://localhost:8080/mercadeo-backend/api/dato-usuario/encuestados');
  }

  onBuscarUsuario(indice: number): Observable<any>{

    return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/dato-usuario/encuestados?id=${indice}`);

  }

}
/*http://localhost:8080/mercadeo-backend/api/dato-usuario/crear*/
