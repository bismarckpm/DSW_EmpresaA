import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { ProductoTipoPresentacion } from '../interfaces/producto';

@Injectable({
  providedIn: 'root'
})
export class TipoPresentacionService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  readonly ROOT_URL = '//localhost:8181/mercadeo-backend/api/producto_tipo_presentacion';

  tipoProducto: ProductoTipoPresentacion[] = [];


  constructor(private http: HttpClient) { }


  createProductoTipoPresentacion(productoTipoPresentacion: ProductoTipoPresentacion[]): Observable<ProductoTipoPresentacion>{
    console.log(JSON.stringify(productoTipoPresentacion));

    return this.http.post<ProductoTipoPresentacion>(this.ROOT_URL+'/agregar', productoTipoPresentacion, this.httpOptions).pipe(
      tap((newProducto: ProductoTipoPresentacion) => {this.log(`added producto w/ id=${newProducto}`)
    }
      ),
      catchError(this.handleError<ProductoTipoPresentacion>('createProductoTipoPresentacion')),
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
