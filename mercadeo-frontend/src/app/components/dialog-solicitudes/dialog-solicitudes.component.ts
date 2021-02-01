import { GetInformacion } from './../../interfaces/producto';
import { SolicitudesServicioService } from './../../services/solicitudes-servicio.service';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Solicitud_Estudio } from 'src/app/interfaces/solicitud_estudio';
import { Producto } from 'src/app/interfaces/producto';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';


@Component({
  selector: 'app-dialog-solicitudes',
  templateUrl: './dialog-solicitudes.component.html',
  styleUrls: ['./dialog-solicitudes.component.css']
})
export class DialogSolicitudesComponent implements OnInit {

  idSolicitud = 0;
  producto: GetInformacion[] = [];
  nombreP = '';
  marca = '';
  categoria = '';
  subcategoria = '';
  subcategoriaN = '';
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  constructor(@Inject(MAT_DIALOG_DATA) public data: Solicitud_Estudio,
              private solicitud: SolicitudesServicioService,
              private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.idSolicitud = this.data.id!;

    this._snackBar.open('Por favor Espere', undefined, {
      duration: 3000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });

    this.solicitud.getProducto(this.idSolicitud).subscribe(
      (product: GetInformacion[]) => {
         console.log(product);
         this.producto = product;
         console.log(product);
         console.log(this.producto[0].producto._nombre);
         console.log(this.producto[0].marca._nombre);
         console.log(this.producto[0].subcategoria._descripcion);
         this.nombreP = this.producto[0].producto._nombre;
         this.marca = this.producto[0].marca._nombre;
         this.categoria = this.producto[0].categoria._nombre;
         this.subcategoria = this.producto[0].subcategoria._descripcion;
         this.subcategoriaN = this.producto[0].subcategoria._nombre;
      }
    );
  }

}
