import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AnyARecord } from 'dns';
import { Subcategoria } from 'src/app/interfaces/subcategoria';
import { NivelEconomicoServicioService } from 'src/app/services/nivel-economico-servicio.service';
import { OcupacionServicioService } from 'src/app/services/ocupacion-servicio.service';
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

  constructor(
    public dialogRef: MatDialogRef<ConsultarEstudioAnalistaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private solicitudService: SolicitudestudioService,
    private nivelService: NivelEconomicoServicioService,
    private ocupacionService: OcupacionServicioService

  ) { }

  
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
    // this.solicitudService.actualizarSolicitud().subscribe();
  }

  // Form

  buildForm(): void {
    this.form = this.fb.group({
      generoPoblacional: [this.data.generoPoblacional,
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
    disponibilidadEnLinea: [this.data.disponibilidadEnLinea,
      Validators.compose([
        Validators.required, 
      ]),],
      nivelEconomico: [this.data.nivelEconomico._id,
      Validators.compose([
        Validators.required, 
      ]),],
      ocupacion: [this.data.ocupacion._id,
      Validators.compose([
        Validators.required, 
      ]),],
   });
 }


}
