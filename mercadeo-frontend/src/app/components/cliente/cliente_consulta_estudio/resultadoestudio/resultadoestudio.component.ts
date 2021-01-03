import { Component, Input, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
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

import { MatPaginator } from '@angular/material/paginator';

import * as Highcharts from 'highcharts';

import highcharts3D from 'highcharts/highcharts-3d';
import { MatTableDataSource } from '@angular/material/table';
import { Observable } from 'rxjs';

highcharts3D(Highcharts);

@Component({
  selector: 'app-resultadoestudio',
  templateUrl: './resultadoestudio.component.html',
  styleUrls: ['./resultadoestudio.component.css'],
  providers: [EstudioclienteService,PreguntaService]
})
export class ResultadoestudioComponent implements OnInit {

  @ViewChildren(MatPaginator) paginator = new QueryList<MatPaginator>();

  // chart Data Respuesta Estudios
  highcharts: typeof Highcharts = Highcharts;
  chartOptions: Highcharts.Options[] = [];
  
  obs!: Observable<any>;
  dataSource!: MatTableDataSource<any>;
  resp: any[] = []; 


  public idEstudio:any;
  estudio: any = [];
  nombre: any[] = [];
  respuestaAbierta: any; 
  respAbierta: any;
  esAbierta:any =  false; 


  //Datos del Estudio
  estudioActual: any;
  productoEstudioActual: any;
  personasParticipantes: any;
  

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

// HighCharts
// Funcion para crear el highchart
// El enunciado recibe el titulo o la pregunta
// Valor obtendra 'name' e 'y' como los datos para generar la grafica
chart(enunciado: any, valor: any): Highcharts.Options {

  let chartOptions: Highcharts.Options = {

    chart: {
      type: "pie",
      plotShadow: false,
      options3d: {
        enabled: true,
        alpha: 45,
        beta: 0,
    },
  },
  title: {
      text: enunciado
  },
  tooltip: {
    headerFormat: "",
    pointFormat:
      "<span style='color:{point.color}'>\u25CF</span> {point.name}: <b>{point.y}</b>",
    style: {
      fontSize: '10px'
    },
  },
  plotOptions : {
    pie: {
      shadow: true,
      center: ["50%", "50%"],
      depth: 25,
      innerSize: "20%",
      allowPointSelect: true,
      cursor: 'pointer',
       dataLabels: {
          enabled: false
       },
       showInLegend: true
    }
 },
  series: [
    {
      type: "pie",
      data: valor 
    }
  ]

};
  return chartOptions;
}



  constructor(
    private _route: ActivatedRoute,
    private _EstudioclienteService: EstudioclienteService
  ) {

  }


  ngOnInit(): void {

   this._route.queryParams.subscribe(
     response =>
     {
       this.idEstudio = response;
       console.log(this.idEstudio);
     }
   );
     console.log(this.idEstudio);
     this.resultadoEstudio(this.idEstudio.estudio);
     this.obtenerEstudioActual(this.idEstudio.estudio);
     this.obtenerProductoEstudioActual(this.idEstudio.estudio);
     this.cantidadParticipantes(this.idEstudio.estudio);
  }

resultadoEstudio(idEstudio: number){
    this._EstudioclienteService.resultadoEstudio(idEstudio).subscribe(
      (response) => {
        this.estudio = response[0]._listaRespuestas;
        this.nombre = response;
        const prueba = response;
        // console.log(prueba);
        //console.log(this.estudio);
        //console.log(this.nombre);
        //this.getUsuarios(response.id);


        // Se realiza una iteracion para obtener el nombre y el valor para generar el pieChart.
        this.nombre.forEach(element => {
          if(element._tipoPregunta != 'Abierta'){
          const valor = element._listaRespuestas.map((x:any) => { return {name: x._descripcion, y: x._valor} })
          const enunciado = element._enunciado;

          this.chartOptions.push( this.chart(enunciado, valor ) );

       

          }else{

            // Caso de ser una respuesta abierta, la cual no requiere grafico, se van
            // pasando los datos en la funcion getRespuestasAbiertas()

            this.esAbierta = true;
            this.respuestaAbierta = element._enunciado;
            this.respAbierta = element._listaRespuestas.map((x:any) => { return {name: x._descripcion, user: x._preguntaAux} })
            // console.log('abierta', this.respAbierta);
            // console.log(this.respAbierta);

            this.getRespuestasAbiertas(this.respuestaAbierta, this.respAbierta);

            // this.dataSource = new MatTableDataSource<any>(this.respAbierta)
            // this.dataSource.paginator = this.paginator.toArray()[0];
            // this.obs = this.dataSource.connect();
 
          }


        });


      }
    )

  }

  getRespuestasAbiertas(enunciado: string, valor: any): any{
    // Se obtienen los datos y se van introduciendo en un array, la cual se usara
    // para mostrar los datos

    const array = {enunciado: enunciado, valor: valor}
    this.resp.push( array );

  }


  getUsuarios(idEstudio: number){ //Esto deberia ser USUARIOS
    this._EstudioclienteService.getUsuarios(idEstudio).subscribe(
      response => {
        this.usuario = response;
      }
    )
  }

obtenerEstudioActual(idEstudio: number){
  this._EstudioclienteService.getEstudioEspecifico(idEstudio).subscribe(
    response => {
      this.estudioActual = response;
      console.log('Estudio Actual', this.estudioActual);
    }, error => {
      console.log(<any>error);
    }
  )
}

obtenerProductoEstudioActual(idEstudio: number){
  this._EstudioclienteService.getProductoEstudio(idEstudio).subscribe(
    response => {
      this.productoEstudioActual = response; 
      console.log(this.productoEstudioActual);
    }, error => {
      console.log(<any>error);
    } 
  )
}

cantidadParticipantes(idEstudio: number){
  this._EstudioclienteService.cantidadParticipantes(idEstudio).subscribe(
    response => {
      this.personasParticipantes = response; 
      console.log(this.personasParticipantes);
    }, error => {
      console.log(<any>error);
    }
  )
}




}
