import { Component, OnInit } from '@angular/core';
import { Pregunta_Encuesta } from '../../../models/pregunta_encuesta';
import { PreguntaService } from '../../../services/pregunta.service';
import { global } from '../../../services/global';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { Subcategoria } from 'src/app/interfaces/subcategoria';
import { Usuario } from 'src/app/models/usuario';
import { Categoria } from 'src/app/models/categoria';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';

@Component({
  selector: 'app-registra-pregunta',
  templateUrl: './registra-pregunta.component.html',
  styleUrls: ['./registra-pregunta.component.css']
})
export class RegistraPreguntaComponent implements OnInit {

 //public subcategorias: string[];
  public seleccionado: string;
  public pregunta_encuesta: Pregunta_Encuesta;
  public url: string;
  public status: string;

  fk_subcategoria: Subcategoria ={
    id: 0,
    nombre: '',
    descripcion: '',
    estado: '',
    idCategoria: Categoria[0]
  }



  subcategorias: Subcategoria[] = [];

  constructor(
    private _preguntaService: PreguntaService,
    private _subcategoriaService: SubcategoriaService,
    public _http: HttpClient,
    private _router: Router,
    private _route: ActivatedRoute

  ) {
    //this.subcategorias = ['Cuidado personal', 'Ropa', 'Zapatos'];
    this.seleccionado = '';
    this.pregunta_encuesta = new Pregunta_Encuesta(0, '', '', 'Activo',this.fk_subcategoria, Usuario[0]);
    this.url = global.url;
    this.status = '';
  }

  ngOnInit(): void {
    this._subcategoriaService.getSubcategorias().subscribe(
      response => {
        this.subcategorias = response ;
      }
    )
    console.log(this.subcategorias)
  }

  captura() {
    console.log(this.seleccionado);
  }



  onSubmit(form: any) {


    console.log(this.pregunta_encuesta);
    this._preguntaService.registrarPregunta(this.pregunta_encuesta).subscribe(
      response => {
        console.log(response)
        this._router.navigate(['listadoPregunta']);
        
      }, error =>{
        this.status = 'error';
        console.log(<any>error);
      }
    )
  }


}
