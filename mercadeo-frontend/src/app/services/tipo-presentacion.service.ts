import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, retry, tap } from 'rxjs/operators';
import { GetProductoTipoPresentacion, ProductoTipoPresentacion } from '../interfaces/producto';

@Injectable({
  providedIn: 'root'
})
export class TipoPresentacionService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  readonly ROOT_URL = '//45.76.60.252:8282/mercadeo-backend/api/producto_tipo_presentacion';


  constructor(private http: HttpClient) { }


  createProductoTipoPresentacion(productoTipoPresentacion: ProductoTipoPresentacion): Observable<any>{
    console.log(JSON.stringify(productoTipoPresentacion));

    return this.http.post<ProductoTipoPresentacion>(this.ROOT_URL+'/agregar', productoTipoPresentacion, this.httpOptions).pipe(
      tap((newTP: ProductoTipoPresentacion) => {this.log(`added newTP w/ id=${newTP.id}`)
    }
      ),
      catchError(this.handleError<ProductoTipoPresentacion>('createProductoTipoPresentacion')),
    );
  }


  getProductoTipoPresentacion(): Observable<any> {
    return this.http.get<GetProductoTipoPresentacion[]>(this.ROOT_URL+"/buscar").pipe(retry(2),
      catchError(this.handleError<GetProductoTipoPresentacion[]>('getProductoTipoPresentacion', []))
    );
  }

  getPTP(id: number): Observable<any> {
    const url = `${this.ROOT_URL}/${id}`;
    return this.http.get<GetProductoTipoPresentacion>(this.ROOT_URL+"/consultar/"+id).pipe(
      tap(_ => this.log(`fetched PTP id=${id}`)),
      catchError(this.handleError<GetProductoTipoPresentacion>(`getPTP id=${id}`))
    );

}

  editProductoTipoPresentacion(productoTipoPresentacion: ProductoTipoPresentacion): Observable<any>{
    console.log(JSON.stringify(productoTipoPresentacion));
    const id = typeof productoTipoPresentacion === 'number' ? productoTipoPresentacion : productoTipoPresentacion.id;
    const url = `${this.ROOT_URL}/actualizar/${id}`;

    return this.http.put<ProductoTipoPresentacion>(url, productoTipoPresentacion, this.httpOptions).pipe(
      tap(_ => this.log(`updated productotipopresentacion id=${productoTipoPresentacion.id}`)),
      catchError(this.handleError<any>('editProductoTipoPresentacion'))
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
  console.log(`Producto Service: ${message}`);
}

}
