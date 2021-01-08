import { DialogSolicitudesComponent } from '../dialog-solicitudes/dialog-solicitudes.component';
import { Solicitud_Estudio, GetSolicitud_Estudio } from './../../interfaces/solicitud_estudio';
import { SolicitudesServicioService } from './../../services/solicitudes-servicio.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DialogCrearEstudioComponent } from '../dialog-crear-estudio/dialog-crear-estudio.component';
import { isThisTypeNode } from 'typescript';

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

        console.log(this.solicitudes[0]);
      }
    );
  }

  //otro metodo mandando el id de la solicitud

  openDialogE(id: number): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.data = {
      id,
    },
    dialogConfig.closeOnNavigation = true;

    const dialogRef = this.dialog.open(DialogCrearEstudioComponent, dialogConfig);

    this.navegacion.events
    .subscribe(() => {
      dialogRef.close();
    });

    dialogRef.afterClosed().subscribe(result => {
        console.log('Dialog closed');
      });
  }

  // Para mostrar la informacion del producto, marca, categorias y subcategorias de la solicitud
  openDialogM(id: number): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.data = {
      id,
    },
    dialogConfig.closeOnNavigation = true;

    const dialogRef = this.dialog.open(DialogSolicitudesComponent, dialogConfig);

    this.navegacion.events
    .subscribe(() => {
      dialogRef.close();
    });

    dialogRef.afterClosed().subscribe(result => {
        console.log('Dialog closed');
      });
  }
}
