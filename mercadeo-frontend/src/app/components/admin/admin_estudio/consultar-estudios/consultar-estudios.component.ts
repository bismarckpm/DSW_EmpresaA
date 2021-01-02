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
  /* displayedColumns: string[] = ['id', 'nombre de estudio', 'estatus', 'accion']; */
  dataSource!: MatTableDataSource<GetEstudio>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private usuario: UsuarioServicioService,
              private estudio: EstudioService,
              public dialog: MatDialog,
              private navegacion: Router) {

    // Create 100 users
    const users = Array.from({length: 100}, (_, k) => this.createNewUser(k + 1));

    // Assign the data to the data source for the table to render
     /* this.dataSource = new MatTableDataSource(users);  */
              }

  ngOnInit(): void {
    /* this.usuario.traerUsuarios().subscribe(
      (usuarios: Usuario[]) => {
        this.usuarios = usuarios;
      }
    ); */
    /* this.busquedaEstudios(); */
  }

   ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  busquedaEstudios() {
    console.log(this.idUsuario);
    this.estudio.getEstudios(0).subscribe(
      (estudios: GetEstudio[]) => {
        this.estudios = estudios;
        this.dataSource = new MatTableDataSource(this.estudios);
        console.log(this.estudios[0]._id);
        console.log(this.estudios[0]._fechaInicio);
        console.log(this.estudios[0]._fechaFin);
        console.log(this.estudios[0]._estatus);
        console.log(this.estudios[0]._nombre);

      }
    );
  }

  eliminarEstudio(estudio: GetEstudio) {
    if (estudio._estatus === 'P'){
      this.estudio.deleteEstudio(estudio._id!);
    }
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


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  createNewUser(id: number): UserData {
    const name = NAMES[Math.round(Math.random() * (NAMES.length - 1))] + ' ' +
        NAMES[Math.round(Math.random() * (NAMES.length - 1))].charAt(0) + '.';

    return {
      id: id.toString(),
      name: name,
      progress: Math.round(Math.random() * 100).toString(),
      color: COLORS[Math.round(Math.random() * (COLORS.length - 1))]
    };
  }

}
