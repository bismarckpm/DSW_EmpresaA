import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Estudio } from '../models/estudio';

@Injectable({
  providedIn: 'root'
})
export class EstudioService {

  constructor(private httpClient: HttpClient) { }

  createEstudio(estudio: Estudio) {
    return this.httpClient.post('http://localhost:3000/estudios', estudio)
      .subscribe(
        response => {
          console.log('resultado de guardar estudio' + response);
        },
        error => console.log('Error al guardar estudio' + error)
      );

  }

  /*http://localhost:8080/mercadeo-backend/api/estudio/addEstudio*/

  getEstudios(id: number): Observable<any>{
    if (id === 0){
      return this.httpClient.get(`http://localhost:3000/estudios`);
      /*http://localhost:8080/mercadeo-backend/api/estudio/listar/0*/
    }else{
      return this.httpClient.get(`http://localhost:3000/estudios?estudioSolicitudDto.id=${id}`);
    }/*http://localhost:8080/mercadeo-backend/api/estudio/listar/${id}*/
  }

  getEstudiosAnalista(id: number): Observable<any> {
    return this.httpClient.get(`http://localhost:3000/estudios?fk_estudio_usuario=${id}&estudios?fk_usuario=usuario?id&estudio?fk_solicitudEstudio=solicitud_estudio?id`);
  }

  getEstudio(id: number): Observable<any>{
    return this.httpClient.get(`http://localhost:3000/estudios?id=${id}`);
  }
  /*http://localhost:8080/mercadeo-backend/api/estudio/buscar/${id}*/
  setEstudio(id: number, estudio: Estudio) {

    return this.httpClient.put('http://localhost:3000/estudios/' + id, estudio)
    /*http://localhost:8080/mercadeo-backend/api/estudio/updateEstudio/*/
    .subscribe(
      response => console.log('modificado exitosamente' + response),
      error => console.log('error modificando' + error),
    );
  }

  deleteEstudio(id: number) {
    return this.httpClient.delete('http://localhost:3000/estudios/' + id)
    /*http://localhost:8080/mercadeo-backend/api/estudio/deleteEstudio/*/
    .subscribe(
      response => console.log('eliminado exitosamente' + response),
      error => console.log('error modificando' + error),
    );
  }
}
