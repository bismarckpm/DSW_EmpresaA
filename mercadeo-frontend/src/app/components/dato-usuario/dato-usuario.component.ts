import { Medio_Comunicacion } from './../../interfaces/medio_comunicacion';
import { MedioComunicacionService } from './../../services/medio-comunicacion.service';
import { FormArray, FormBuilder, Validators } from '@angular/forms';
import { UsuarioServicioService } from './../../services/usuario-servicio.service';
import { NivelEconomicoServicioService } from './../../services/nivel-economico-servicio.service';
import { OcupacionServicioService } from './../../services/ocupacion-servicio.service';
import { NivelAcademicoServicioService } from './../../services/nivel-academico-servicio.service';
import { LugarServicioService } from './../../services/lugar-servicio.service';

import { Usuario } from 'src/app/interfaces/usuario';


import { GetNivel_Economico, Nivel_Economico } from '../../interfaces/nivel_economico';
import { GetOcupacion, Ocupacion } from '../../interfaces/ocupacion';
import { GetNivel_Academico, Nivel_Academico } from '../../interfaces/nivel_academico';


import { GetLugar, Lugar } from '../../interfaces/lugar';
import { EncuestadoServicioService } from '../../services/encuestado-servicio.service';
import { Dato_Usuario, GetDato_Usuario } from '../../interfaces/dato_usuario';
import { Component, Input, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { Rol } from 'src/app/interfaces/rol';
import { HttpClient } from '@angular/common/http';
import { delay } from 'rxjs/operators';
import { DatePipe } from '@angular/common';
import { HijoServicioService } from 'src/app/services/hijo-servicio.service';
import { TelefonoServicioService } from 'src/app/services/telefono-servicio.service';
import { Hijo } from 'src/app/interfaces/hijo';
import { Telefono } from 'src/app/interfaces/telefono';


@Component({
  selector: 'app-dato-usuario',
  templateUrl: './dato-usuario.component.html',
  styleUrls: ['./dato-usuario.component.css']
})
export class DatoUsuarioComponent implements OnInit {

  lugarfk = 0;
  nombreP = '';
  nombreS = '';
  codigo = 0;
  cedula = '';
  apellidoP = '';
  apellidoS = '';
  sexo = '';
  fechaNacimiento = new Date();

  edoCivil = '';
  disp = '';
  numP = '';
  users: Dato_Usuario[] = [];
  lugares: GetLugar[] = [];
  nivel: GetNivel_Academico[] = [];
  nivelfk = 0;
  ocup: GetOcupacion[] = [];
  ocupfk = 0;
  nivelesE: GetNivel_Economico[] = [];
  nivelEfk = 0;
  nombreU = '';
  correo = '';
  password = '';
  fkrol = 4;
  usuarioId = 0;
  estado = 'A';
  codigoR = '';
  hijosN = '';
  phoneN: string[] = [];
  estCiviles: string[] = ['Soltero/a', 'Viudo/a', 'Casado/a', 'Divorciado/a'];
  disps: string[] = ['En linea', 'Fuera de linea'];
  generos: string[] = ['M', 'F'];
  mediosC: string[] = ['Teléfono', 'Tableta', 'PC', 'Laptop'];
  firstFormGroup: any;
  secondFormGroup: any;
  fechaNacHj = [] as any;
  sexoH: string[] = [];
  medioC = '';
  constructor(private usuarioService: EncuestadoServicioService,
              private lugarService: LugarServicioService,
              private nivelA: NivelAcademicoServicioService,
              private ocupacion: OcupacionServicioService,
              private nivelEco: NivelEconomicoServicioService,
              private userS: UsuarioServicioService,
              private navegacion: Router,
              public datepipe: DatePipe,
              private fb:FormBuilder,
              private hijo: HijoServicioService,
              private telefono: TelefonoServicioService,
              private med: MedioComunicacionService
              ) { }

  ngOnInit(): void {
     this.lugarService.onCargarLugar().subscribe(
      (lugar: GetLugar[]) => {
        this.lugares = lugar;
        console.log(this.lugares);
      }
  );

     this.nivelA.onCargarNivel().subscribe(
    (nivel: GetNivel_Academico[]) => {
      this.nivel = nivel;
    }
  );

     this.ocupacion.onCargarOcupacion().subscribe(
    (ocupacion: GetOcupacion[]) => {
      this.ocup = ocupacion;
     }
  );

     this.nivelEco.onCargarNivelE().subscribe(
    (nivelE: GetNivel_Economico[]) => {
      this.nivelesE = nivelE;
     }
  );

    /*  this.usuarioService.traerEncuestados().subscribe(
    (usuario: Dato_Usuario[]) => {
      this.users.push(usuario.slice(-1)[0]);
      // tslint:disable-next-line: no-string-literal
      console.log(this.users[0].id);

     }
  ); */

     this.firstFormGroup = this.fb.group({
    fechaNacH: this.fb.array([this.fb.group({
      numeroHijos: ['', Validators.required],
      genero: ['', Validators.required]
    })])
  });

     this.secondFormGroup = this.fb.group({
    telefonos: this.fb.array([this.fb.group({
      phones: ['', Validators.required]
    })])
  });
}

añadeHijo(){
  return this.fb.group({
    numeroHijos: [],
    genero: []
  });
 }

añadeTelefono() {
  return this.fb.group({
    phones: []
  });
}

addNextHijo() {
  (this.firstFormGroup.controls['fechaNacH'] as FormArray).push(this.añadeHijo());
}

removerHijo(id: number) {
  (this.firstFormGroup.controls['fechaNacH'] as FormArray).removeAt(id);
}

addNextTelefono() {
  (this.secondFormGroup.controls['telefonos'] as FormArray).push(this.añadeTelefono());
}

removerTelefono(id: number) {
  (this.secondFormGroup.controls['telefonos'] as FormArray).removeAt(id);
}


 Delay(ms: number) {
  return new Promise( resolve => setTimeout(resolve, ms) );
}

 async insertarUsuario() {
  let foranea: number;
  console.log(this.fechaNacimiento);
  console.log(this.edoCivil);

  /* console.log(this.users.length); */
  /* console.log(this.users); */
  /* console.log(this.users[0].id); */

  /* console.log(this.users[0].id); */
  // tslint:disable-next-line: no-non-null-assertion
  /* this.users.slice(-1)[0]._id! + 1 */


  /* if (this.users.length === 1){
    console.log("entre en el indefinido");
    f = 1;
  }
  else{
    console.log("entre en el else");
    f = this.users[0].id! + 1;

    console.log(f);
  } */
  /*let lugar = new Lugar(this.lugarfk);
  let nivelA = new Nivel_Academico(this.nivelfk);
  let oP = new Ocupacion(this.ocupfk);
  let nE = new Nivel_Economico(this.nivelEfk);
  let rol = new Rol(this.fkrol); */

  let encuestado: Dato_Usuario = {
    cedula: this.cedula ,
    primerNombre: this.nombreP,
    segundoNombre: this.nombreS,
    primerApellido: this.apellidoP,
    segundoApellido: this.apellidoS,
    sexo: this.sexo,
    fechaNacimiento: this.fechaNacimiento,
    estadoCivil: this.edoCivil,
    disponibilidadEnLinea: this.disp,
    conCuantasPersonasVive: Number(this.numP),
    lugarDto: this.lugarfk,
    nivelAcademicoDto: this.nivelfk,
    ocupacionDto: this.ocupfk,
    nivelEconomicoDto: this.nivelEfk
  };

  this.usuarioService.onGuardarUsuario(encuestado).subscribe(
     usuario => {
        this.users.push(usuario);
        foranea = this.users[0].id!;
        /* console.log(this.users[0].id); */

      }
    );
  await this.Delay(5000);
  //console.log(f);
  console.log("HOLAAAA :D");
  console.log(foranea!);
   /* console.log(this.users[0].id); */


  if (this.sexoH.length !== 0){
    for (let i = 0; i < this.sexoH.length; i++) {
     /*  fechaNac = new Date(this.hijosF[i]); */
      let hijosT: Hijo = {
        fechaNacimiento: this.fechaNacHj[i],
        genero: this.sexoH[i],
        estado: 'A',
        datoUsuarioDto: foranea!};

      this.hijo.createHijo(hijosT);
      console.log(hijosT);
    }
}

  if (this.phoneN.length !== 0){
  for (let j = 0; j < this.phoneN.length; j++) {
    let TelefonosT: Telefono = {
      numero: this.phoneN[j],
      estado: 'A',
      datoUsuarioDto: foranea!};

    this.telefono.createTelefono(TelefonosT);
    console.log(TelefonosT);
  }
}

  let medio: Medio_Comunicacion = {
  nombre: this.medioC,
  datoUsuarioDto: foranea!};

  this.med.addMedio(medio);




  /* console.log(f); */
  /* let enc = new Dato_Usuario(f); */
  /* let usuario: Usuario = {
     nombreUsuario: this.nombreU,
     correo: this.correo,
     estado: this.estado,
     codigoRecuperacion: this.codigoR,
     password: this.password,
     rolDto: this.fkrol,
     datoUsuarioDto: f
    };

  this.userS.onGuardarUser(usuario); */

  this.nombreP = '';
  this.nombreS = '';
  this.cedula = '';
  this.apellidoP = '';
  this.apellidoS = '';
  this.sexo = '';

  this.edoCivil = '';
  this.disp = '';
  this.numP = '';
  this.lugarfk = 0;

  /* if  (Number(this.hijosN) > 0 || Number(this.phoneN) > 0){
    this.navegacion.navigate(['datosadicionales', Number(this.hijosN), Number(this.phoneN), foranea]);
  }
  if (Number(this.hijosN) === 0 && Number(this.phoneN) === 0){*/
  this.navegacion.navigate(['crearusuario', foranea!]);
  // }
}



}
