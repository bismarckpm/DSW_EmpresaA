import { EstudioService } from '../../../../services/estudio.service';
import { UsuarioServicioService } from '../../../../services/usuario-servicio.service';
import { SolicitudesServicioService } from '../../../../services/solicitudes-servicio.service';
import { GetUsuario, Usuario } from '../../../../interfaces/usuario';
import { Solicitud_Estudio } from '../../../../interfaces/solicitud_estudio';
import { Component, OnInit } from '@angular/core';
import { Estudio } from 'src/app/interfaces/estudio';

@Component({
  selector: 'app-crear-estudio',
  templateUrl: './crear-estudio.component.html',
  styleUrls: ['./crear-estudio.component.css']
})
export class CrearEstudioComponent implements OnInit {

  nombreEs = '';
  tipoIns = '';
  fechaI = new Date();
  fechaF = new Date();
  estatus = '';
  estado = '';
  idSolicitud = 0;
  idAnalista = 0;
  codigo = 0;

  solicitudes: Solicitud_Estudio[] = [];
  analistas: Usuario[] = [];
  estudios: Estudio[] = [];
  constructor(private solicitud: SolicitudesServicioService,
              private user: UsuarioServicioService, private estudio: EstudioService ) { }

  ngOnInit(): void {
    /* this.estudio.getEstudios(this.codigo).subscribe(
      (est: Estudio[]) => {
        this.estudios = est;
        /* this.codigo = this.estudios.slice(-1)[0].id!;
      }
    ); */


    /* this.solicitud.getSolicitudes().subscribe(
      (sol: Solicitud_Estudio[]) => {
        this.solicitudes = sol;
      }
    ); */

    this.user.getUsuariosAnalista(2).subscribe(
      (analista: Usuario[]) => {
        this.analistas = analista;
      }
    );
  }

  asignarEstudio(){
    /* let solic = new Solicitud_Estudio(this.idSolicitud);
    let analist = new Usuario(this.idAnalista); */

    this.idSolicitud = 1;
    console.log(this.fechaI);
    console.log(this.fechaF);

    let estudio: Estudio = {
      nombre: this.nombreEs,
      tipoDeInstrumento: 'Instrumento1',
      fechaInicio: this.fechaI,
      fechaFin: this.fechaF,
      estatus: this.estatus,
      estado: 'A',
      solicitudEstudioDto: this.idSolicitud,
      usuarioDto: this.idAnalista
    };

    this.estudio.createEstudio(estudio);
  }
 }
