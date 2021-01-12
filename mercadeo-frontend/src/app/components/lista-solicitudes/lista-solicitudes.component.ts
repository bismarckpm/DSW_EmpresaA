import { DialogSolicitudesComponent } from '../dialog-solicitudes/dialog-solicitudes.component';
import { Solicitud_Estudio, GetSolicitud_Estudio } from './../../interfaces/solicitud_estudio';
import { SolicitudesServicioService } from './../../services/solicitudes-servicio.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DialogCrearEstudioComponent } from '../dialog-crear-estudio/dialog-crear-estudio.component';
import { isThisTypeNode } from 'typescript';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-lista-solicitudes',
  templateUrl: './lista-solicitudes.component.html',
  styleUrls: ['./lista-solicitudes.component.css']
})
export class ListaSolicitudesComponent implements OnInit {
  isWait=false;
  solicitudes: GetSolicitud_Estudio[] = [];
  constructor(private navegacion: Router,
              private sol: SolicitudesServicioService,
              public dialog: MatDialog,
              private _loginService: LoginService) { }

  ngOnInit(): void {
    this.isWait = true;
    this.sol.getSols().subscribe(
      (sols: GetSolicitud_Estudio[]) => {
        this.solicitudes = sols;
        console.log(this.solicitudes[0]);
        this.isWait = false;
      }
    );
  }

  //Dialog para crear estudio

  openDialogE(id: number): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.data = {
      id,
    },
    dialogConfig.closeOnNavigation = true;

    const dialogRef = this.dialog.open(DialogCrearEstudioComponent, dialogConfig);

    this.navegacion.events.subscribe(() =>{
      this.dialog.closeAll();
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

    dialogRef.afterClosed().subscribe(result => {
        console.log('Dialog closed');
      });
  }

  atras() {
    this.navegacion.navigate(['admin']);
  }
}
