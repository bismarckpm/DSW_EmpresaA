import { SolicitudesServicioService } from './../../services/solicitudes-servicio.service';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Solicitud_Estudio } from 'src/app/interfaces/solicitud_estudio';
import { Producto } from 'src/app/interfaces/producto';

@Component({
  selector: 'app-dialog-solicitudes',
  templateUrl: './dialog-solicitudes.component.html',
  styleUrls: ['./dialog-solicitudes.component.css']
})
export class DialogSolicitudesComponent implements OnInit {

  idSolicitud = 0;
  producto: Producto[] = [];
  constructor(@Inject(MAT_DIALOG_DATA) public data: Solicitud_Estudio,
              private solicitud: SolicitudesServicioService) { }

  ngOnInit(): void {
    this.idSolicitud = this.data.id!;

    this.solicitud.getProducto(this.idSolicitud).subscribe(
      (product: Producto[]) => {
         this.producto =  product;
         console.log(this.producto);
      }
    );
  }

}
