import { UsuarioServicioService } from '../../../../services/usuario-servicio.service';
import { EstudioService } from '../../../../services/estudio.service';
import { Estudio, GetEstudio, SetEstudio } from '../../../../interfaces/estudio';
import { Usuario } from 'src/app/interfaces/usuario';

import { Component, OnInit, ViewChild } from '@angular/core';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { DialogconsultarestudioComponent } from '../dialogconsultarestudio/dialogconsultarestudio.component';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { AlertService } from 'src/app/services/alert.service';


export interface UserData {
  id: string;
  name: string;
  progress: string;
  color: string;
}



@Component({
  selector: 'app-consultar-estudios',
  templateUrl: './consultar-estudios.component.html',
  styleUrls: ['./consultar-estudios.component.css']
})
export class ConsultarEstudiosComponent implements OnInit {

  usuarios: Usuario[] = [];
  estudios: GetEstudio[] = [];
  idUsuario: number = 0;
  isWait=false;
  dataSource!: MatTableDataSource<any>;
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  // Alerts
  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };


  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private usuario: UsuarioServicioService,
              private estudio: EstudioService,
              public dialog: MatDialog,
              private navegacion: Router,
              private _snackBar: MatSnackBar,
              private alertService: AlertService,

              )
              {}

  ngOnInit(): void {
    this._snackBar.open('Por favor espere, cargando estudios', undefined, {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
    setTimeout(() => {
      this.busquedaEstudios();
      }, 3000);

  }

   ngAfterViewInit() {
  }

  busquedaEstudios() {
    this.isWait=true;
    this.estudio.getEstudios(0).subscribe(
      (estudios) => {

        this.estudios = estudios.objeto;
        this.isWait=false;

        console.log(this.estudios[0]._id);
        console.log(this.estudios[0]._fechaInicio);
        console.log(this.estudios[0]._fechaFin);
        console.log(this.estudios[0]._estatus);
        console.log(this.estudios[0]._nombre);

      }
    );
  }

  eliminarEstudio(id: number, estudio: GetEstudio) {


      console.log(id);

      let estudioE: SetEstudio = {
        nombre: estudio._nombre,
        fechaInicio: estudio._fechaInicio,
        fechaFin: estudio._fechaFin,
        estatus: estudio._estatus,
        estado: 'I',
        conclusion: estudio._conclusion,
        solicitudEstudioDto: estudio._solicitudEstudio._id,
        usuarioDto: estudio._usuario._id
      };

      setTimeout(() => {
        this.estudio.deleteEstudio(id, estudioE);
      }, 1000);


      this._snackBar.open('Estudio Eliminado exitosamente', undefined, {
        duration: 2000,
        horizontalPosition: this.horizontalPosition,
        verticalPosition: this.verticalPosition,
      });

      setTimeout(() => {
        this.busquedaEstudios();
        }, 3000);
    //}
  }

  openDialog(est: GetEstudio): void {
    console.log(est._id);

    const dialogConfig = new MatDialogConfig();

    dialogConfig.data = {
      id: est._id,
      nombre: est._nombre,
      fechaInicio: est._fechaInicio,
      fechaFin: est._fechaFin,
      estatus: est._estatus,
      estado: est._estado
    };

    const dialogRef = this.dialog.open(DialogconsultarestudioComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(result => {
        console.log('Dialog closed');
      });
  }


  listaPreguntas(id: number){
    this.navegacion.navigate(['asignarpreguntasaestudio', id]);
  }

  atras(){
    this.navegacion.navigate(['listasolicitudes']);
  }
}
