import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of , throwError} from "rxjs";
import { catchError, map, tap, retry } from 'rxjs/operators';
import { Categoria } from 'src/interfaces/categoria';


@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  readonly ROOT_URL = '/api/categoria'
  constructor(private http: HttpClient) { }

  getCategorias(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(this.ROOT_URL).pipe(retry(1),
      catchError(this.handleError<Categoria[]>('getCategoria', []))
    );
  }

  createCategoria(categoria: Categoria): Observable<Categoria>{
    console.log(JSON.stringify(categoria));

    return this.http.post<Categoria>(this.ROOT_URL, categoria, this.httpOptions).pipe(
      tap((newCategoria: Categoria) => this.log(`added categoria w/ id=${newCategoria.id}`)),
      catchError(this.handleError<Categoria>('createCategoria'))
    );
  }

  editCategoria(categoria: Categoria): Observable<Categoria>{
    console.log(JSON.stringify(categoria));
    const id = typeof categoria === 'number' ? categoria : categoria.id;
    const url = `${this.ROOT_URL}/${id}`;

    return this.http.put<Categoria>(url, categoria, this.httpOptions).pipe(
      tap(_ => this.log(`updated categoria id=${categoria.id}`)),
      catchError(this.handleError<any>('editCategoria'))
    );
  }

  deleteCategoria(categoria: Categoria | number): Observable<Categoria>{
    console.log(JSON.stringify(categoria));
    const id = typeof categoria === 'number' ? categoria : categoria.id;
    const url = `${this.ROOT_URL}/${id}`;

    return this.http.delete<Categoria>(url).pipe(
      tap(_ => this.log(`deleted categoria id=${id}`)),
      catchError(this.handleError<Categoria>('deleteCategoria'))
    );
  }



getCategoria(id: number): Observable<Categoria> {
  const url = `${this.ROOT_URL}/${id}`;
  return this.http.get<Categoria>(url).pipe(
    tap(_ => this.log(`fetched categoria id=${id}`)),
    catchError(this.handleError<Categoria>(`getCategoria id=${id}`))
  );
}



/* GET categorias por nombre */
searchCategorias(term: string): Observable<Categoria[]> {
  if (!term.trim()) {
    // if not search term, return empty hero array.
    return of([]);
  }
  return this.http.get<Categoria[]>(`${this.ROOT_URL}/?name=${term}`).pipe(
    tap(x => x.length ?
       this.log(`found categorias matching "${term}"`) :
       this.log(`no categorias matching "${term}"`)),
    catchError(this.handleError<Categoria[]>('searchCategorias', []))
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
  console.log(`CategoriaService: ${message}`);
}


}
