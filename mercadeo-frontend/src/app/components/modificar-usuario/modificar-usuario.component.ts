import { RolServicioService } from './../../services/rol-servicio.service';
import { EncuestadoServicioService } from './../../services/encuestado-servicio.service';
import { Dato_Usuario } from '../../interfaces/dato_usuario';
import { ConsultarUsuarioComponent } from './../consultar-usuario/consultar-usuario.component';
import { UsuarioServicioService } from './../../services/usuario-servicio.service';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Usuario } from 'src/app/interfaces/usuario';
import { Rol } from 'src/app/interfaces/rol';

@Component({
  selector: 'app-modificar-usuario',
  templateUrl: './modificar-usuario.component.html',
  styleUrls: ['./modificar-usuario.component.css']
})
export class ModificarUsuarioComponent implements OnInit {

  @Input() indice: number= 0;
  @Input() indiceEn: number= 0;
  nombreU: string = '';
  correo: string = '';
  nombreP: string = '';
  nombreS: string = '';
  apellidoP: string = '';
  apellidoS: string = '';
  cedula: string = '';
  sexo: string = '';
  fechaNac: string = '';
  edoCivil: string = '';
  fkRol: number = 0;
  fkUsu: number = 0;
  usuario!: Usuario;
  encuestado: Dato_Usuario[] = [];
  roles: Rol[] = [];
  rols = new Rol(this.fkRol);
  dats = new Dato_Usuario(this.fkUsu);
  constructor(private route: ActivatedRoute, private usuarioService: UsuarioServicioService,
              private datoUsuario: EncuestadoServicioService, private rol: RolServicioService) { }

  ngOnInit(): void {
    this.indice = this.route.snapshot.params['id'];
    this.indiceEn = this.route.snapshot.params['fk_datoUsuarios'];
    console.log(this.indice);
    console.log(this.indiceEn);
    this.usuarioService.onBuscarUsuario(this.indice).subscribe(
      (usuario: Usuario) => {
        this.usuario  = usuario;
        this.nombreU = this.usuario.nombreUsuario!;
        this.correo = this.usuario.correo!;
        this.rols = this.usuario.rolDto!;
        this.dats = this.usuario.datoUsuarioDto!;
      }
    );

    if(this.fkRol < 3){
      this.rol.onCargarRoles().subscribe(
        (roles: Rol[]) => {
          this.roles = roles;
          this.roles.splice(2,2);
        }
      );
    }

    if(this.indiceEn > 0){
      this.datoUsuario.onBuscarUsuario(this.indiceEn).subscribe(
        (encuestado: Dato_Usuario[]) => {
          this.encuestado  = encuestado;
          this.nombreP = this.encuestado[0].primerNombre!;
          this.nombreS = this.encuestado[0].segundoNombre!;
          this.apellidoP = this.encuestado[0].primerApellido!;
          this.apellidoS = this.encuestado[0].segundoApellido!;
          this.cedula = this.encuestado[0].cedula!;
          this.sexo = this.encuestado[0].sexo!;
          this.fechaNac = this.encuestado[0].fechaNacimiento!;
          this.edoCivil = this.encuestado[0].estadoCivil!;
        }
      );
    }

  }

  actualizarUsuario() {
    let user = new Usuario(this.indice, this.nombreU, this.correo,
      this.usuario.estado!, this.usuario.codigoRecuperacion!,
      this.usuario.password!, this.rols, this.dats);

    this.usuarioService.onModificarUsuario(this.indice, user);
  }



}
