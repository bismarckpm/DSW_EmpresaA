import { EstudioService } from './../../services/estudio.service';
import { UsuarioServicioService } from './../../services/usuario-servicio.service';
import { SolicitudesServicioService } from './../../services/solicitudes-servicio.service';
import { Usuario } from './../../models/usuario';
import { Solicitud_Estudio } from './../../models/solicitud_estudio';
import { Component, OnInit } from '@angular/core';
import { Estudio } from 'src/app/models/estudio';

@Component({
  selector: 'app-crear-estudio',
  templateUrl: './crear-estudio.component.html',
  styleUrls: ['./crear-estudio.component.css']
})
export class CrearEstudioComponent implements OnInit {

  nombreEs = '';
  tipoIns = '';
  fechaI = '';
  fechaF = '';
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
    this.estudio.getEstudios(this.codigo).subscribe(
      (est: Estudio[]) => {
        this.estudios = est;
        this.codigo = this.estudios.slice(-1)[0].id;
      }
    );


    this.solicitud.getSolicitudes().subscribe(
      (sol: Solicitud_Estudio[]) => {
        this.solicitudes = sol;
      }
    );

    this.user.getUsuariosAnalista(2).subscribe(
      (analista: Usuario[]) => {
        this.analistas = analista;
      }
    );
  }

  asignarEstudio(){
    let estudio = new Estudio(this.codigo + 1, this.nombreEs, this.tipoIns, this.fechaI,
                  this.fechaF, this.estatus, this.estado, this.idSolicitud, this.idAnalista);

    this.estudio.createEstudio(estudio);
  }
 }
