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
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-registra-pregunta',
  templateUrl: './registra-pregunta.component.html',
  styleUrls: ['./registra-pregunta.component.css']
})
export class RegistraPreguntaComponent implements OnInit {

  //public subcategorias;
  //public seleccionado: string;
  //public pregunta_encuesta: Pregunta_Encuesta;

  //public status: string;

  registrarPreguntaForm: any;

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
    private _route: ActivatedRoute,
    private fb: FormBuilder,

  ) {
    //this.subcategorias = ['Cuidado personal', 'Ropa', 'Zapatos'];
    //this.seleccionado = '';
    //this.pregunta_encuesta = new Pregunta_Encuesta(0, '', '', 'Activo',0, 0);

    //this.status = '';
  }

  ngOnInit(): void {
    this._subcategoriaService.getSubcategorias().subscribe(
      response => {
        this.subcategorias = response ;
      }
    )
    console.log(this.subcategorias)
    this.buildForm();
  }

  buildForm(): void {
    this.registrarPreguntaForm = this.fb.group({
     descripcion: ["",
     Validators.compose([
       Validators.required]),
    ],tipoPregunta: ["",
    Validators.compose([
      Validators.required]),
    ],subcategoriaDto: ["",
    Validators.compose([
      Validators.required]),
    ]
  });
}





  onSubmit() {

    const NewP = {
      id: 0,
      descripcion: this.registrarPreguntaForm.get("descripcion").value,
      tipoPregunta: this.registrarPreguntaForm.get("tipoPregunta").value,
      estado: "A",
      subcategoriaDto: this.registrarPreguntaForm.get("subcategoriaDto").value.id,
      usuarioDto: 1
    }

    console.log(NewP);
    this._preguntaService.registrarPregunta(NewP).subscribe(
      response => {
        console.log(response)
        //this._router.navigate(['listadoPregunta']);
        
      }, error =>{
        //this.status = 'error';
        console.log(<any>error);
      }
    )
  }


}
