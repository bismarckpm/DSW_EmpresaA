import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Alert } from 'src/app/interfaces/alert.model';
import { Solicitud_Estudio } from 'src/app/interfaces/solicitud_estudio';
import { User } from 'src/app/interfaces/user';
import { AlertService } from 'src/app/services/alert.service';
import { LoginService } from 'src/app/services/login.service';
import { LugarServicioService } from 'src/app/services/lugar-servicio.service';
import { NivelEconomicoServicioService } from 'src/app/services/nivel-economico-servicio.service';
import { OcupacionServicioService } from 'src/app/services/ocupacion-servicio.service';
import { ProductoService } from 'src/app/services/producto.service';
import { RegionEstudioService } from 'src/app/services/regionestudio.service';
import { SolicitudestudioService } from 'src/app/services/solicitudestudio.service';

@Component({
  selector: 'app-editasolicitud',
  templateUrl: './editasolicitud.component.html',
  styleUrls: ['./editasolicitud.component.css']
})
export class EditasolicitudComponent implements OnInit {


  editarSolicitudForm: any;

  nivel: string =  'Probando';

  nivelEconomico: any; 
  ocupacion: any;
  productos: any;
  isWait = false;

  public user: User;
  public identity;

  public idSolicitud: any;
  Solicitud: any; 
  OcupaAux: any; 
  public regiones: any;
  public region: any;


  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };

  constructor(
    private fb: FormBuilder,
    private _solicitudEstudioService: SolicitudestudioService,
    private _nivelEconomicoService: NivelEconomicoServicioService,
    private _ocupacionService: OcupacionServicioService,
    private _productoService: ProductoService,
    private _regionEstudioService: RegionEstudioService,
    public _loginService: LoginService,
    private _route: ActivatedRoute,
    private _lugarService: LugarServicioService,
    public _router: Router,
    private _alertService: AlertService
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

    this._route.queryParams.subscribe(
      response => {
        console.log(response);
        this.idSolicitud = response;
        console.log(this.idSolicitud);
        this.buscaSolicitud(this.idSolicitud.idSolicitud);
        this.buscarRegionesSolicitud(this.idSolicitud.idSolicitud);
      }
    );
    this.buscarNivelEconomico();
    this.buscarOcupacion();
    this.buscarProductos(this.identity.id); //Recuerda pasar el id del user
    this.buscarRegiones();
    
    console.log(this.Solicitud);
    console.log(this.nivelEconomico);
    this.buildForm();
  }

  buscaSolicitud(idSolicitud: number){

    this._solicitudEstudioService.getSolicitud(idSolicitud).subscribe(
      response => {
        this.Solicitud = response.objeto;
        if ( this.Solicitud._ocupacion ) { 
          this.OcupaAux = this.Solicitud._ocupacion._id;
          console.log(this.OcupaAux);
        } else {
         this.OcupaAux = '';
        }
        console.log(this.Solicitud); 
        console.log(this.OcupaAux);
      }
    );
  }

  buscarRegiones(){
    this._lugarService.obtenerMunicipios().subscribe(
      response => {
        this.region = response.objeto;
        console.log('cargo regiones', this.region);
      }
    )
  }

  buildForm(): void {
    this.editarSolicitudForm = this.fb.group({
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
    nivelEconomicoDto: ['',
    Validators.compose([
      Validators.required])
    ],
    productoDto: ["",
    Validators.compose([
      Validators.required])
    ],
    ocupacionDto: [null,
    ],
    regionAsignada: this.fb.array([])
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

 addNextRegion() {
  (this.editarSolicitudForm.controls['regionAsignada'] as FormArray).push(this.añadeRegionEstudio());
  this._alertService.success("Region añadida correctamente",this.options);
}

deleteRegion(index: number) {
  (this.editarSolicitudForm.controls['regionAsignada'] as FormArray).removeAt(index);
  this._alertService.success("Region eliminada correctamente", this.options);
}

buscarRegionesSolicitud(idSolicitud: number){
  this._regionEstudioService.buscaRegionesSolicitud(idSolicitud).subscribe(
    response => {
      this.regiones = response.objeto;
      console.log('regiones actuales', this.regiones);

      for(let region of this.regiones){
        
        (this.editarSolicitudForm.controls['regionAsignada'] as FormArray).push(
          this.fb.group({
            id: 0,
            lugarDto:[region._id, Validators.compose([
              Validators.required])
            ],
            solicitudEstudioDto:0
          })
        );
        
      }
    }
  )
}

 //Obtener el nivel economico
buscarNivelEconomico(){
  this._nivelEconomicoService.onCargarNivelE().subscribe(
    response => {
      this.nivelEconomico = response.objeto;
      console.log(this.nivelEconomico);
    }
  );
}

buscarOcupacion(){
  this._ocupacionService.onCargarOcupacion().subscribe(
    response => {
      this.ocupacion = response.objeto;
      console.log(this.ocupacion);
    }
  )
}

buscarProductos(idUsuario: number){

  this._productoService.getProductosCliente(idUsuario).subscribe(
    response => {
      this.productos = response.objeto;
    }
  )
}



guardar(Solicitud: any){

  console.log('Solicitud' ,Solicitud)

  let NewS: Solicitud_Estudio;

  if(this.editarSolicitudForm.get("ocupacionDto").value == null || this.editarSolicitudForm.get("ocupacionDto").value.length <= 0){ 
    NewS = {
      id: this.idSolicitud.idSolicitud,
      descripcionSolicitud: this.editarSolicitudForm.get("descripcionSolicitud").value,
      generoPoblacional: this.editarSolicitudForm.get("generoPoblacional").value,
      fechaPeticion: new Date(),
      edadMinimaPoblacion: this.editarSolicitudForm.get("edadMinimaPoblacion").value,
      edadMaximaPoblacion: this.editarSolicitudForm.get("edadMaximaPoblacion").value,
      estatus: 'Solicitado',
      estado: "A",
      conCuantasPersonasVive: Solicitud._conCuantasPersonasVive,
      disponibilidadEnLinea: this.editarSolicitudForm.get("disponibilidadEnLinea").value,
      nivelEconomicoDto: this.editarSolicitudForm.get("nivelEconomicoDto").value,
      productoDto: this.editarSolicitudForm.get("productoDto").value,
      ocupacionDto: Solicitud._conCuantasPersonasVive,
      usuarioDto: this.user.id
    }
  } else {
    NewS = {
      id: this.idSolicitud.idSolicitud,
      descripcionSolicitud: this.editarSolicitudForm.get("descripcionSolicitud").value,
      generoPoblacional: this.editarSolicitudForm.get("generoPoblacional").value,
      fechaPeticion: new Date(),
      edadMinimaPoblacion: this.editarSolicitudForm.get("edadMinimaPoblacion").value,
      edadMaximaPoblacion: this.editarSolicitudForm.get("edadMaximaPoblacion").value,
      estatus: 'Solicitado',
      estado: "A",
      conCuantasPersonasVive: this.editarSolicitudForm.get("conCuantasPersonasVive").value,
      disponibilidadEnLinea: this.editarSolicitudForm.get("disponibilidadEnLinea").value,
      nivelEconomicoDto: this.editarSolicitudForm.get("nivelEconomicoDto").value,
      productoDto: this.editarSolicitudForm.get("productoDto").value,
      ocupacionDto: this.editarSolicitudForm.get("ocupacionDto").value,
      usuarioDto: this.user.id
    }
  }


  const regionesActualizadas = this.editarSolicitudForm.get("regionAsignada").value
  console.log(regionesActualizadas);
  console.log(NewS);

  this._solicitudEstudioService.actualizarSolicitud(NewS).subscribe(
    response => {
      console.log(response);
      this._regionEstudioService.actualizarRegionesSolicitud(response.objeto._id,regionesActualizadas).subscribe(
        response => {
          console.log(response);
          this._alertService.success(response.mensaje + ' ' + response.estado);
        }, error => {
          console.log(<any>error);
          this._alertService.error(response.mensaje + '    ' + response.estado);
        }
      );
      this._alertService.success(response.mensaje + '  ' + response.estado);
      this._router.navigate(['cliente']);
    }, error => {
      this._alertService.error(error.mensaje);

    }
  )

}

}