import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, Validators } from '@angular/forms';
import { from, identity } from 'rxjs';
import { Ocupacion } from 'src/app/interfaces/ocupacion';
import { Solicitud_Estudio } from '../../../../../interfaces/solicitud_estudio';
import { SolicitudestudioService } from '../../../../../services/solicitudestudio.service';
import { DatePipe } from '@angular/common';
import { User } from 'src/app/interfaces/user';
import { LoginService } from 'src/app/services/login.service';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';
import { NivelEconomicoServicioService } from 'src/app/services/nivel-economico-servicio.service';
import { OcupacionServicioService } from 'src/app/services/ocupacion-servicio.service';
import { ProductoService } from 'src/app/services/producto.service';
import { LugarServicioService } from 'src/app/services/lugar-servicio.service';
import { RegionEstudioService } from 'src/app/services/regionestudio.service';

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
  regiones: any;
  isWait = false;

  public user: User;
  public identity;
  public opcion = false;
  public fechaActual: any;

  constructor(
    private fb: FormBuilder,
    private _solicitudEstudioService: SolicitudestudioService,
    private _nivelEconomicoService: NivelEconomicoServicioService,
    private _ocupacionService: OcupacionServicioService,
    private _productoService: ProductoService,
    private _lugarService: LugarServicioService,
    private _regionEstudioService: RegionEstudioService,
    public datepipe: DatePipe,
    public _loginService: LoginService,
    public _router: Router
  ) {

    this.identity = JSON.parse(_loginService.getIdentity());


    this.user = new User(
      this.identity.id,
      this.identity.nombreUsuario,
      this.identity.correo,
      this.identity.estado,
      this.identity.idRol
    )
   }

  ngOnInit(): void {
    this.buscarNivelEconomico();
    this.buscarOcupacion();
    this.buscarProductos(this.identity.id); //Recuerda pasar el id del user
    this.buildForm();
    this.buscarRegiones();
    this.fechaActual = new Date();
  }

  /* Estos dos metodos funcionan para mostrar los datos opcionales*/
  activaOpcion(){
    this.opcion=true;
  }

  desactivaOpcion(){
    this.opcion =false;
  }


/*Construcción del formulario para guardar la solicitud*/

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
    conCuantasPersonasVive: [null,
    ],
    disponibilidadEnLinea: ["",
    Validators.compose([
      Validators.required])
    ],
    nivelEconomicoDto: ["",
    Validators.compose([
      Validators.required])
    ],
    productoDto: ["",
    Validators.compose([
      Validators.required])
    ],
    ocupacionDto: [null,
    ],
    regionAsignada: this.fb.array([this.añadeRegionEstudio()])
   });
 }


 añadeRegionEstudio(){
  return this.fb.group({
    id: 0,
    lugarDto:['', Validators.compose([
      Validators.required])
    ],
    solicitudEstudioDto:0
  })
 }

//Botones que controlan region de estudios
addNextRegion() {
  (this.registrarSolicitudForm.controls['regionAsignada'] as FormArray).push(this.añadeRegionEstudio());
}

deleteRegion(index: number) {
  (this.registrarSolicitudForm.controls['regionAsignada'] as FormArray).removeAt(index);
}

 // Obtener todos los niveles economicos de la base de datos
buscarNivelEconomico(){
  this._nivelEconomicoService.onCargarNivelE().subscribe(
    response => {
      this.nivelEconomico = response;
      console.log(this.nivelEconomico);
    }
  );
}

//Obtener todas las ocupaciones existentes en la bd
buscarOcupacion(){
  this._ocupacionService.onCargarOcupacion().subscribe(
    response => {
      this.ocupacion = response;
      console.log(this.ocupacion);
    }
  )
}


//Obtener todos los productos de ese cliente de la BD
buscarProductos(idUsuario: number){

  this._productoService.getProductosCliente(idUsuario).subscribe(
    response => {
      this.productos = response;
      console.log(this.productos);
    }
  )
}

//Obtener las regiones de estudio
buscarRegiones(){
  this._lugarService.obtenerEstados().subscribe(
    response => {
      this.regiones = response;
      console.log(this.regiones);
    }
  )
}


//Método que guarda los datos de la solicitud

  guardar(){

    const NewS: Solicitud_Estudio = {
      id: 0,
      descripcionSolicitud: this.registrarSolicitudForm.get("descripcionSolicitud").value,
      generoPoblacional: this.registrarSolicitudForm.get("generoPoblacional").value,
      fechaPeticion: new Date(),
      edadMinimaPoblacion: this.registrarSolicitudForm.get("edadMinimaPoblacion").value,
      edadMaximaPoblacion: this.registrarSolicitudForm.get("edadMaximaPoblacion").value,
      estatus: "Solicitado",
      estado: "A",
      conCuantasPersonasVive: this.registrarSolicitudForm.get("conCuantasPersonasVive").value,
      disponibilidadEnLinea: this.registrarSolicitudForm.get("disponibilidadEnLinea").value,
      nivelEconomicoDto: this.registrarSolicitudForm.get("nivelEconomicoDto").value,
      productoDto: this.registrarSolicitudForm.get("productoDto").value,
      ocupacionDto: this.registrarSolicitudForm.get("ocupacionDto").value,
      usuarioDto: this.user.id
    }

    const regionEstudio = this.registrarSolicitudForm.get("regionAsignada").value;
    console.log(NewS);
    console.log(regionEstudio);

    this._solicitudEstudioService.registrarSolicitud(NewS).subscribe(
      response => {
        console.log(response);
        this._regionEstudioService.registrarRegionEstudio(response.id,regionEstudio).subscribe(
          response => {
            console.log(response);
          }
        )
        this._router.navigate(['cliente']);
      }
    )


  }

}
