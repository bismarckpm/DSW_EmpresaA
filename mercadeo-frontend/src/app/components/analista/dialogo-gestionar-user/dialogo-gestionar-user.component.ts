import { Component, Inject, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormBuilder, Validators } from '@angular/forms';
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
    public dialogRef: MatDialogRef<DialogConsultaSolicitudComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    // Forms
    private fb: FormBuilder,
    // Services
    private _datoService: EncuestadoServicioService,
    private hijo: HijoServicioService,
    private telefono: TelefonoServicioService,
    private lugarService: LugarServicioService,
    private nivelA: NivelAcademicoServicioService,
    private ocupacionService: OcupacionServicioService,
    private nivelEco: NivelEconomicoServicioService,

  ) { }

  ngOnInit(): void {
    console.log(this.data.idUser)
    this.getUser();
    this.getDatos();
    this.buildForm();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }


  // Get User Data
  getUser() {
    this._datoService.getDatoUsuarioPorIdUsuario(this.data.idUser).subscribe(
      (response) => {
        this.usuario = response;
        console.log(this.usuario);
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
      Validators.compose([
        Validators.required]),
      ],
      medioComunicacion: ["",
      Validators.compose([
        Validators.required]),
      ],   
      nivelEconomico: ["",
      Validators.compose([
        Validators.required]),
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
      Validators.compose([
        Validators.required]),
      ],  
      disponibilidadEnLinea: ["",
      Validators.compose([
        Validators.required]),
      ],  

    });
 }

 
 save(usuario: any) {
  let newUser: Dato_Usuario = {
    cedula: usuario._cedula,
    primerNombre: this.userForm.get("primerNombre").value,
    segundoNombre: this.userForm.get("segundoNombre").value,
    primerApellido: this.userForm.get("primerApellido").value,
    segundoApellido: this.userForm.get("segundoApellido").value,
    sexo: usuario._sexo,
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


  console.log(newUser);

  if(confirm("Estas seguro de actualizar los datos?")) {
      this.isWait = true;
      this._datoService.setDatoUsuario(usuario._id ,newUser).subscribe((response) => {
         this.openSnackBar("Usuario Actualizado");
         this.isWait = false;
         this.onNoClick();
      })
  }
 }





  //  GET DATOS

  lugares: any[] = [];
  ocupaciones: any[] = [];
  nivelesEconomicos: any[] = [];
  nivelesAcademicos: any[] = [];


  getDatos() {
    this.lugarService.onCargarLugar().subscribe(
      (lugar) => {
        this.lugares = lugar;
        // console.log(this.lugares);
      }
  );

    this.nivelA.onCargarNivel().subscribe(
    (nivel) => {
      this.nivelesAcademicos = nivel;
    }
  );

    this.ocupacionService.onCargarOcupacion().subscribe(
    (ocupacion) => {
      this.ocupaciones = ocupacion;
      // console.log(this.ocupaciones)
    }
  );

    this.nivelEco.onCargarNivelE().subscribe(
    (nivelE) => {
      this.nivelesEconomicos = nivelE;
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