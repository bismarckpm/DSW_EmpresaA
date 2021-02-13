import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { forkJoin, Observable, of } from 'rxjs';
import { catchError, mergeMap, retry, tap } from 'rxjs/operators';
import { GetSubcategoria, Subcategoria } from '../interfaces/subcategoria';

@Injectable({
  providedIn: 'root'
})
export class SubcategoriaService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  readonly ROOT_URL = '//localhost:8080/mercadeo-backend/api/subcategoria';
  constructor(private http: HttpClient) { }

  getSubcategorias(): Observable<any> {
    return this.http.get<GetSubcategoria[]>(this.ROOT_URL+"/buscar").pipe(retry(2),
      catchError(this.handleError<GetSubcategoria[]>('getSubcategoria', []))
    );
  }


  getSubcategoria(id: number): Observable<any> {
    const url = `${this.ROOT_URL}/consultar/${id}`;
    return this.http.get<GetSubcategoria>(url).pipe(
      tap(_ => this.log(`fetched Subcategoria id=${id}`)),
      catchError(this.handleError<GetSubcategoria>(`getSubcategoria id=${id}`))
    );

}


  createSubcategoria(subcategoria: Subcategoria): Observable<any>{
    console.log(JSON.stringify(subcategoria));

    return this.http.post<Subcategoria>(this.ROOT_URL+"/agregar", subcategoria, this.httpOptions).pipe(
      tap((newSubcategoria: Subcategoria) => this.log(`added subcategoria w/ id=${newSubcategoria.id}`)),
      catchError(this.handleError<Subcategoria>('createSubcategoria'))
    );
  }

  deleteSubcategoria(subcategoria: Subcategoria | number): Observable<any>{
    console.log(JSON.stringify(subcategoria));
    const id = typeof subcategoria === 'number' ? subcategoria : subcategoria.id;
    const url = `${this.ROOT_URL}/${id}`;

    return this.http.delete<Subcategoria>(url).pipe(
      tap(_ => this.log(`deleted subcategoria id=${id}`)),
      catchError(this.handleError<Subcategoria>('deleteSubcategoria'))
    );

  }


  editSubcategoria(subcategoria: Subcategoria): Observable<any>{
    console.log(JSON.stringify(subcategoria));
    
    const id = typeof subcategoria === 'number' ? subcategoria : subcategoria.id;
    const url = `${this.ROOT_URL}/actualizar/${id}`;

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
