import { Router, ActivatedRoute } from '@angular/router';
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
  analistas: Usuario[] = [];
  estudios: Estudio[] = [];

  constructor(private solicitud: SolicitudesServicioService,
              private user: UsuarioServicioService, private estudio: EstudioService,
              private navegacion: Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.idSolicitud = this.route.snapshot.params['idSolicitud'];
    console.log(this.idSolicitud);

    this.user.getUsuariosAnalista(2).subscribe(
      (analista: Usuario[]) => {
        this.analistas = analista;
      }
    );
  }

  asignarEstudio(){

    console.log(this.fechaI);

    let estudio: Estudio = {
      nombre: this.nombreEs,
      fechaInicio: this.fechaI,
      estatus: 'En Espera',
      estado: 'A',
      solicitudEstudioDto: Number(this.idSolicitud),
      usuarioDto: this.idAnalista
    };

    this.estudio.createEstudio(estudio);
    this.navegacion.navigate(['consultarestudios']);
  }

 }
