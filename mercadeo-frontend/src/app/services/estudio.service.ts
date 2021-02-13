import { Observable, of , throwError} from "rxjs";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Estudio} from '../interfaces/estudio';
import { catchError, map, tap, retry } from 'rxjs/operators';
import { AlertService } from "./alert.service";

@Injectable({
  providedIn: 'root'
})
export class EstudioService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  readonly ROOT_URL = '//localhost:8080/mercadeo-backend/api/estudio';

  // Alerts
  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };

  constructor(
    private httpClient: HttpClient,
    private alertService: AlertService,
    ) { }

  // crear estudio(ADMIN)
  createEstudio(estudio: Estudio): Observable<any> {
    return this.httpClient.put('http://localhost:8080/mercadeo-backend/api/estudio/addEstudio', estudio);


  }

  createEstudioRecomendacion(idS: number,estudio: Estudio): Observable<any> {
    return this.httpClient.put(`http://localhost:8080/mercadeo-backend/api/estudio/addEstudioPorRecomendacion/${idS}`, estudio)
  }



  getEstudios(id: number): Observable<any>{
    if (id === 0){
      //obtiene estudios para la pantalla de consultar estudios(ADMIN)
      return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/estudio/showEstudio`);
    }else{
      // obtiene estudios por responder para dashboard de encuestado(ADMIN)
      console.log("Entre aquuiiiiiiiiiii " +`${id}`);
      return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/usuario/Dashboard-Encuestado/${id}`);
    }
  }


  getEstudio(id: number): Observable<any>{
    return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/estudio/consultar/${id}`);
  }


  /*Metodo que modifica un estudio(ADMIN)*/
  setEstudio(id: number, estudio: Estudio){

    return this.httpClient.put(`http://localhost:8080/mercadeo-backend/api/estudio/updateEstudio/${id}`, estudio)
    .subscribe(
      response => {
        console.log('modificado exitosamente' + response)    
        this.alertService.success('modificado exitosamente', this.options)
    },
      error => {
        console.log('error modificando' + error)
        this.alertService.error(error.mensaje, this.options)
      }
    );
  }

  /*Metodo para analista dialog-estatus.component.ts*/
  setEstudio2(id: number, estudio: Estudio) : Observable<any>{

    return this.httpClient.put(`http://localhost:8080/mercadeo-backend/api/estudio/updateEstudio/${id}`, estudio)

  }

  //borrar estudio(ADMIN)
  deleteEstudio(id: number, estudio: Estudio) {
    return this.httpClient.put(`http://localhost:8080/mercadeo-backend/api/estudio/updateEstudio/${id}`, estudio)
    .subscribe(
      response => {
        console.log('eliminado exitosamente' + response)    
        this.alertService.success('eliminado exitosamente'  , this.options)
    },
      error => {
        console.log('error eliminando' + error.message)
        this.alertService.error(error.message + error.rejection + error.columnNumber, this.options)
      }
    );
  }

  //obtiene los estudios recomendados o plantillas (ADMIN)
  getPlantilla(id: number): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/mercadeo-backend/api/estudio/estudiosRecomendados/${id}`);
  }

  //  ANALISTA

  // Estudios asignados al analista
  getEstudiosAnalista(id: number): Observable<any> {
    console.log(id);

    return this.httpClient.get<any>(this.ROOT_URL+'/getEstudiosUsuario/'+ id).pipe(
      tap(_ => this.log(`fetched estudio analista id=${id}`))
    );
  }


  // Obtener lista de poblacion asignados a los estudios del analista
  getPoblacion(id: number): Observable<any> {
    console.log(id);

    return this.httpClient.get<any>(this.ROOT_URL+'/poblacionEstudio/'+ id, this.httpOptions).pipe(
      tap(_ => this.log(`fetched encuestados del estudio analista id=${id}`))
    );
  }


  // Recibe ID Estudio
  // Revisa que si el estudio ha sido respondido por algun usuario
  // Devuelve Boolean True o False
  getValidarPoblacionEstudio(idEstudio: number): Observable<any>{
    return this.httpClient.get(this.ROOT_URL + '/validarContestado/'+`${idEstudio}`, this.httpOptions).pipe(
      tap(_ => this.log(`fetched idEstudio= ${idEstudio}` )),
      catchError(this.handleError<any>(`getValidarPoblacionEstudio idEstudio= ${idEstudio}`))
    );
  }


  //Encuestado
  getEncuestaRespondida(idUsuario: number): Observable<any>{
    return this.httpClient.get(this.ROOT_URL + '/getEstudiosRespondidosEncuestado/'+`${idUsuario}`, this.httpOptions);
  }



  // Recibe ID Usuario / ID Estudio
  // Revisa que si el estudio ha sido respondido por el usuario X
  // Devuelve Boolean True o False
  getValidarParticipacion(idUsuario: number, idEstudio: number): Observable<any>{
    return this.httpClient.get(this.ROOT_URL + '/validarParticipacion/'+`${idUsuario}`+'/'+`${idEstudio}`, this.httpOptions).pipe(
      tap(_ => this.log(`fetched idUser=${idUsuario} idEstudio= ${idEstudio}` )),
      catchError(this.handleError<any>(`getValidarParticipacion idUser=${idUsuario} idEstudio= ${idEstudio}`))
    );
  }


 /**
 * Handle Http operation that failed.
 * Let the app continue.
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
private handleError<T>(operation = 'operation', result?: T) {
  return (error: any): Observable<T> => {

    // TODO: send the error to remote logging infrastructure
    console.error(error); // log to console instead

    // TODO: better job of transforming error for user consumption
    this.log(`${operation} failed: ${error.message}`);

    // Let the app keep running by returning an empty result.
    return of(result as T);
  };
}

private log(message: string) {
  console.log(`EstudioService: ${message}`);
}


}
