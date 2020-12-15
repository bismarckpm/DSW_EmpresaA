import { Component, Input, OnInit } from '@angular/core';
import { EstudioclienteService } from '../../../services/estudiocliente.service';
import { PreguntaService } from '../../../services/pregunta.service';
import { Estudio } from '../../../models/estudio';
import { Pregunta_Encuesta } from '../../../models/pregunta_encuesta';
import { Pregunta_Estudio } from '../../../models/pregunta_estudio';
import { Respuesta_Pregunta } from '../../../models/respuesta_pregunta';
import {Subcategoria } from '../../../models/subcategoria';
import { Location } from '@angular/common';
import { Usuario } from '../../../models/usuario';
import { Solicitud_Estudio } from 'src/app/models/solicitud_estudio';
import { ActivatedRoute } from '@angular/router';
import { Categoria } from 'src/app/models/categoria';

@Component({
  selector: 'app-resultadoestudio',
  templateUrl: './resultadoestudio.component.html',
  styleUrls: ['./resultadoestudio.component.css'],
  providers: [EstudioclienteService,PreguntaService]
})
export class ResultadoestudioComponent implements OnInit {


  public idEstudio;
  estudio: Estudio;

  solicitudEstudio: Solicitud_Estudio = {
    id: 0,
    descripcionSolicitud: '',
    generoPoblacional: '',
    fechaPeticion: '',
    edadMinimaPoblacion: '',
    edadMaximaPoblacion: '',
    estado: 'A',
    cantidadHijos: 0,
    generoHijos: '',
    edadMinimaHijos: '',
    edadMaximaHijos: '',
    conCuantasPersonasVive: 0,
    disponibilidadEnLinea: '',
    fk_nivelEconomico: 0,
    fk_producto: 0,
    fk_usuario: 0,
    fk_ocupacion:0
  }

  usuario: Usuario = {
    id: 0,
    nombreUsuario: '',
    correo: '',
    estado: '',
    codigoRecuperacion: '',
    password: '',
    fk_rol: 0,
    fk_datoUsuario: 0
  }


  categoria: Categoria = {
    id: 0,
    nombre: '',
    estado: ''
  }

  subcategoria: Subcategoria = {
    id: 0,
    nombre: '',
    descripcion: '',
    estado: '',
    fk_categoria: 0
  }

  pregunta_encuesta: Pregunta_Encuesta = {
    id: 0,
    descripcion: '',
    tipoPregunta: '',
    estado: '',
    subcategoriaDto: 0,
    usuarioDto: 0
  }

  constructor(
    private _route: ActivatedRoute,
    private _EstudioclienteService: EstudioclienteService
  ) {
    this.estudio = new Estudio(0,'','','','','','',0,0);
  }


  ngOnInit(): void {
    
   this._route.queryParams.subscribe(
     response => 
     {
       this.idEstudio = response;
     }
   );
     console.log(this.idEstudio);
     this.getEstudio(this.idEstudio.estudio);
     
  }

  getEstudio(idEstudio: number){
    this._EstudioclienteService.getEstudio(idEstudio).subscribe(
      response => {
        this.estudio = response
        console.log(this.estudio);
        this.getUsuarios(this.estudio.id);
      }
    )
    
  }

  getUsuarios(idEstudio: number){ //Esto deberia ser USUARIOS
    this._EstudioclienteService.getUsuarios(idEstudio).subscribe(
      response => {
        this.usuario = response;
      }
    )
  }





}
