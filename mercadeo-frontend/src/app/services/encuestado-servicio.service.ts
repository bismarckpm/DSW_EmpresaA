import { GetDato_Usuario } from './../interfaces/dato_usuario';
import { map } from 'rxjs/operators';
import { Dato_Usuario } from '../interfaces/dato_usuario';
import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class EncuestadoServicioService {

  id = 0;
  constructor(private httpClient: HttpClient) {
  }

  //Guadar Encuestado
  onGuardarUsuario(user: Dato_Usuario){
     return this.httpClient.post<Dato_Usuario>('http://localhost:8080/mercadeo-backend/api/dato-usuario/crear', user
     );

  }

  getDatoUsuario(id: number): Observable<any>{
      return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/dato-usuario/consultar/${id}`);
  }

  setDatoUsuario(id: number, user: Dato_Usuario) {
    return this.httpClient.put<Dato_Usuario>(`http://localhost:8080/mercadeo-backend/api/dato-usuario/actualizar/${id}`, user
    ).subscribe(
      response => console.log('modificado exitosamente' + response),
      error => console.log('error modificando' + <any>error),
    );
  }

  onCargarUsuarios(busqueda: string): Observable<any>{
      return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/dato-usuario/encuestados?primerNombre=${busqueda}`);
  }

  traerEncuestados(): Observable<Dato_Usuario[]>{
    return this.httpClient.get<Dato_Usuario[]>('http://localhost:8080/mercadeo-backend/api/dato-usuario/listar');
  }

  onBuscarUsuario(indice: number): Observable<any>{

    return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/dato-usuario/encuestados?id=${indice}`);

  }

  BuscarUsuario(idUser: number): Observable<any>{
    return this.httpClient.get('http://localhost:8080/mercadeo-backend/api/usuario/consultar/'+`${idUser}`);
  }

}
/*http://localhost:8080/mercadeo-backend/api/dato-usuario/crear*/
