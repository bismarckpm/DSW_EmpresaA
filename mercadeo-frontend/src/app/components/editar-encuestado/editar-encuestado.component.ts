import { GetTelefono } from './../../interfaces/telefono';
import { GetDato_Usuario } from './../../interfaces/dato_usuario';
import { UsuarioServicioService } from './../../services/usuario-servicio.service';
import { GetNivel_Academico } from './../../interfaces/nivel_academico';
import { Component, OnInit } from '@angular/core';
import { Dato_Usuario } from 'src/app/interfaces/dato_usuario';
import { GetLugar } from 'src/app/interfaces/lugar';
import { GetOcupacion } from 'src/app/interfaces/ocupacion';
import { GetNivel_Economico } from 'src/app/interfaces/nivel_economico';
import { Telefono } from 'src/app/interfaces/telefono';
import { GetHijo, Hijo } from 'src/app/interfaces/hijo';
import { EncuestadoServicioService } from 'src/app/services/encuestado-servicio.service';
import { LugarServicioService } from 'src/app/services/lugar-servicio.service';
import { NivelAcademicoServicioService } from 'src/app/services/nivel-academico-servicio.service';
import { OcupacionServicioService } from 'src/app/services/ocupacion-servicio.service';
import { NivelEconomicoServicioService } from 'src/app/services/nivel-economico-servicio.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { FormArray, FormBuilder } from '@angular/forms';
import { HijoServicioService } from 'src/app/services/hijo-servicio.service';
import { TelefonoServicioService } from 'src/app/services/telefono-servicio.service';

@Component({
  selector: 'app-editar-encuestado',
  templateUrl: './editar-encuestado.component.html',
  styleUrls: ['./editar-encuestado.component.css']
})
export class EditarEncuestadoComponent implements OnInit {

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
  numP = 0;
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
  disps: string[] = ['Si', 'No'];
  generos: string[] = ['Masculino', 'Femenino'];
  mediosC: string[] = ['Teléfono', 'Tableta', 'PC', 'Laptop'];
  firstFormGroup: any;
  secondFormGroup: any;
  thirdFormGroup: any;
  fechaNacHj = [] as any;
  sexoH: string[] = [];

  medioC = '';
  hcheck = false;
  tcheck = false;
  mcheck = false;
  hijosF: Hijo[] = [];
  telefonosF: Telefono[] = [];
  idUsuario = 0;
  datoU: GetDato_Usuario[] = [];
  fkDatoUsuario = 0;
  idHijos: number[] = [];
  tphone: number[] = [];
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
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.idUsuario = this.route.snapshot.params['idUsuario'];
    this.fkDatoUsuario = this.route.snapshot.params['fkDatoUsuario'];
    console.log(this.idUsuario);

    this.usuarioService.getDatoUsuario(this.fkDatoUsuario).subscribe(
      (dU: GetDato_Usuario) => {
        console.log(dU);
        this.datoU.push(dU);
        console.log(this.datoU);
        this.nombreP = this.datoU[0]._primerNombre;
        console.log(this.nombreP);
        this.nombreS = this.datoU[0]._segundoNombre;
        this.cedula = this.datoU[0]._cedula;
        this.apellidoP = this.datoU[0]._primerApellido;
        this.apellidoS = this.datoU[0]._segundoApellido;
        this.numP = this.datoU[0]._conCuantasPersonasVive;
        this.sexo = this.datoU[0]._sexo;
        this.fechaNacimiento = this.datoU[0]._fechaNacimiento;
        this.edoCivil = this.datoU[0]._estadoCivil;
        this.disp = this.datoU[0]._disponibilidadEnLinea;
        this.medioC = this.datoU[0]._medioComunicacion;
        this.lugarfk = this.datoU[0]._lugar._id!;
        console.log(this.lugarfk);
        this.nivelfk = this.datoU[0]._nivelAcademico._id!;
        console.log(this.nivelfk);
        this.ocupfk = this.datoU[0]._ocupacion._id!;
        console.log(this.ocupfk);
        this.nivelEfk = this.datoU[0]._nivelEconomico._id!;
        console.log(this.nivelEfk);
      }
  );

    this.hijo.getHijos(this.fkDatoUsuario).subscribe(
      (hijos: GetHijo[]) => {
        for(let i = 0; i < hijos.length; i ++){
          this.idHijos.push(hijos[i]._id);
        }
      }
    );

    this.telefono.getTelefonos(this.fkDatoUsuario).subscribe(
      (telefons: GetTelefono[]) => {
        for(let j = 0; j < telefons.length; j ++){
          this.tphone.push(telefons[j]._id);
        }
      }
    );

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

    this.firstFormGroup = this.fb.group({
    fechaNacH: this.fb.array([this.fb.group({
      numeroHijos: [''],
      genero: ['']
    })])
  });

    this.secondFormGroup = this.fb.group({
    telefonos: this.fb.array([this.fb.group({
      phones: ['']
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

insertarUsuario() {

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
    medioComunicacion: this.medioC,
    lugarDto: this.lugarfk,
    nivelAcademicoDto: this.nivelfk,
    ocupacionDto: this.ocupfk,
    nivelEconomicoDto: this.nivelEfk
  };
  console.log(encuestado);

  this.usuarioService.setDatoUsuario(this.fkDatoUsuario, encuestado);
  //console.log(f);
  console.log("HOLAAAA :D");
  console.log(this.idUsuario);
   /* console.log(this.users[0].id); */


  if (this.hcheck === true){
    for (let i = 0; i < this.sexoH.length; i++) {
     /*  fechaNac = new Date(this.hijosF[i]); */
      let hijosT: Hijo = {
        id: this.idHijos[i],
        fechaNacimiento: this.fechaNacHj[i],
        genero: this.sexoH[i],
        estado: 'A',
        datoUsuarioDto: Number(this.fkDatoUsuario)};


      console.log(hijosT);
      this.hijosF.push(hijosT);
    }
    this.hijo.setHijos(this.hijosF);
}

  if (this.tcheck === true){
  for (let j = 0; j < this.phoneN.length; j++) {
    let TelefonosT: Telefono = {
      id: this.tphone[j],
      numero: this.phoneN[j],
      estado: 'A',
      datoUsuarioDto: Number(this.fkDatoUsuario)};

    console.log(TelefonosT);
    this.telefonosF.push(TelefonosT);
  }

  this.telefono.setTelefonos(this.telefonosF);
}
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
  this.numP = 0;
  this.lugarfk = 0;

  /* if  (Number(this.hijosN) > 0 || Number(this.phoneN) > 0){
    this.navegacion.navigate(['datosadicionales', Number(this.hijosN), Number(this.phoneN), foranea]);
  }
  if (Number(this.hijosN) === 0 && Number(this.phoneN) === 0){*/
  this.navegacion.navigate(['modificarpersona', this.idUsuario, this.fkDatoUsuario]);
  // }




}
}
