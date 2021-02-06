import { Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AnyARecord } from 'dns';
import { Solicitud_Estudio } from 'src/app/interfaces/solicitud_estudio';
import { Subcategoria } from 'src/app/interfaces/subcategoria';
import { User } from 'src/app/interfaces/user';
import { LoginService } from 'src/app/services/login.service';
import { LugarServicioService } from 'src/app/services/lugar-servicio.service';
import { NivelEconomicoServicioService } from 'src/app/services/nivel-economico-servicio.service';
import { OcupacionServicioService } from 'src/app/services/ocupacion-servicio.service';
import { RegionEstudioService } from 'src/app/services/regionestudio.service';
import { SolicitudestudioService } from 'src/app/services/solicitudestudio.service';
import { ConsultarEstudioAnalistaComponent } from '../analista_estudio_asignado/consultar-estudio-analista/consultar-estudio-analista.component';

@Component({
  selector: 'app-dialogo-gestionar-poblacion',
  templateUrl: './dialogo-gestionar-poblacion.component.html',
  styleUrls: ['./dialogo-gestionar-poblacion.component.css']
})
export class DialogoGestionarPoblacionComponent implements OnInit {

  solicitud : any;
  nivel: any[] =[];
  ocupacion: any[] = [];
  form: any;
  isWait = false;

  public user: User;
  public identity;
  public regiones: any;
  public region: any;

  constructor(
    public dialogRef: MatDialogRef<DialogoGestionarPoblacionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private solicitudService: SolicitudestudioService,
    private nivelService: NivelEconomicoServicioService,
    private ocupacionService: OcupacionServicioService,
    public _loginService: LoginService,
    private _lugarService: LugarServicioService,
    private _regionEstudioService: RegionEstudioService,

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
    console.log(this.data)
    // console.log('he', this.data.generoPoblacional)
    // console.log(this.data.disponibilidadEnLinea)
    this.get();
    this.getNivel();
    this.getOcupacion();
    this.buscarRegiones();
    this.getRegionesSolicitud(this.data.data._solicitudEstudio._id);
    this.buildForm();
  }


  // Funcion para traerme el estudio
  get(){
    const id = this.data.data._solicitudEstudio._id;
    console.log(id)
    this.solicitudService.getSolicitud(id).subscribe(data => {this.solicitud = data;});
  }

  getNivel(){

    this.nivelService.onCargarNivelE().subscribe(data => {
      this.nivel = data;

    });
  }

  getOcupacion(){

    this.ocupacionService.onCargarOcupacion().subscribe(data => {
      this.ocupacion = data;
    });
  }

  buscarRegiones(){
    this._lugarService.obtenerMunicipios().subscribe(
      response => {
        this.region = response;
        console.log(this.region);
      }
    )
  }

  getRegionesSolicitud(idSolicitud: number){
    this._regionEstudioService.buscaRegionesSolicitud(idSolicitud).subscribe(
      response => {
        this.regiones = response;
        console.log(this.regiones);
        for(let region of this.regiones){
          
          (this.form.controls['regionAsignada'] as FormArray).push(
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

  // Update

  updateSolicitud() {

  this.isWait = true;
    
  const NewS: Solicitud_Estudio = {
    id: this.data.data._solicitudEstudio._id,
    descripcionSolicitud: this.data.data._solicitudEstudio._descripcionSolicitud,
    generoPoblacional: this.form.get("generoPoblacional").value,
    fechaPeticion: this.data.data._solicitudEstudio._fechaPeticion,
    edadMinimaPoblacion: this.form.get("edadMinimaPoblacion").value,
    edadMaximaPoblacion: this.form.get("edadMaximaPoblacion").value,
    estatus: this.data.data._solicitudEstudio._estatus,
    estado: this.data.data._solicitudEstudio._estado,
    conCuantasPersonasVive: this.form.get("conCuantasPersonasVive").value,
    disponibilidadEnLinea: this.form.get("disponibilidadEnLinea").value,
    nivelEconomicoDto: this.form.get("nivelEconomicoDto").value,
    productoDto:  this.data.data._solicitudEstudio._producto._id,
    ocupacionDto: this.form.get("ocupacionDto").value,
    usuarioDto: this.data.data._solicitudEstudio._usuario._id
  }

   console.log('Nueva Solicitud', NewS);

   const regionesActualizadas = this.form.get("regionAsignada").value
   console.log(regionesActualizadas);

    this.solicitudService.actualizarSolicitud(NewS).subscribe( 
    response => {
      this.isWait = false;
      console.log(response);
      
      this._regionEstudioService.actualizarRegionesSolicitud(response.id,regionesActualizadas).subscribe(
        response => {
          console.log(response);
        }, error => {
          console.log(<any>error);
        })

      this.dialogRef.close();

    }
    );


  }

  // Form

  buildForm(): void {
    this.form = this.fb.group({
      generoPoblacional: ["",
        Validators.compose([
          Validators.required, 
        ]),],
      edadMinimaPoblacion: ['',
        Validators.compose([
          Validators.required, 
        ]),],
      edadMaximaPoblacion: ['',
        Validators.compose([
          Validators.required, 
        ]),],
      conCuantasPersonasVive: ['',
        Validators.compose([
          Validators.required, 
        ]),],
      disponibilidadEnLinea: ["",
        Validators.compose([
          Validators.required, 
        ]),],
      nivelEconomicoDto: ["",
      Validators.compose([
        Validators.required, 
      ]),],
      ocupacionDto: ["",
      Validators.compose([
        Validators.required, 
      ]),],
      regionAsignada: this.fb.array([])

    });
 }


  // Region

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
    (this.form.controls['regionAsignada'] as FormArray).push(this.añadeRegionEstudio());
  }
  
  deleteRegion(index: number) {
    (this.form.controls['regionAsignada'] as FormArray).removeAt(index);
  }

}
