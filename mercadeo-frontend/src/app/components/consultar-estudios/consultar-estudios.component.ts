import { UsuarioServicioService } from './../../services/usuario-servicio.service';
import { EstudioService } from './../../services/estudio.service';
import { Estudio } from './../../models/estudio';
import { Usuario } from 'src/app/models/usuario';

import { Component, OnInit } from '@angular/core';
import { faCheck, faStop, faTrash, faEdit } from '@fortawesome/free-solid-svg-icons';
import {MatDialog, MatDialogConfig, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { DialogconsultarestudioComponent } from '../dialogconsultarestudio/dialogconsultarestudio.component';

@Component({
  selector: 'app-consultar-estudios',
  templateUrl: './consultar-estudios.component.html',
  styleUrls: ['./consultar-estudios.component.css']
})
export class ConsultarEstudiosComponent implements OnInit {

  usuarios: Usuario[] = [];
  estudios: Estudio[] = [];
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
      (estudios: Estudio[]) => {
        this.estudios = estudios;
      }
    );
  }

  eliminarEstudio(estudio: Estudio) {
    if (estudio.status === 'P'){
      this.estudio.deleteEstudio(estudio.id!);
    }
  }

  openDialog(est: Estudio): void {
    console.log(est.id);

    const dialogConfig = new MatDialogConfig();

    dialogConfig.data = {
      id: est.id,
      tipoInstrumento: est.tipoInstrumento,
      nombre: est.nombre,
      fechaInicio: est.fechaInicio,
      fechaFinal: est.fechaFinal,
      status: est.status,
      estado: est.estado
    }

    const dialogRef = this.dialog.open(DialogconsultarestudioComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(result => {
        console.log('Dialog closed');
      });
  }






}
