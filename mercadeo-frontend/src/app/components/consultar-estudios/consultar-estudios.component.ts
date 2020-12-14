import { UsuarioServicioService } from './../../services/usuario-servicio.service';
import { EstudioService } from './../../services/estudio.service';
import { Estudio } from './../../models/estudio';
import { Usuario } from 'src/app/models/usuario';

import { Component, OnInit } from '@angular/core';
import { faCheck, faStop, faTrash, faEdit } from '@fortawesome/free-solid-svg-icons';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
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
  faCheck = faCheck;
  faTrash = faTrash;
  faEdit = faEdit;
  faWarning = faStop;
  constructor(private usuario: UsuarioServicioService,
              private estudio: EstudioService,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.usuario.onCargarUsuarios('').subscribe(
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
    if (estudio.estatus === 'P'){
      this.estudio.deleteEstudio(estudio.id);
    }
  }

  openDialog(est: Estudio): void {
    console.log(est.id);
    const dialogRef = this.dialog.open(DialogconsultarestudioComponent, {
      width: '30rem',
      data: {id: est.id,
              tipoInstrumento: est.tipoInstrumento,
              nombre: est.nombre,
              fechaInicio: est.fechaInicio,
              fechaFin: est.fechaFin,
              estatus: est.estatus,
              estado: est.estado
            }
      });

    dialogRef.afterClosed().subscribe(result => {
        console.log('Dialog closed');
      });
  }






}
