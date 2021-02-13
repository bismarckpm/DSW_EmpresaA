import { Usuario } from '../../../../interfaces/usuario';
import { UsuarioServicioService } from '../../../../services/usuario-servicio.service';
import { Solicitud_Estudio } from '../../../../interfaces/solicitud_estudio';
import { SolicitudesServicioService } from '../../../../services/solicitudes-servicio.service';
import { EstudioService } from '../../../../services/estudio.service';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Estudio, GetEstudio, SetEstudio } from 'src/app/interfaces/estudio';
import { DatePipe } from '@angular/common';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';


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
  conclusion: string = '';
  estudios: GetEstudio[] = [];
  // solicitudes: Solicitud_Estudio[] = [];
  analistas: Usuario[] = [];
  estados: string[] = [];
  estatuses: string[] = ['En Proceso', 'Finalizado'];
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  constructor(private route: ActivatedRoute, private estudio: EstudioService,
              private soli: SolicitudesServicioService, private usuarios: UsuarioServicioService,
              public datepipe: DatePipe, private navegacion: Router,
              private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    // this.soli.getSolicitudes().subscribe(
    //   (solic: Solicitud_Estudio[]) => {
    //     this.solicitudes = solic;
    //   }
    // );

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
        // this.estudios.push(estudio.estudio);

        this.estudios.push(estudio);
        console.log(estudio);
        this.nombreEs = this.estudios[0]._nombre;
        console.log(this.nombreEs);
        this.fechaIn = this.estudios[0]._fechaInicio;
        this.estatus = this.estudios[0]._estatus;
        this.estado = this.estudios[0]._estado;
        this.conclusion = this.estudios[0]._conclusion;
        this.fkSol = this.estudios[0]._solicitudEstudio._id;
        this.fkUser = this.estudios[0]._usuario._id;

        console.log(this.fechaIn);
        console.log(this.fkSol);
        console.log(this.fkUser);

      }
    );
  }

  actualizarEstudio() {

    let estudioE: SetEstudio = {
      nombre: this.nombreEs,
      fechaInicio: this.fechaIn,
      fechaFin: this.fechaFn,
      estatus: this.estatus,
      estado: this.estado,
      conclusion: this.conclusion,
      solicitudEstudioDto: this.fkSol,
      usuarioDto: this.fkUser
    };

    setTimeout(() => {
      this.estudio.setEstudio(this.id, estudioE);
      }, 1000);

    this.atras();

    this._snackBar.open('Estudio Modificado exitosamente', undefined, {
      duration: 1000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

  atras() {
    this.navegacion.navigate(['consultarestudios']);
  }

}

