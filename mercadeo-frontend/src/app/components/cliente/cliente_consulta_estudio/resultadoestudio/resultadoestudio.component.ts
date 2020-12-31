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

@Component({
  selector: 'app-resultadoestudio',
  templateUrl: './resultadoestudio.component.html',
  styleUrls: ['./resultadoestudio.component.css'],
  providers: [EstudioclienteService,PreguntaService]
})
export class ResultadoestudioComponent implements OnInit {


  public idEstudio:any;
  estudio: any = [];
  nombre: any[] = [];

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

chart(enunciado: any, valor: any): Highcharts.Options {

  let chartOptions: Highcharts.Options = {

    title: {
        text: enunciado
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
highcharts: typeof Highcharts = Highcharts;
chartOptions: Highcharts.Options[] = [];


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
     this.resultadoEstudio(1);

  }

resultadoEstudio(idEstudio: number){
    this._EstudioclienteService.resultadoEstudio(idEstudio).subscribe(
      (response) => {
        this.estudio = response[0]._listaRespuestas;
        this.nombre = response;
        console.log(this.estudio);
        console.log(this.nombre);
        //this.getUsuarios(response.id);


        // const enunciado = response[0]._enunciado;
          // const valor = this.estudio.map((x:any) => { return {name: x._descripcion, y: x._valor} })
        // console.log(enunciado);
        // console.log(valor);


        this.nombre.forEach(element => {
          const valor = element._listaRespuestas.map((x:any) => { return {name: x._descripcion, y: x._valor} })
          const enunciado = element._enunciado;
  
          console.log('each', element);
  
          console.log('pregunta', element._enunciado);
          console.log('valor', valor);
         
          this.chartOptions.push( this.chart(enunciado, valor ) );
        });


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