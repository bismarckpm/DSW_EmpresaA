import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { from } from 'rxjs';
import { Nivel_Economico } from 'src/app/models/nivel_economico';
import { Ocupacion } from 'src/app/models/ocupacion';
import { Solicitud_Estudio } from '../../../models/solicitud_estudio';
import { SolicitudestudioService } from '../../../services/solicitudestudio.service';

@Component({
  selector: 'app-registrarsolicitud',
  templateUrl: './registrarsolicitud.component.html',
  styleUrls: ['./registrarsolicitud.component.css'],
  providers: [SolicitudestudioService]
})
export class RegistrarsolicitudComponent implements OnInit {


  registrarSolicitudForm: any;

  nivelEconomico: any; 
  ocupacion: any;
  productos: any;
  isWait = false;

  constructor(
    private fb: FormBuilder,
    private _solicitudEstudioService: SolicitudestudioService
  ) { }

  ngOnInit(): void {
    this.buscarNivelEconomico();
    this.buscarOcupacion();
    this.buscarProductos(1); //Recuerda pasar el id del user
    this.buildForm();
  }


  buildForm(): void {
    this.registrarSolicitudForm = this.fb.group({
     descripcionSolicitud: ["",
     Validators.compose([
       Validators.required]),
    ],
     generoPoblacional: ["",
     Validators.compose([
       Validators.required]),
    ],
    fechaPeticion: ["",
    Validators.compose([
      Validators.required]),
    ],
      edadMinimaPoblacion: ["",
    Validators.compose([
      Validators.required])
    ],
    edadMaximaPoblacion: ["",
    Validators.compose([
      Validators.required])
    ],
    cantidadHijos: ["",
    Validators.compose([
      Validators.required])
    ],
    generoHijos: ["",
    Validators.compose([
      Validators.required])
    ],
    edadMinimaHijos: ["",
    Validators.compose([
      Validators.required])
    ],
    edadMaximaHijos: ["",
    Validators.compose([
      Validators.required])
    ],
    conCuantasPersonasVive: ["",
    Validators.compose([
      Validators.required])
    ],
    disponibilidadEnLinea: ["",
    Validators.compose([
      Validators.required])
    ],
    fk_nivelEconomico: ["",
    Validators.compose([
      Validators.required])
    ],
    fk_producto: ["",
    Validators.compose([
      Validators.required])
    ],
    fk_ocupacion: ["",
    Validators.compose([
      Validators.required])
    ],
   });
 }

buscarNivelEconomico(){
  this._solicitudEstudioService.getNivelEconomico().subscribe(
    response => {
      this.nivelEconomico = response;
      console.log(this.nivelEconomico);
    }
  );
}

buscarOcupacion(){
  this._solicitudEstudioService.getOcupacion().subscribe(
    response => {
      this.ocupacion = response;
      console.log(this.ocupacion);
    }
  )
}

buscarProductos(idUsuario: number){

  this._solicitudEstudioService.getProductos(idUsuario).subscribe(
    response => {
      this.productos = response;
    }
  )
}



  guardar(){

    const NewS: Solicitud_Estudio = {
      id: 0,
      descripcionSolicitud: this.registrarSolicitudForm.get("descripcionSolicitud").value,
      generoPoblacional: this.registrarSolicitudForm.get("generoPoblacional").value,
      fechaPeticion: this.registrarSolicitudForm.get("fechaPeticion").value,
      edadMinimaPoblacion: this.registrarSolicitudForm.get("edadMinimaPoblacion").value,
      edadMaximaPoblacion: this.registrarSolicitudForm.get("edadMaximaPoblacion").value,
      estado: "A",
      cantidadHijos: this.registrarSolicitudForm.get("cantidadHijos").value,
      generoHijos: this.registrarSolicitudForm.get("generoHijos").value,
      edadMinimaHijos: this.registrarSolicitudForm.get("edadMinimaHijos").value,
      edadMaximaHijos: this.registrarSolicitudForm.get("edadMaximaHijos").value,
      conCuantasPersonasVive: this.registrarSolicitudForm.get("conCuantasPersonasVive").value,
      disponibilidadEnLinea: this.registrarSolicitudForm.get("disponibilidadEnLinea").value,
      fk_nivelEconomico: this.registrarSolicitudForm.get("fk_nivelEconomico").value,
      fk_producto: this.registrarSolicitudForm.get("fk_producto").value,
      fk_ocupacion: this.registrarSolicitudForm.get("fk_ocupacion").value,
      fk_usuario: 1
    }

    this._solicitudEstudioService.registrarSolicitud(NewS).subscribe(
      response => {
        console.log(response);
        //location.reload();
      }
    )


  }

}
