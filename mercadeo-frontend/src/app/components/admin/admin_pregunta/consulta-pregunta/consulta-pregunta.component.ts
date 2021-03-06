import { Component, OnInit } from '@angular/core';
import { Pregunta_Encuesta } from '../../../../interfaces/pregunta_encuesta';
import { PreguntaService } from '../../../../services/pregunta.service';
import { SubcategoriaService } from '../../../../services/subcategoria.service';
import { Subcategoria } from '../../../../interfaces/subcategoria';
import { GetSubcategoria } from '../../../../interfaces/subcategoria';
import { Usuario } from '../../../../interfaces/usuario';
import { global } from '../../../../services/global';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { idText } from 'typescript';
import { AlertService } from 'src/app/services/alert.service';
//import { ConsoleReporter } from 'jasmine';




@Component({
  selector: 'app-consulta-pregunta',
  templateUrl: './consulta-pregunta.component.html',
  styleUrls: ['./consulta-pregunta.component.css'],
  providers: [PreguntaService]
})
export class ConsultaPreguntaComponent implements OnInit {

  // Alerts
  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };


  public preguntas: any;
  public pregunta: any;
  //public subcategorias: any;
  public subcategoria: any;


  fk_subcategoria: Subcategoria = {
    id: 0,
    nombre: '',
    estado: 'A',
    descripcion: '',
    categoriaDto: 0
    //categoriaDto: Categoria [0]
  };

  subcategorias: GetSubcategoria[] = [];
  usuarios: Usuario[] = [];
  
  constructor(
    private _preguntaService: PreguntaService,
    private _router: Router,
    private _route: ActivatedRoute,
    private alertService: AlertService,

    //private _preguntas: Pregunta_Encuesta
  ) { 
    //this.preguntas;
    //this.Pregunta = new Pregunta_Encuesta(1,"","","activo",1,1)

    


  }
  ngOnInit(): void {
    this.listadoPreguntas();
  }

  editPregunta(pregunta: number){
    //console.log(pregunta);
    this._preguntaService.consultaPregunta(pregunta).subscribe(
      response => {

        this.pregunta = response;
        console.log(response);
         
      }
    )


  }

  listadoPreguntas(){
    this._preguntaService.listaPreguntas().subscribe(
      response => {

        this.preguntas = response.objeto;
        // console.log(this.preguntas);
      },error => {
      console.log(<any>error);
    }
    );

  }

  onDeletePregunta(pregunta: any): void{
    console.log(pregunta);

    const Pregunta: Pregunta_Encuesta = {
      id: pregunta._id,
      descripcion: pregunta._descripcion,
      tipoPregunta: pregunta._tipoPregunta,
      estado: pregunta._estado = "I",
      subcategoriaDto: pregunta._subcategoria._id,
      usuarioDto: pregunta._usuario._id  
  };

    if(confirm("¿Estás seguro que deseas eliminar la pregunta?")){
    
      this._preguntaService.eliminarPregunta(Pregunta).subscribe(
        response => {
          console.log(response);
          this.alertService.warn(response.mensaje + '    Estado: '+ response.estado, this.options)
        }
      );
    }
  }

  listadoSubcategorias(){
    this._preguntaService.listaSubcategoria().subscribe(
      response => {

        this.subcategorias = response.objeto;
        this.subcategorias = this.subcategorias.filter(item=> item._estado === 'A');

        console.log(response)
      }, error => {
        console.log(<any>error);
      }
    )
  }

 onUpdate(form: any){
   const Pregunta:  Pregunta_Encuesta = {
    id: this.pregunta.objeto._id,
    descripcion: this.pregunta.objeto._descripcion,
    tipoPregunta: this.pregunta.objeto._tipoPregunta,
    estado: this.pregunta.objeto._estado = "A",
    subcategoriaDto: parseInt(this.pregunta.objeto._subcategoria._id),
    usuarioDto: this.pregunta.objeto._usuario._id  
   };

  console.log('preguntame' ,Pregunta);

  this._preguntaService.actualizarPregunta(Pregunta).subscribe(
    response => {
      if(response){
        console.log(response);
        this.alertService.success(response.mensaje + '    Estado: '+ response.estado, this.options)

      // this._router.navigate(['listadoPreguntas']) -> Esto no funciona ya que nos encontramos en esa misma URL. 
        location.reload(); //Sirve para recargar la misma página. 
      }
    }, error=>{
      this.alertService.error(error.mensaje, this.options)

      console.log(<any>error);
    }
  )
  }

  consultaRespuesta(pregunta: any){
    this._router.navigate(['/consultaRespuesta'], { queryParams: {
      pregunta: pregunta
    }});
  }



}
