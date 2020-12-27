import { UsuarioServicioService } from '../../../../services/usuario-servicio.service';
import { EstudioService } from '../../../../services/estudio.service';
import { Estudio, GetEstudio } from '../../../../interfaces/estudio';
import { Usuario } from 'src/app/interfaces/usuario';

import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { DialogconsultarestudioComponent } from '../dialogconsultarestudio/dialogconsultarestudio.component';

@Component({
  selector: 'app-consultar-estudios',
  templateUrl: './consultar-estudios.component.html',
  styleUrls: ['./consultar-estudios.component.css']
})
export class ConsultarEstudiosComponent implements OnInit {

  usuarios: Usuario[] = [];
  estudios: GetEstudio[] = [];
  idUsuario: number = 0;

  constructor(private usuario: UsuarioServicioService,
              private estudio: EstudioService,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.usuario.traerUsuarios().subscribe(
      (usuarios: Usuario[]) => {
        this.usuarios = usuarios;
      }
    );
  }

  busquedaEstudios() {
    console.log(this.idUsuario);
    this.estudio.getEstudios(this.idUsuario).subscribe(
      (estudios: GetEstudio[]) => {
        this.estudios = estudios;
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






}
