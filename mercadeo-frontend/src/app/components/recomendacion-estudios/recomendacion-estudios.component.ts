import { Estudio, GetEstudio } from './../../interfaces/estudio';
import { Subscription } from 'rxjs';
import { EstudioService } from 'src/app/services/estudio.service';
import { SolicitudesServicioService } from './../../services/solicitudes-servicio.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-recomendacion-estudios',
  templateUrl: './recomendacion-estudios.component.html',
  styleUrls: ['./recomendacion-estudios.component.css']
})
export class RecomendacionEstudiosComponent implements OnInit {

  idSolicitud = 0;
  estudios: GetEstudio[] = [];
  constructor(private route: ActivatedRoute,
              private estudiosR: EstudioService) { }

  ngOnInit(): void {
    this.idSolicitud = this.route.snapshot.params['idSolicitud'];

    this.estudiosR.getPlantilla(this.idSolicitud).subscribe(
      (estudios: GetEstudio[]) => {
        this.estudios = estudios;
        console.log(this.estudios);
      }
    );


  }

  crearEstudio(est: GetEstudio) {
    let estudio: Estudio = {
      nombre: 'Estudio creado con Plantilla',
      fechaInicio: est._fechaInicio,
      /* fechaFin: this.fechaF, */
      estatus: 'En Espera',
      estado: 'A',
      solicitudEstudioDto: Number(this.idSolicitud),
      usuarioDto: 1
    };

    this.estudiosR.createEstudio(estudio);
  }
}
