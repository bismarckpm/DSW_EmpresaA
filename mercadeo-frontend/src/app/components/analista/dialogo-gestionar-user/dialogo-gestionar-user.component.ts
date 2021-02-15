import { Component, Inject, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormArray, FormBuilder, Validators } from '@angular/forms';
// Services
import { RegionEstudioService } from 'src/app/services/regionestudio.service';
import { DialogConsultaSolicitudComponent } from '../../cliente/dialog-consulta-solicitud/dialog-consulta-solicitud.component';
import { EncuestadoServicioService } from 'src/app/services/encuestado-servicio.service';
import { HijoServicioService } from 'src/app/services/hijo-servicio.service';
import { TelefonoServicioService } from 'src/app/services/telefono-servicio.service';
import { LugarServicioService } from 'src/app/services/lugar-servicio.service';
import { NivelAcademicoServicioService } from 'src/app/services/nivel-academico-servicio.service';
import { NivelEconomicoServicioService } from 'src/app/services/nivel-economico-servicio.service';
import { OcupacionServicioService } from 'src/app/services/ocupacion-servicio.service';
import { Dato_Usuario } from 'src/app/interfaces/dato_usuario';
import { MatSnackBar } from '@angular/material/snack-bar';
import { GetTelefono } from 'src/app/interfaces/telefono';

@Component({
  selector: 'app-dialogo-gestionar-user',
  templateUrl: './dialogo-gestionar-user.component.html',
  styleUrls: ['./dialogo-gestionar-user.component.css']
})
export class DialogoGestionarUserComponent implements OnInit {

  usuario: any;
  isWait = false;
  estCiviles: string[] = ['Soltero/a', 'Viudo/a', 'Casado/a', 'Divorciado/a'];
  mediosC: string[] = ['Teléfono', 'Tableta', 'PC', 'Laptop'];
  disps: string[] = ['Si', 'No'];

  constructor(
    private _snackBar: MatSnackBar,
    // Dialogs
    public dialogRef: MatDialogRef<DialogoGestionarUserComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    // Forms
    private fb: FormBuilder,
    // Services
    private _datoService: EncuestadoServicioService,
    private hijo: HijoServicioService,
    private telefonoService: TelefonoServicioService,
    private lugarService: LugarServicioService,
    private nivelA: NivelAcademicoServicioService,
    private ocupacionService: OcupacionServicioService,
    private nivelEco: NivelEconomicoServicioService,
    private _tlfnService: TelefonoServicioService,

  ) { }

  ngOnInit(): void {
    console.log(this.data.idUser._id)
    this.getUser();
    this.getDatos();
    this.buildForm();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }


  // Get User Data
  getUser() {
    this._datoService.getDatoUsuarioPorIdUsuario(this.data.idUser._id).subscribe(
      (response) => {
        this.usuario = response.objeto;
        console.log(this.usuario);
        this.getUbicacion(this.usuario._lugar._lugar._id);

      },

      (error) => {
        console.log(error);
      }
    );
  }

  // Forms
  userForm: any;
  buildForm(): void {
    this.userForm = this.fb.group({

      primerNombre: ["",
      Validators.compose([
        Validators.required,]),
      ],
      segundoNombre: ["",
      Validators.compose([
        Validators.required]),
      ],
      primerApellido: ["",
      Validators.compose([
        Validators.required]),
      ],
      segundoApellido: ["",
      Validators.compose([
        Validators.required]),
      ],
      estadoCivil: ["",
      Validators.compose([
        Validators.required]),
      ],
      conCuantasPersonasVive: ["",

      ],
      medioComunicacion: ["",
      Validators.compose([
        Validators.required]),
      ],   
      nivelEconomico: ["",
      Validators.compose([
        Validators.required]),
      ],  
      estado: ["",
      ],  
      lugar: ["",
      Validators.compose([
        Validators.required]),
      ],  
      nivelAcademico: ["",
      Validators.compose([
        Validators.required]),
      ],  
      ocupacion: ["",

      ],  
      disponibilidadEnLinea: ["",
      Validators.compose([
        Validators.required]),
      ],  
      telefono: this.fb.array([this.fb.group({
        phones: ['']
      })])
    });
 }

 
// TELEFONO
añadeTelefono() {
  return this.fb.group({
    phones: []
  });
}

addNextTelefono() {
  (this.userForm.controls['telefono'] as FormArray).push(this.añadeTelefono());
}

removerTelefono(id: number) {
  (this.userForm.controls['telefono'] as FormArray).removeAt(id);
}


// SAVE
 save(usuario: any) {
  let newUser: Dato_Usuario = {
    cedula: usuario._cedula,
    primerNombre: this.userForm.get("primerNombre").value,
    segundoNombre: this.userForm.get("segundoNombre").value,
    primerApellido: this.userForm.get("primerApellido").value,
    segundoApellido: this.userForm.get("segundoApellido").value,
    sexo: usuario._sexo,
    estado: usuario._estado,
    fechaNacimiento: usuario._fechaNacimiento,
    estadoCivil: this.userForm.get("estadoCivil").value,
    disponibilidadEnLinea: usuario._disponibilidadEnLinea,
    conCuantasPersonasVive: this.userForm.get("conCuantasPersonasVive").value,
    medioComunicacion: this.userForm.get("medioComunicacion").value,
    lugarDto: this.userForm.get("lugar").value,
    nivelAcademicoDto: this.userForm.get("nivelAcademico").value,
    ocupacionDto: this.userForm.get("ocupacion").value,
    nivelEconomicoDto: this.userForm.get("nivelEconomico").value,
  };

  // let newTelefono: any = {
  //   id:  this.telefono._id,
  //   estado:  this.telefono._estado,
  //   numero: this.userForm.get("telefono").value,
  //   datoUsuarioDto: this.telefono._datoUsuario._id
  // }

  // // console.log(newUser);
  // console.log('telefono ', newTelefono);


  if(confirm("Estas seguro de actualizar los datos?")) {
      this.isWait = true;
      this._datoService.setDatoUsuario(usuario._id ,newUser).subscribe((response) => {
         this.openSnackBar("Usuario Actualizado " + response.estado );
         this.isWait = false;
         this.onNoClick();
      })
  }
  
 }

 saveTlfno(telefono: any) {
   this.telefonoService.setTelefonos(telefono);
 }



  //  GET DATOS

  lugares: any[] = [];
  municipios: any[] = [];
  ocupaciones: any[] = [];
  nivelesEconomicos: any[] = [];
  nivelesAcademicos: any[] = [];
  telefono: any;

  selected: any;

  getUbicacion(idestado: number) {
    this.lugarService.obtenerMunicipios().subscribe(
      (lugar) => {
        this.municipios = lugar.objeto;
        this.municipios = this.municipios.filter(item => item._lugar._id == idestado);
        console.log(this.municipios);
      },
      (error) => {
        console.log(error);
      }
  );
}

  getDatos() {
    this.lugarService.onCargarLugar().subscribe(
      (lugar) => {
        this.lugares = lugar.objeto;
        this.lugares = this.lugares.filter(item=> item._tipo == 'Estado');

        // console.log(this.lugares);
      }
  );


  this._tlfnService.getTelefonos(this.data.idUser._datoUsuario._id).subscribe((data) => {
    this.telefono = data.objeto;
    console.log('telefono', this.telefono)
  });

  // this._tlfnService.getTelefonos(this.data.idUser._datoUsuario._id).subscribe(
  //   (telefons: GetTelefono[]) => {
  //     console.log(telefons);
  //     if(telefons.length > 0){

  //       for(let j = 0; j < telefons.length; j ++){
  //       this.telefono.push(telefons[j]);
  //       if (j < telefons.length - 1){
  //         (this.userForm.controls['telefono'] as FormArray).push(this.añadeTelefono());
  //       }
  //     }
  //   }
  //   }
  // );

    this.nivelA.onCargarNivel().subscribe(
    (nivel) => {
      this.nivelesAcademicos = nivel.objeto;
    }
  );

    this.ocupacionService.onCargarOcupacion().subscribe(
    (ocupacion) => {
      this.ocupaciones = ocupacion.objeto;
      // console.log(this.ocupaciones)
    }
  );

    this.nivelEco.onCargarNivelE().subscribe(
    (nivelE) => {
      this.nivelesEconomicos = nivelE.objeto;
    }
  );
}


// Snackbar

openSnackBar(msg: string) {
  this._snackBar.open(msg, 'Ok', {
    duration: 2000,
  });
}

}