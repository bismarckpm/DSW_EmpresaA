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
import { FormBuilder, Validators } from '@angular/forms';
import { LoginService } from 'src/app/services/login.service';
import { User } from 'src/app/interfaces/user';

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
    this._preguntaService.registrarPregunta(this.pregunta_encuesta).subscribe(() => {this._router.navigate(['/listadoPregunta']);} );

  }


}
