import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MuestraAnalistaService {

  constructor(private http: HttpClient) { }

  
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json', Authorization: 'my-auth-token' })
  };
  readonly ROOT_URL = '//45.76.60.252:8080/mercadeo-backend/api/respuesta';


  // Trae todos los encuestados o la muestra de un estudio asignado al id analista
  getMuestra(id: number): Observable<any[]> {

    return this.http.get<any[]>(this.ROOT_URL+"/listar/encuestados-resuelto/"+id).pipe(
      tap(_ => this.log(`fetched marca id=${id}`)),
      catchError(this.handleError<any[]>(`getMuestra id=${id}`))
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
  console.log(`MuestraAnalistaService: ${message}`);
}

}