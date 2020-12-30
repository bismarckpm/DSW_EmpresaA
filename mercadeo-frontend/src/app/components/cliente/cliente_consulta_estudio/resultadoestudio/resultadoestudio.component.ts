import { Component, Input, OnInit } from '@angular/core';
import { EstudioclienteService } from '../../../../services/estudiocliente.service';
import { PreguntaService } from '../../../../services/pregunta.service';
import { Estudio } from '../../../../interfaces/estudio';
import { Pregunta_Encuesta } from '../../../../interfaces/pregunta_encuesta';
import { Pregunta_Estudio } from '../../../../interfaces/pregunta_estudio';
import { Respuesta_Pregunta } from '../../../../interfaces/respuesta_pregunta';
import {Subcategoria } from '../../../../interfaces/subcategoria';
import { Location } from '@angular/common';
import { Usuario } from '../../../../interfaces/usuario';
import { Solicitud_Estudio } from 'src/app/interfaces/solicitud_estudio';
import { ActivatedRoute } from '@angular/router';
import { Categoria } from '../../../../interfaces/categoria';

import * as Highcharts from 'highcharts';
import { ProductoService } from 'src/app/services/producto.service';

@Component({
  selector: 'app-resultadoestudio',
  templateUrl: './resultadoestudio.component.html',
  styleUrls: ['./resultadoestudio.component.css'],
  providers: [EstudioclienteService,PreguntaService]
})
export class ResultadoestudioComponent implements OnInit {


  public idEstudio:any;
  estudio: Estudio = {
    id: 0,
    nombre: '',
    fechaInicio: new Date(),
    fechaFin: new Date(),
    estatus: '',
    estado: 'A',
    solicitudEstudioDto: 0,
    usuarioDto: 0
  };

  solicitudEstudio: Solicitud_Estudio = {
    id: 0,
    descripcionSolicitud: '',
    generoPoblacional: '',
    fechaPeticion: new Date(),
    edadMinimaPoblacion: '',
    edadMaximaPoblacion: '',
    estatus: 'Solicitado',
    estado: 'A',
    conCuantasPersonasVive: 0,
    disponibilidadEnLinea: '',
    nivelEconomicoDto: 0,
    productoDto: 0,
    ocupacionDto:0,
    usuarioDto: 0,

  }

  usuario: Usuario = {
    id: 0,
    nombreUsuario: '',
    correo: '',
    estado: '',
    codigoRecuperacion: '',
    password: '',
    rolDto: 0,
    datoUsuarioDto: 0
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
    categoriaDto: 0
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
    private _EstudioclienteService: EstudioclienteService,
    private _productoService: ProductoService,
  ) {

  }

  productos : any[] = [];

  get(): void {
    this._productoService.getProductos().subscribe(data => 
      {this.productos = data;
        console.log(this.productos);
        const h = this.productos.map(x => x._id);
        console.log(h);

        Highcharts
        this.chartOptions.series= h;
        

      });
  }

  ngOnInit(): void {

  this.get();
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
        this.getUsuarios(response.id);
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


  
// HighCharts
highcharts: typeof Highcharts = Highcharts;
chartOptions: Highcharts.Options = {
    chart: {
      type: 'column'
    },
    xAxis: {
      categories: ['que','loco']
    },
    series: [
      {
        data: [],
        type: 'column'
      }
    ],
        lang: {
        noData: 'https://www.highcharts.com/samples/graphics/sun.png'
    },
    noData: {
        style: {
            fontWeight: 'bold',
            fontSize: '15px',
            color: '#303030'
        }
    }
  };





}
