import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { forkJoin, Observable, of } from 'rxjs';
import { catchError, mergeMap, retry, tap } from 'rxjs/operators';
import { Subcategoria } from '../interfaces/subcategoria';

@Injectable({
  providedIn: 'root'
})
export class SubcategoriaService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  readonly ROOT_URL = '//localhost:8080/mercadeo-backend/api/subcategoria';
  constructor(private http: HttpClient) { }

  getSubcategorias(): Observable<Subcategoria[]> {
    return this.http.get<Subcategoria[]>(this.ROOT_URL+"/buscar").pipe(retry(1),
      catchError(this.handleError<Subcategoria[]>('getSubcategoria', []))
    );
  }


  getSubcategoria(id: number): Observable<Subcategoria> {
    const url = `${this.ROOT_URL}/${id}`;
    return this.http.get<Subcategoria>(url).pipe(
      tap(_ => this.log(`fetched Subcategoria id=${id}`)),
      catchError(this.handleError<Subcategoria>(`getSubcategoria id=${id}`))
    );

}


  createSubcategoria(subcategoria: Subcategoria): Observable<Subcategoria>{
    console.log(JSON.stringify(subcategoria));

    return this.http.post<Subcategoria>(this.ROOT_URL, subcategoria, this.httpOptions).pipe(
      tap((newSubcategoria: Subcategoria) => this.log(`added subcategoria w/ id=${newSubcategoria.id}`)),
      catchError(this.handleError<Subcategoria>('createSubcategoria'))
    );
  }

  deleteSubcategoria(subcategoria: Subcategoria | number): Observable<Subcategoria>{
    console.log(JSON.stringify(subcategoria));
    const id = typeof subcategoria === 'number' ? subcategoria : subcategoria.id;
    const url = `${this.ROOT_URL}/${id}`;

    return this.http.delete<Subcategoria>(url).pipe(
      tap(_ => this.log(`deleted subcategoria id=${id}`)),
      catchError(this.handleError<Subcategoria>('deleteSubcategoria'))
    );

  }


  editSubcategoria(subcategoria: Subcategoria): Observable<Subcategoria>{
    console.log(JSON.stringify(subcategoria));
    const id = typeof subcategoria === 'number' ? subcategoria : subcategoria.id;
    const url = `${this.ROOT_URL}/${id}`;

    return this.http.put<Subcategoria>(url, subcategoria, this.httpOptions).pipe(
      tap(_ => this.log(`updated subcategoria id=${subcategoria.id}`)),
      catchError(this.handleError<any>('editSubcategoria'))
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
  console.log(`SubCategoriaService: ${message}`);
}

  
}
