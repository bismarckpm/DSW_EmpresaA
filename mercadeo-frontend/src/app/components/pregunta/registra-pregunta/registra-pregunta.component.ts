import { Component, OnInit } from '@angular/core';
import { Pregunta_Encuesta } from '../../../modelos/pregunta_encuesta';
import { PreguntaService } from '../../../services/pregunta.service';
import { global } from '../../../services/global';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { Subcategoria } from 'src/app/interfaces/subcategoria';
import { GetSubcategoria } from 'src/app/interfaces/subcategoria';
import { Usuario } from 'src/app/modelos/usuario';
import { Categoria } from 'src/app/modelos/categoria';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';
import { FormBuilder, Validators } from '@angular/forms';
import { LoginService } from 'src/app/services/login.service';
import { User } from 'src/app/modelos/user';

@Component({
  selector: 'app-registra-pregunta',
  templateUrl: './registra-pregunta.component.html',
  styleUrls: ['./registra-pregunta.component.css']
})
export class RegistraPreguntaComponent implements OnInit {

  //public subcategorias;
  //public seleccionado: string;
  public pregunta_encuesta: Pregunta_Encuesta;
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
    private _subcategoriaService: SubcategoriaService,
    public _http: HttpClient,
    private _router: Router,
    private _route: ActivatedRoute,
    private fb: FormBuilder,
    private _loginService: LoginService

  ) {
    //this.subcategorias = ['Cuidado personal', 'Ropa', 'Zapatos'];
    //this.seleccionado = '';
    this.pregunta_encuesta = new Pregunta_Encuesta(0, '', '', 'Activo',0, 0);
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
    ]
  });
}





  onSubmit() {

    this.pregunta_encuesta = {
      id: 0,
      descripcion: this.registrarPreguntaForm.get("descripcion").value,
      tipoPregunta: this.registrarPreguntaForm.get("tipoPregunta").value,
      estado: "A",
      subcategoriaDto: this.registrarPreguntaForm.get("subcategoriaDto").value._id,
      usuarioDto: this.user.id
    }

    console.log(this.pregunta_encuesta);
    this._preguntaService.registrarPregunta(this.pregunta_encuesta).subscribe();
  }


}
