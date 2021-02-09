import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of , throwError} from "rxjs";
import { catchError, map, tap, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PoblacionService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  readonly ROOT_URL = '//localhost:8080/mercadeo-backend/api/poblacion';

  constructor(public http: HttpClient) { }

  // Este metodo muestra la poblacion que no esta relacionada con este estudio

  getPoblacionNoRelacionada(idEstudio : any): Observable<any> {
    return this.http.get<any>(this.ROOT_URL+'/poblacionGeneral/'+idEstudio, this.httpOptions ).pipe(retry(1),
      catchError(this.handleError<any>('getPoblacionNoRelacionada', []))
    );
  }


  // este metodo muestra a la poblacion que es parte del estudio

  getPoblacion(idEstudio : any): Observable<any> {
    return this.http.get<any>(this.ROOT_URL+'/poblacionEstudio/'+idEstudio, this.httpOptions ).pipe(retry(1),
      catchError(this.handleError<any>('getPoblacion', []))
    );
  }


  // este metodo inserta la poblacion inicial del estudio haciendo match con los datos de la solicitud de ese momento
  // api/poblacion/PoblacionRecomendada/idSolicitud/idEstudio

  addPoblacionInicial(idSolicitud: any, idEstudio:any): Observable<any>{
    
    return this.http.post<any>(this.ROOT_URL+'/PoblacionRecomendada/'+ idSolicitud+'/'+idEstudio, this.httpOptions).pipe(
      tap(_ => this.log(`adde poblacion matched`)),
      catchError(this.handleError<any>('addPoblacion'))
    );
  }


  // este metodo es para agregar un nuevo campo de esta tabla

  addPoblacionNueva(poblacion : any): Observable<any> {
    console.log(poblacion, 'aqui')

    return this.http.post<any>(this.ROOT_URL+'/agregar/', poblacion, this.httpOptions).pipe(
      tap((poblacion: any) => this.log(`added persona w/ id=${poblacion.id}`)),
      catchError(this.handleError<any>('addPoblacion'))
    );
  }


  // este metodo es para eliminar personas de la tabla inactivandola
  // api/poblacion/actualizar NOTA me falta la ID
  editPoblacion(poblacion: any): Observable<any>{
    // console.log(poblacion, 'aqui')
    return this.http.put<any>(this.ROOT_URL+'/actualizar/'+ poblacion.id , poblacion, this.httpOptions).pipe(
      tap(_ => this.log(`updated poblacion id=${poblacion.id}`)),
      catchError(this.handleError<any>('editPoblacion'))
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
  console.log(`PoblacionService: ${message}`);
}



}
