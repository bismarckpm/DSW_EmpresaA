import { Component, OnInit } from '@angular/core';
import { Pregunta_Encuesta } from '../../../models/pregunta_encuesta';
import { PreguntaService } from '../../../services/pregunta.service';
import { SubcategoriaService } from '../../../services/subcategoria.service';
import { Subcategoria } from '../../../interfaces/subcategoria';
import { Usuario } from '../../../models/usuario';
import { global } from '../../../services/global';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { Categoria } from 'src/app/models/categoria';
import { idText } from 'typescript';
//import { ConsoleReporter } from 'jasmine';




@Component({
  selector: 'app-consulta-pregunta',
  templateUrl: './consulta-pregunta.component.html',
  styleUrls: ['./consulta-pregunta.component.css'],
  providers: [PreguntaService,SubcategoriaService]
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
    estado: 'Activo',
    descripcion: '',
    idCategoria: Categoria [0]
  };

  subcategorias: Subcategoria[] = [];
  usuarios: Usuario[] = [];
  
  constructor(
    private _preguntaService: PreguntaService,
    private _subcategoriaService: SubcategoriaService,
    private _router: Router,
    private _route: ActivatedRoute
    //private _preguntas: Pregunta_Encuesta
  ) { 
    this.preguntas;
    //this.Pregunta = new Pregunta_Encuesta(1,"","","activo",1,1)

  }
  ngOnInit(): void {
    this.listadoPreguntas();
    
    this._subcategoriaService.getSubcategorias().subscribe(
      response => {
        this.subcategorias = response;
        //console.log(this.subcategorias)
      }
    );
  }

  editPregunta(pregunta: Pregunta_Encuesta){
    //console.log(pregunta);
    this._preguntaService.consultaPregunta(pregunta.id).subscribe(
      response => {
        this.pregunta = response;
        //console.log(response);
         
      }
    )
  }

  listadoPreguntas(){
    this._preguntaService.listaPreguntas().subscribe(
      response => {
        this.preguntas = response;
        //console.log(this.preguntas);
      },error => {
      console.log(<any>error);
    }
    );

  }

  onDeletePregunta(pregunta: Pregunta_Encuesta): void{
    //console.log(pregunta);
    const Pregunta: Pregunta_Encuesta = {
      id: pregunta.id,
      descripcion: pregunta.descripcion,
      tipoPregunta: pregunta.tipoPregunta,
      estado: 'Inactivo',
      fk_subcategoria: pregunta.fk_subcategoria,
      fk_usuario: pregunta.fk_usuario
    }
    if(confirm("¿Estás seguro que deseas eliminar la pregunta?")){
    
      this._preguntaService.eliminarPregunta(Pregunta).subscribe(
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
        //console.log(response);
      }, error => {
        console.log(<any>error);
      }
    )
  }

 onUpdate(form: any){
   this.Pregunta = new Pregunta_Encuesta(
    this.pregunta.id,
    this.pregunta.descripcion,
    this.pregunta.tipoPregunta,
    this.pregunta.estado = "Activo",
    this.pregunta.fk_subcategoria,
    this.pregunta.fk_usuario  
  );

  console.log(this.Pregunta);
  
  this._preguntaService.actualizarPregunta(this.Pregunta).subscribe(
    response => {
      if(response){
        console.log(response);
      //this._router.navigate(['listadoPreguntas']) -> Esto no funciona ya que nos encontramos en esa misma URL. 
        location.reload(); //Sirve para recargar la misma página. 
      }
    }, error=>{
      console.log(<any>error);
    }
  )
  }

  consultaRespuesta(pregunta: Pregunta_Encuesta){
    const idPregunta = pregunta.id;
    this._router.navigate(['/consultaRespuesta'], { queryParams: {
      pregunta: idPregunta
    }});
  }



}
