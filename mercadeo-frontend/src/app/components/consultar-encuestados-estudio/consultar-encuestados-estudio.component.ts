import { UsuarioServicioService } from './../../services/usuario-servicio.service';
import { EstudioService } from './../../services/estudio.service';
import { Estudio } from '../../interfaces/estudio';
import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/interfaces/usuario';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DialogMostrarUsuarioComponent } from '../admin/admin_usuario/dialog-mostrar-usuario/dialog-mostrar-usuario.component';

@Component({
  selector: 'app-consultar-encuestados-estudio',
  templateUrl: './consultar-encuestados-estudio.component.html',
  styleUrls: ['./consultar-encuestados-estudio.component.css']
})
export class ConsultarEncuestadosEstudioComponent implements OnInit {

  idA = 0;
  idR = 0;
  idEstudio = 0;
  estudios: Estudio[] = [];
  users: Usuario[] = [];
  constructor(private est: EstudioService, private user: UsuarioServicioService,
              public dialog: MatDialog ) { }

  ngOnInit(): void {
      this.idA = 0;
      this.idR = 2;

      this.est.getEstudios(this.idA).subscribe(
        (estudios: Estudio[]) => {
          this.estudios = estudios;
        }
      );
  }

   busquedaEncuestados() {
     this.user.getUsuariosEncuestados(this.idEstudio).subscribe(
      (usuarios: Usuario[]) => {
        this.users = usuarios;
      }
    );
  }

  openDialogU(user: Usuario): void {

    const dialogConfig = new MatDialogConfig();

    dialogConfig.data = {
      id: user.id,
      nombreUsuario: user.nombreUsuario,
      correo: user.correo,
      estado: user.estado,
      password: user.password,
      rolDto: user.rolDto,
      datoUsuarioDto: user.datoUsuarioDto

    }
    const dialogRef = this.dialog.open(DialogMostrarUsuarioComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(result => {
        console.log('Dialog closed');
      });
  }

}
/* http://localhost:3000/estudios?id=2&estudios?id=pregunta_estudio?fk_estudio&pregunta_estudio?id=respuesta?fk_preguntaEstudio&respuesta?fk_usuario=usuarios?id

http://localhost:3000/usuarios?id=respuesta?fk_usuario&respuesta?fk_preguntaEstudio=pregunta_estudio?id&pregunta_estudio?fk_estudio=&estudios?id&estudios?id=2 */








/* usuario?id=respuesta?fk_usuario




*/
