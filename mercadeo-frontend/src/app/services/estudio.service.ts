import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Estudio } from '../interfaces/estudio';

@Injectable({
  providedIn: 'root'
})
export class EstudioService {

  constructor(private httpClient: HttpClient) { }

  createEstudio(estudio: Estudio) {
    this.httpClient.put('http://localhost:8080/mercadeo-backend/api/estudio/addEstudio', estudio)
      .subscribe(
        response => {
          console.log('resultado de guardar estudio' + response);
        },
        error => console.log('Error al guardar estudio' + error)
      );

  }

  getEstudios(id: number): Observable<any>{
    if (id === 0){
      return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/estudio/showEstudio`);
    }else{
      return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/estudio/listar/${id}`);
    }
  }

  getEstudiosAnalista(id: number): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/estudios?fk_estudio_usuario=${id}&estudios?fk_usuario=usuario?id&estudio?fk_solicitudEstudio=solicitud_estudio?id`);
  }

  getEstudio(id: number): Observable<any>{
    return this.httpClient.get(`http://localhost:8080/estudios?id=${id}`);
  }

  setEstudio(id: number, estudio: Estudio) {

    return this.httpClient.put('http://localhost:8080/mercadeo-backend/api/estudio/updateEstudio/' + id, estudio)
    .subscribe(
      response => console.log('modificado exitosamente' + response),
      error => console.log('error modificando' + error),
    );
  }

  deleteEstudio(id: number) {
    return this.httpClient.delete('http://localhost:8080/estudios/' + id)
    .subscribe(
      response => console.log('eliminado exitosamente' + response),
      error => console.log('error modificando' + error),
    );
  }
}
