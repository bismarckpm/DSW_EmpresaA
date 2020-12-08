import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, retry, tap } from 'rxjs/operators';
import { Presentacion } from 'src/interfaces/presentacion';

@Injectable({
  providedIn: 'root'
})
export class PresentacionService {

  presentaciones: Presentacion[] = [];
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  readonly ROOT_URL = '/api/presentacion'
  constructor(private http: HttpClient) { }


  getPresentaciones(): Observable<Presentacion[]> {
    return this.http.get<Presentacion[]>(this.ROOT_URL).pipe(retry(1),
      catchError(this.handleError<Presentacion[]>('getPresentaciones', []))
    );
  }



  getPresentacion(id: number): Observable<Presentacion> {
    const url = `${this.ROOT_URL}/${id}`;
    return this.http.get<Presentacion>(url).pipe(
      tap(_ => this.log(`fetched Presentacion id=${id}`)),
      catchError(this.handleError<Presentacion>(`getPresentacion id=${id}`))
    );

}

  createPresentacion(presentacion: Presentacion): Observable<Presentacion>{
    console.log(JSON.stringify(presentacion));

    return this.http.post<Presentacion>(this.ROOT_URL, presentacion, this.httpOptions).pipe(
      tap((newPresentacion: Presentacion) => this.log(`added presentacion w/ id=${newPresentacion.id}`)),
      catchError(this.handleError<Presentacion>('createPresentacion'))
    );
  }

  deletePresentacion(presentacion: Presentacion | number): Observable<Presentacion>{
    console.log(JSON.stringify(presentacion));
    const id = typeof presentacion === 'number' ? presentacion : presentacion.id;
    const url = `${this.ROOT_URL}/${id}`;

    return this.http.delete<Presentacion>(url).pipe(
      tap(_ => this.log(`deleted presentacion id=${id}`)),
      catchError(this.handleError<Presentacion>('deletePresentacion'))
    );

  }


  editPresentacion(presentacion: Presentacion): Observable<Presentacion>{
    console.log(JSON.stringify(presentacion));
    const id = typeof presentacion === 'number' ? presentacion : presentacion.id;
    const url = `${this.ROOT_URL}/${id}`;

    return this.http.put<Presentacion>(url, presentacion, this.httpOptions).pipe(
      tap(_ => this.log(`updated presentacion id=${presentacion.id}`)),
      catchError(this.handleError<any>('editPresentacion'))
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
  console.log(`Tipo Service: ${message}`);
}

}
