import { Solicitud_Estudio, GetSolicitud_Estudio } from './../../interfaces/solicitud_estudio';
import { SolicitudesServicioService } from './../../services/solicitudes-servicio.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DialogCrearEstudioComponent } from '../dialog-crear-estudio/dialog-crear-estudio.component';

@Component({
  selector: 'app-lista-solicitudes',
  templateUrl: './lista-solicitudes.component.html',
  styleUrls: ['./lista-solicitudes.component.css']
})
export class ListaSolicitudesComponent implements OnInit {

  solicitudes: GetSolicitud_Estudio[] = [];
  constructor(private navegacion: Router,
              private sol: SolicitudesServicioService,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.sol.getSols().subscribe(
      (sols: GetSolicitud_Estudio[]) => {
        this.solicitudes = sols;

        console.log(this.solicitudes);
        /* console.log(this.solicitudes[0]);
        console.log(this.solicitudes[0]);
        console.log(this.solicitudes[0]);
        console.log(this.solicitudes[0]); */

      }
    );
  }


  openDialogE(id: number): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.data = {
      id,
    };

    const dialogRef = this.dialog.open(DialogCrearEstudioComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(result => {
        console.log('Dialog closed');
      });
  }
}
