import { UsuarioServicioService } from './../../services/usuario-servicio.service';
import { NivelEconomicoServicioService } from './../../services/nivel-economico-servicio.service';
import { OcupacionServicioService } from './../../services/ocupacion-servicio.service';
import { NivelAcademicoServicioService } from './../../services/nivel-academico-servicio.service';
import { LugarServicioService } from './../../services/lugar-servicio.service';

import { Usuario } from 'src/app/interfaces/usuario';


import { Nivel_Economico } from '../../interfaces/nivel_economico';
import { Ocupacion } from '../../interfaces/ocupacion';
import { Nivel_Academico } from '../../interfaces/nivel_academico';


import { Lugar } from '../../interfaces/lugar';
import { EncuestadoServicioService } from '../../services/encuestado-servicio.service';
import { Dato_Usuario } from '../../interfaces/dato_usuario';
import { Component, Input, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { Rol } from 'src/app/interfaces/rol';
import { HttpClient } from '@angular/common/http';

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
  fechaNacimiento = '';
  edoCivil = '';
  disp = '';
  numP = '';
  users: Dato_Usuario[] = [];
  lugares: Lugar[] = [];
  nivel: Nivel_Academico[] = [];
  nivelfk = 0;
  ocup: Ocupacion[] = [];
  ocupfk = 0;
  nivelesE: Nivel_Economico[] = [];
  nivelEfk = 0;
  nombreU = '';
  correo = '';
  password = '';
  fkrol = 4;
  usuarioId = 0;
  estado = 'A';
  codigoR = '';
  hijosN = '';
  phoneN = '';
  foranea = 0;
  foraneas:number[]=[];
  constructor(private usuarioService: EncuestadoServicioService,
              private lugarService: LugarServicioService,
              private nivelA: NivelAcademicoServicioService,
              private ocupacion: OcupacionServicioService,
              private nivelEco: NivelEconomicoServicioService,
              private userS: UsuarioServicioService,
              private navegacion: Router,
              ) { }

  ngOnInit(): void {
     this.lugarService.onCargarLugar().subscribe(
      (lugar: Lugar[]) => {
        this.lugares = lugar;
      }
  );

     this.nivelA.onCargarNivel().subscribe(
    (nivel: Nivel_Academico[]) => {
      this.nivel = nivel;
    }
  );

     this.ocupacion.onCargarOcupacion().subscribe(
    (ocupacion: Ocupacion[]) => {
      this.ocup = ocupacion;
     }
  );

     this.nivelEco.onCargarNivelE().subscribe(
    (nivelE: Nivel_Economico[]) => {
      this.nivelesE = nivelE;
     }
  );

   this.usuarioService.traerEncuestados().subscribe(
    (usuario: Dato_Usuario[]) => {
      this.users = usuario;
     }
  );

}

 insertarUsuario() {
  console.log(this.users);
  // tslint:disable-next-line: no-non-null-assertion
  let f = this.users.slice(-1)[0].id! + 1;
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
    nivelEconomicoDto: this.nivelEfk};

  this.usuarioService.onGuardarUsuario(encuestado).subscribe(
     data => { this.foranea = data.id;
      console.log(this.foranea);
      }
  )
  //console.log(f);
  console.log("HOLAAAA :D");
  console.log(this.foranea);
  /* console.log(f); */
  /* let enc = new Dato_Usuario(f); */
  let usuario: Usuario = {
     nombreUsuario: this.nombreU,
     correo: this.correo,
     estado: this.estado,
     codigoRecuperacion: this.codigoR,
     password: this.password,
     rolDto: this.fkrol,
     datoUsuarioDto: f};

  this.userS.onGuardarUser(usuario);

  this.nombreP = '';
  this.nombreS = '';
  this.cedula = '';
  this.apellidoP = '';
  this.apellidoS = '';
  this.sexo = '';
  this.fechaNacimiento = '';
  this.edoCivil = '';
  this.disp = '';
  this.numP = '';
  this.lugarfk = 0;

  if  (Number(this.hijosN) > 0 || Number(this.phoneN) > 0){
    this.navegacion.navigate(['datosadicionales', Number(this.hijosN), Number(this.phoneN), f]);
  }
}



}
