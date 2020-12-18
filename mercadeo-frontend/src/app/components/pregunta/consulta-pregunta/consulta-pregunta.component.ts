import { Component, OnInit } from '@angular/core';
import { Pregunta_Encuesta } from '../../../modelos/pregunta_encuesta';
import { PreguntaService } from '../../../services/pregunta.service';
import { SubcategoriaService } from '../../../services/subcategoria.service';
import { Subcategoria } from '../../../interfaces/subcategoria';
import { GetSubcategoria } from '../../../interfaces/subcategoria';
import { Usuario } from '../../../modelos/usuario';
import { global } from '../../../services/global';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { Categoria } from 'src/app/modelos/categoria';
import { idText } from 'typescript';
//import { ConsoleReporter } from 'jasmine';




@Component({
  selector: 'app-consulta-pregunta',
  templateUrl: './consulta-pregunta.component.html',
  styleUrls: ['./consulta-pregunta.component.css'],
  providers: [PreguntaService]
})
export class ConsultaPreguntaComponent implements OnInit {


  public preguntas: any;
  public pregunta: any;
  //public subcategorias: any;
  public subcategoria: any;

  public Pregunta: any; 

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
    private _route: ActivatedRoute
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
        this.preguntas = response;
        console.log(this.preguntas);
      },error => {
      console.log(<any>error);
    }
    );

  }

  onDeletePregunta(pregunta: any): void{
    console.log(pregunta);

    this.Pregunta = new Pregunta_Encuesta(
      pregunta.id = pregunta._id,
      pregunta.descripcion = pregunta._descripcion,
      pregunta.tipoPregunta = pregunta._tipoPregunta,
      pregunta.estado = pregunta._estado = "I",
      pregunta.subcategoriaDto = pregunta._subcategoria._id,
      pregunta.usuarioDto = pregunta._usuario._id  
    );

    if(confirm("¿Estás seguro que deseas eliminar la pregunta?")){
    
      this._preguntaService.eliminarPregunta(this.Pregunta).subscribe(
        response => {
          console.log(response);
        }
      );
    }
  }

  listadoSubcategorias(){
    this._preguntaService.listaSubcategoria().subscribe(
      response => {
        this.subcategorias = response;
        console.log(response)
      }, error => {
        console.log(<any>error);
      }
    )
  }

 onUpdate(form: any){
   this.Pregunta = new Pregunta_Encuesta(
    this.pregunta._id,
    this.pregunta._descripcion,
    this.pregunta._tipoPregunta,
    this.pregunta._estado = "A",
    this.pregunta._subcategoria._id,
    this.pregunta._usuario._id  
  );

  console.log(this.Pregunta);
  
  this._preguntaService.actualizarPregunta(this.Pregunta).subscribe(
    response => {
      if(response){
        console.log(response);
      //this._router.navigate(['listadoPreguntas']) -> Esto no funciona ya que nos encontramos en esa misma URL. 
        //location.reload(); //Sirve para recargar la misma página. 
      }
    }, error=>{
      console.log(<any>error);
    }
  )
  }

  consultaRespuesta(pregunta: any){
    
    console.log(pregunta);
    this._router.navigate(['/consultaRespuesta'], { queryParams: {
      pregunta: pregunta
    }});
  }



}
