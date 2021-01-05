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

  constructor(
    public dialogRef: MatDialogRef<ConsultarEstudioAnalistaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private solicitudService: SolicitudestudioService,
    private nivelService: NivelEconomicoServicioService,
    private ocupacionService: OcupacionServicioService,
    public _loginService: LoginService,

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
    console.log('he', this.data.generoPoblacional)
    console.log(this.data.disponibilidadEnLinea)
    const id = this.data.id;
    this.get();
    this.getNivel();
    this.getOcupacion();
    this.buildForm();
  }


  // Funcion para traerme el estudio
  get(){
    const id = this.data.id;
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



  // Update

  updateSolicitud() {

  this.isWait = true;
    
  const NewS: Solicitud_Estudio = {
    id: this.data.id,
    descripcionSolicitud: this.data.descripcion,
    generoPoblacional: this.form.get("generoPoblacional").value,
    fechaPeticion: new Date(),
    edadMinimaPoblacion: this.form.get("edadMinimaPoblacion").value,
    edadMaximaPoblacion: this.form.get("edadMaximaPoblacion").value,
    estatus: 'Solicitado',
    estado: "Activo",
    conCuantasPersonasVive: this.form.get("conCuantasPersonasVive").value,
    disponibilidadEnLinea: this.form.get("disponibilidadEnLinea").value,
    nivelEconomicoDto: this.form.get("nivelEconomicoDto").value,
    productoDto:  this.data.producto,
    ocupacionDto: this.form.get("ocupacionDto").value,
    usuarioDto: this.data.usuario
  }

   console.log('aqui', NewS);

    this.solicitudService.actualizarSolicitud(NewS).subscribe( 
    response => {
      this.isWait = false;
      console.log(response);
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
   });
 }


}
