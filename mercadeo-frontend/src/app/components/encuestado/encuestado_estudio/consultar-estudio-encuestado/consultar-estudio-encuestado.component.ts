import { LoginService } from './../../../../services/login.service';
import { GetEstudioEncuestado } from './../../../../interfaces/estudio';
import { Component, OnInit } from '@angular/core';
import { Estudio } from 'src/app/interfaces/estudio';
import { EstudioService } from 'src/app/services/estudio.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/interfaces/user';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';


@Component({
  selector: 'app-consultar-estudio-encuestado',
  templateUrl: './consultar-estudio-encuestado.component.html',
  styleUrls: ['./consultar-estudio-encuestado.component.css']
})
export class ConsultarEstudioEncuestadoComponent implements OnInit {


  showDiv = 'A';
  encuestaRespondida: any;
  public identity: any;
  isEmpty = false;

  idU: number = 1;
  idR: number = 0;
  isWait=false;
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  estudios: GetEstudioEncuestado[] = [];
  public user: User = {
    id:0,
    nombreUsuario:'',
    correo:'',
    estado:'',
    idRol:0
  };

  isUser = false;

  constructor(private estudio: EstudioService,
              private navegacion: Router,
              private _loginService: LoginService,
              private route: ActivatedRoute,
              private _snackBar: MatSnackBar) {

              }

  ngOnInit(): void {

    this.getUser();

    this._snackBar.open('Por favor espere, cargando estudios', undefined, {
      duration: 3000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });

    setTimeout(() => {
      this.estudiosRespondidos();
      this.busquedaEstudios();
      }, 1000);


  }

  getUser(): void {
    this.identity = JSON.parse(this._loginService.getIdentity());
    this.user = new User(
      this.identity.id,
      this.identity.nombreUsuario,
      this.identity.correo,
      this.identity.estado,
      this.identity.idRol )
    if (this.user) {
        this.isUser = true;
        console.log(this.user)
      }
  }

  opcionBoton(dato: any){
    this.showDiv = dato;
  }


  busquedaEstudios() {
    this.isWait=true;
    this.estudio.getEstudios(this.user.id).subscribe(
      (estudios: GetEstudioEncuestado[]) => {
        this.estudios = estudios;
        this.isWait=false;
        console.log('por responder' + this.estudios);
      }
    );
}


estudiosRespondidos(){
  this.estudio.getEncuestaRespondida(this.user.id).subscribe(
    response => {
      this.encuestaRespondida = response;

        // Si esta vacio el array
        // isEmpty = true
        if (this.encuestaRespondida.length == 0) {
          this.isEmpty = true;
        } else {
          this.isEmpty = false;
        }

      console.log('respondidos' + this.encuestaRespondida );
    }, error => {
      console.log(<any>error);
    }
  )
}

  encuestaContestada(id: number){
    this.navegacion.navigate(['encuestarespondida', id, this.user.id]);
  }
  encuesta(id: number) {
    this.navegacion.navigate(['contestarencuesta', id, this.user.id]);
  }


}
