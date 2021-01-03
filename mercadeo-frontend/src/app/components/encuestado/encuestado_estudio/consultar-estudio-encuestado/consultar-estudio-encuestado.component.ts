import { LoginService } from './../../../../services/login.service';
import { GetEstudioEncuestado } from './../../../../interfaces/estudio';
import { Component, OnInit } from '@angular/core';
import { Estudio } from 'src/app/interfaces/estudio';
import { EstudioService } from 'src/app/services/estudio.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/interfaces/user';

@Component({
  selector: 'app-consultar-estudio-encuestado',
  templateUrl: './consultar-estudio-encuestado.component.html',
  styleUrls: ['./consultar-estudio-encuestado.component.css']
})
export class ConsultarEstudioEncuestadoComponent implements OnInit {


  showDiv = 'A';
  encuestaRespondida: any;
  identity: any;

  idU: number = 0;
  idR: number = 0;
  estudios: GetEstudioEncuestado[] = [];
  /* public identity; */
  /* user: any; */
  constructor(private estudio: EstudioService,
              private navegacion: Router,
              private _loginService: LoginService) {
                this.identity = JSON.parse(_loginService.getIdentity());

                /*this.user = new User(
                  this.identity.id,
                  this.identity.nombreUsuario,
                  this.identity.correo,
                  this.identity.estado,
                  this.identity.idRol
                ); */
              }

  ngOnInit(): void {


    this.estudiosRespondidos();
    this.busquedaEstudios();
  }

  opcionBoton(dato: any){
    this.showDiv = dato;
  }


  busquedaEstudios() {
    this.estudio.getEstudios(this.identity.id).subscribe(
      (estudios: GetEstudioEncuestado[]) => {
        this.estudios = estudios;
        console.log(this.estudios);
      }
    );
}


estudiosRespondidos(){
  this.estudio.getEncuestaRespondida(this.identity.id).subscribe(
    response => {
      this.encuestaRespondida = response;
      console.log(this.encuestaRespondida);
    }, error => {
      console.log(<any>error);
    }
  )
}

  encuesta(id: number) {
    this.navegacion.navigate(['contestarencuesta', id]);
  }
/* this.route.navigate(['crearusuario', ]); */

}
