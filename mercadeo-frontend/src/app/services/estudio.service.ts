import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Estudio} from '../interfaces/estudio';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class EstudioService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  readonly ROOT_URL = '//localhost:8080/mercadeo-backend/api/estudio';

  constructor(private httpClient: HttpClient) { }

  createEstudio(estudio: Estudio): Observable<any> {
    return this.httpClient.put('http://localhost:8080/mercadeo-backend/api/estudio/addEstudio', estudio)

  }

  getEstudios(id: number): Observable<any>{
    if (id === 0){
      return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/estudio/showEstudio`);
    }else{
      console.log("Entre aquuiiiiiiiiiii");
      return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/usuario/Dashboard-Encuestado/${id}`);
    }
  }


  getEstudio(id: number): Observable<any>{
    return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/estudio/consultar/${id}`);
  }

  setEstudio(id: number, estudio: Estudio){

    return this.httpClient.put(`http://localhost:8080/mercadeo-backend/api/estudio/updateEstudio/${id}`, estudio)
    .subscribe(
      response => console.log('modificado exitosamente' + response),
      error => console.log('error modificando' + error),
    );
  }

  setEstudio2(id: number, estudio: Estudio) : Observable<any>{

    return this.httpClient.put(`http://localhost:8080/mercadeo-backend/api/estudio/updateEstudio/${id}`, estudio)

  }

  deleteEstudio(id: number) {
    return this.httpClient.delete(`http://localhost:8080/mercadeo-backend/api/estudio/deleteEstudio/${id}`)
    .subscribe(
      response => console.log('eliminado exitosamente' + response),
      error => console.log('error modificando' + error),
    );
  }

  getPlantilla(id: number) {
    return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/estudio/estudiosRecomendados/${id}`);
  }

  //  ANALISTA

  // Estudios asignados al analista
  getEstudiosAnalista(id: number): Observable<any[]> {
    console.log(id);

    return this.httpClient.get<any[]>(this.ROOT_URL+'/getEstudiosUsuario/'+ id).pipe(
      tap(_ => this.log(`fetched estudio analista id=${id}`))
    );
  }


  // Obtener lista de poblacion asignados a los estudios del analista
  getPoblacion(id: number): Observable<any[]> {
    console.log(id);

    return this.httpClient.get<any[]>(this.ROOT_URL+'/poblacionEstudio/'+ id).pipe(
      tap(_ => this.log(`fetched encuestados del estudio analista id=${id}`))
    );
  }



  private log(message: string) {
    console.log(`EstudioService: ${message}`);
  }

  //Encuestado
  getEncuestaRespondida(idUsuario: number): Observable<any>{
    return this.httpClient.get(this.ROOT_URL + '/getEstudiosRespondidosEncuestado/'+`${idUsuario}`, this.httpOptions);
  }

}
