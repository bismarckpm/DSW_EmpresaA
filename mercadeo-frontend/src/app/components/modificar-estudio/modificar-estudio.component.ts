import { Usuario } from './../../models/usuario';
import { UsuarioServicioService } from './../../services/usuario-servicio.service';
import { Solicitud_Estudio } from './../../models/solicitud_estudio';
import { SolicitudesServicioService } from './../../services/solicitudes-servicio.service';
import { EstudioService } from './../../services/estudio.service';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Estudio } from 'src/app/models/estudio';
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
  fechaFn!: Date;
  fechaIn!: Date;
  estudios: Estudio[] = [];
  solicitudes: Solicitud_Estudio[] = [];
  analistas: Usuario[] = [];
  constructor(private route: ActivatedRoute, private estudio: EstudioService,
    private soli: SolicitudesServicioService, private usuarios: UsuarioServicioService,
    public datepipe: DatePipe) { }

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

      (estudio: Estudio[]) => {
        this.estudios  = estudio;
        this.nombreEs = this.estudios[0].nombre!;
        this.tipoIns = this.estudios[0].tipoInstrumento!;
        this.fechaIn = this.estudios[0].fechaInicio!;
        this.fechaFn = this.estudios[0].fechaFinal!;
        this.estatus = this.estudios[0].status!;
        this.estado = this.estudios[0].estado!;
        this.fechaI = this.datepipe.transform(this.fechaIn, 'yyyy-MM-dd')!;
        this.fechaF = this.datepipe.transform(this.fechaFn, 'yyyy-MM-dd')!;
        /* this.fechaI = this.fechaIn.;
        this.fechaF = this.fechaFn.toDateString(); */
      }
    );
  }

  actualizarEstudio() {
    let solic = new Solicitud_Estudio(this.fkSol);
    let user = new Usuario(this.fkUser);
    this.fechaFn = new Date(this.fechaF);
    this.fechaIn = new Date(this.fechaI);

    let estudioE = new Estudio(this.id, this.nombreEs, this.tipoIns, this.fechaIn,
      this.fechaFn, this.estatus, this.estado, solic, user);

    this.estudio.setEstudio(this.id, estudioE);
  }



}
 /* onGuardarUsuario() {
    let usuarioE = new Usuario(this.indice, this.nombreU, this.correo,
      this.usuario.estado, this.usuario.codigoRecuperacion, this.usuario.password, this.usuario.fk_rol,
      this.usuario.fk_datoUsuario);
    this.usuarioService.modificarUsuario(this.indice, usuarioE);
  } */
