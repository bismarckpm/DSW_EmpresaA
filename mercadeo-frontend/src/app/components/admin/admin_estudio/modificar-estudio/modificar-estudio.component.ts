import { Usuario } from '../../../../interfaces/usuario';
import { UsuarioServicioService } from '../../../../services/usuario-servicio.service';
import { Solicitud_Estudio } from '../../../../interfaces/solicitud_estudio';
import { SolicitudesServicioService } from '../../../../services/solicitudes-servicio.service';
import { EstudioService } from '../../../../services/estudio.service';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Estudio, GetEstudio, SetEstudio } from 'src/app/interfaces/estudio';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-modificar-estudio',
  templateUrl: './modificar-estudio.component.html',
  styleUrls: ['./modificar-estudio.component.css']
})
export class ModificarEstudioComponent implements OnInit {

  @Input() id: number = 0;
  nombreEs: string = '';
  tipoIns: string = '';
  fechaI: string = '';
  fechaF: string = '';
  estatus: string = '';
  estado: string = '';
  fkSol: number = 0;
  fkUser: number= 0;
  fechaFn = new Date();
  fechaIn = new Date();
  estudios: GetEstudio[] = [];
  solicitudes: Solicitud_Estudio[] = [];
  analistas: Usuario[] = [];
  estados: string[] = [];
  constructor(private route: ActivatedRoute, private estudio: EstudioService,
              private soli: SolicitudesServicioService, private usuarios: UsuarioServicioService,
              public datepipe: DatePipe, private navegacion: Router) { }

  ngOnInit(): void {
    this.soli.getSolicitudes().subscribe(
      (solic: Solicitud_Estudio[]) => {
        this.solicitudes = solic;
      }
    );

    this.usuarios.getUsuariosAnalista(2).subscribe(
      (analista: Usuario[]) => {
        this.analistas = analista;
      }
    );

    console.log(this.id);
    this.id = this.route.snapshot.params['idEst'];
    console.log(this.id);


    this.estudio.getEstudio(this.id).subscribe(

      (estudio: GetEstudio) => {
        this.estudios.push(estudio);
        console.log(estudio);
        this.nombreEs = this.estudios[0]._nombre;
        console.log(this.nombreEs);
        this.fechaIn = this.estudios[0]._fechaInicio;
        this.estatus = this.estudios[0]._estatus;
        this.estado = this.estudios[0]._estado;
        this.fkSol = this.estudios[0]._solicitudEstudio._id;
        this.fkUser = this.estudios[0]._usuario._id;
        /* this.fechaI = this.datepipe.transform(this.fechaIn, 'yyyy-MM-dd')!;
        this.fechaF = this.datepipe.transform(this.fechaFn, 'yyyy-MM-dd')!; */
        /* console.log(this.fechaFn); */
        console.log(this.fechaIn);
        console.log(this.fkSol);
        console.log(this.fkUser);

      }
    );
  }

  actualizarEstudio() {
    /* let solic = new Solicitud_Estudio(this.fkSol);
    let user = new Usuario(this.fkUser); */


    let estudioE: SetEstudio = {
      nombre: this.nombreEs,
      fechaInicio: this.fechaIn,
      fechaFin: this.fechaFn,
      estatus: this.estatus,
      estado: this.estado,
      solicitudEstudioDto: this.fkSol,
      usuarioDto: this.fkUser
    };

    this.estudio.setEstudio(this.id, estudioE);
    this.atras();
  }

  atras() {
    this.navegacion.navigate(['consultarestudios']);
  }

}
 /* onGuardarUsuario() {
    let usuarioE = new Usuario(this.indice, this.nombreU, this.correo,
      this.usuario.estado, this.usuario.codigoRecuperacion, this.usuario.password, this.usuario.fk_rol,
      this.usuario.fk_datoUsuario);
    this.usuarioService.modificarUsuario(this.indice, usuarioE);
  } */
