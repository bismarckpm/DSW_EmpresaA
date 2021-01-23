import { UsuarioServicioService } from '../../../../services/usuario-servicio.service';
import { EstudioService } from '../../../../services/estudio.service';
import { Estudio, GetEstudio } from '../../../../interfaces/estudio';
import { Usuario } from 'src/app/interfaces/usuario';

import { Component, OnInit, ViewChild } from '@angular/core';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { DialogconsultarestudioComponent } from '../dialogconsultarestudio/dialogconsultarestudio.component';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';


export interface UserData {
  id: string;
  name: string;
  progress: string;
  color: string;
}

/** Constants used to fill up our data base. */
const COLORS: string[] = [
  'maroon', 'red', 'orange', 'yellow', 'olive', 'green', 'purple', 'fuchsia', 'lime', 'teal',
  'aqua', 'blue', 'navy', 'black', 'gray'
];
const NAMES: string[] = [
  'Maia', 'Asher', 'Olivia', 'Atticus', 'Amelia', 'Jack', 'Charlotte', 'Theodore', 'Isla', 'Oliver',
  'Isabella', 'Jasper', 'Cora', 'Levi', 'Violet', 'Arthur', 'Mia', 'Thomas', 'Elizabeth'
];

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
  /* displayedColumns: string[] = ['id', 'nombre de estudio', 'estatus', 'accion']; */
  dataSource!: MatTableDataSource<any>;
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private usuario: UsuarioServicioService,
              private estudio: EstudioService,
              public dialog: MatDialog,
              private navegacion: Router,
              private _snackBar: MatSnackBar) {


              }

  ngOnInit(): void {
    this.busquedaEstudios();
  }

   ngAfterViewInit() {
  }

  busquedaEstudios() {
    this.isWait=true;
    this.estudio.getEstudios(0).subscribe(
      (estudios: GetEstudio[]) => {
        this.estudios = estudios;
        this.isWait=false;

        console.log(this.estudios[0]._id);
        console.log(this.estudios[0]._fechaInicio);
        console.log(this.estudios[0]._fechaFin);
        console.log(this.estudios[0]._estatus);
        console.log(this.estudios[0]._nombre);

      }
    );
  }

  eliminarEstudio(id: number) {


      console.log(id);
      this.estudio.deleteEstudio(id);

      this._snackBar.open('Estudio Eliminado exitosamente', undefined, {
        duration: 1000,
        horizontalPosition: this.horizontalPosition,
        verticalPosition: this.verticalPosition,
      });

      setTimeout(() => {
        this.busquedaEstudios();
        }, 1000);
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
