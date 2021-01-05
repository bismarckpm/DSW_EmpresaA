import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GetRol } from 'src/app/interfaces/rol';
import { Usuario } from 'src/app/interfaces/usuario';
import { RolServicioService } from 'src/app/services/rol-servicio.service';
import { UsuarioServicioService } from 'src/app/services/usuario-servicio.service';

@Component({
  selector: 'app-registrarusuario',
  templateUrl: './registrarusuario.component.html',
  styleUrls: ['./registrarusuario.component.css']
})
export class RegistrarusuarioComponent implements OnInit {


  roles: GetRol[] = [];
  usuarios: Usuario[] = [];
  codigo: number = 0;
  nombreU: string = '';
  correo: string = '';
  passw: string = '';
  estado: string = '';
  codigoR: string = '';
  fkrol: number = 0;
  fkdatoU: number = 0;
  datoUfk = 0;

  constructor(private rol: RolServicioService, 
              private user: UsuarioServicioService, 
              private route: ActivatedRoute,
              private navegacion: Router) { }

  ngOnInit(): void {
    this.datoUfk = this.route.snapshot.params['fk_datoUsuario'];
    console.log(this.datoUfk);
}



  createUsuario() {
      let usu: Usuario = {
        nombreUsuario: this.nombreU,
        correo: this.correo,
        estado: "A",
        codigoRecuperacion: this.codigoR,
        password: this.passw,
        rolDto: 4,
        datoUsuarioDto: Number(this.datoUfk) 
      };
      console.log(usu);
      this.user.onGuardarUser(usu);
      //this.navegacion.navigate(['login']);
    }

}


