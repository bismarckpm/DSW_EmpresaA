import { Component, OnInit } from '@angular/core';
import { Pregunta_Encuesta } from '../../../../interfaces/pregunta_encuesta';
import { PreguntaService } from '../../../../services/pregunta.service';
import { global } from '../../../../services/global';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { Subcategoria } from 'src/app/interfaces/subcategoria';
import { GetSubcategoria } from 'src/app/interfaces/subcategoria';
import { Usuario } from 'src/app/interfaces/usuario';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';
import { FormArray, FormBuilder, Validators } from '@angular/forms';
import { LoginService } from 'src/app/services/login.service';
import { User } from 'src/app/interfaces/user';
import { Respuesta_Pregunta } from 'src/app/interfaces/respuesta_pregunta';
import { RespuestapreguntaService } from 'src/app/services/respuestapregunta.service';

@Component({
  selector: 'app-registra-pregunta',
  templateUrl: './registra-pregunta.component.html',
  styleUrls: ['./registra-pregunta.component.css']
})
export class RegistraPreguntaComponent implements OnInit {

  //public subcategorias;
  //public seleccionado: string;
   pregunta_encuesta: Pregunta_Encuesta = {
     id: 0,
     descripcion: '',
     tipoPregunta: '',
     estado: 'A', 
     subcategoriaDto: 0,
     usuarioDto: 0
    };

  /*  respuesta_pregunta: Respuesta_Pregunta = {
      id: 0,
      nombre: '',
      estado: '',
      preguntaEncuestaDto: 0
    }*/
    
  public identity;
  public user: User;

  //public status: string;

  registrarPreguntaForm: any;

 /* subcategoriaDto: Subcategoria ={
    id: 0,
    nombre: '',
    descripcion: '',
    estado: '',
    categoriaDto: Categoria[0]
  }*/



  subcategorias: GetSubcategoria[] = [];

  constructor(
    private _preguntaService: PreguntaService,
    private _respuestaPreguntaService: RespuestapreguntaService,
    private _subcategoriaService: SubcategoriaService,
    public _http: HttpClient,
    private _router: Router,
    private _route: ActivatedRoute,
    private fb: FormBuilder,
    private _loginService: LoginService

  ) {
    //this.subcategorias = ['Cuidado personal', 'Ropa', 'Zapatos'];
    //this.seleccionado = '';

    this.identity = JSON.parse(_loginService.getIdentity());
    //this.status = '';

    this.user = new User(
      this.identity.id,
      this.identity.nombreUsuario,
      this.identity.correo,
      this.identity.estado,
      this.identity.idRol
    )
    
  }

  ngOnInit(): void {
    this._subcategoriaService.getSubcategorias().subscribe(
      response => {
        this.subcategorias = response ;
      }
    )
    console.log(this.subcategorias)
    this.buildForm();
    console.log(this.user);
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
    ], respuestaAsignada: this.fb.array([this.añadeRespuesta()])
  });
}

añadeRespuesta() {
  return this.fb.group({
    id: 0,
    nombre: [""],
    estado: ["A"],
    preguntaEncuestaDto: 0
  })
}

addNext() {
  (this.registrarPreguntaForm.controls['respuestaAsignada'] as FormArray).push(this.añadeRespuesta());
}

eliminarOpcion(index: number) {
  (this.registrarPreguntaForm.controls['respuestaAsignada'] as FormArray).removeAt(index);
}


  onSubmit() {

  /*  this.pregunta_encuesta = {
      id: 0,
      descripcion: this.registrarPreguntaForm.get("descripcion").value,
      tipoPregunta: this.registrarPreguntaForm.get("tipoPregunta").value,
      estado: "A",
      subcategoriaDto: this.registrarPreguntaForm.get("subcategoriaDto").value._id,
      usuarioDto: this.user.id
    }

    console.log(this.registrarPreguntaForm.get("respuestaAsignada").value);


    console.log(this.pregunta_encuesta);
    //this._preguntaService.registrarPregunta(this.pregunta_encuesta).subscribe(() => {this._router.navigate(['/listadoPregunta']);} );
*/
  }

  creaPregunta(){

    this.pregunta_encuesta = {
      id: 0,
      descripcion: this.registrarPreguntaForm.get("descripcion").value,
      tipoPregunta: this.registrarPreguntaForm.get("tipoPregunta").value,
      estado: "A",
      subcategoriaDto: this.registrarPreguntaForm.get("subcategoriaDto").value._id,
      usuarioDto: this.user.id
    }

    const respuestas = this.registrarPreguntaForm.get("respuestaAsignada").value;

    console.log(this.pregunta_encuesta);
    console.log(respuestas);

    this._preguntaService.registrarPregunta(this.pregunta_encuesta).subscribe(
      response =>  {
        console.log(response);
        if(this.pregunta_encuesta.tipoPregunta == 'Seleccion simple' || this.pregunta_encuesta.tipoPregunta == 'Seleccion multiple' ){
          this._respuestaPreguntaService.registraRespuestaConPregunta(response.id,respuestas).subscribe(
            respuesta => {
              console.log(respuesta);
            }
          );
        }
      }
    );
    //this._router.navigate(['/listadoPregunta']);
    }
}
