import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { from, identity } from 'rxjs';
import { Ocupacion } from 'src/app/interfaces/ocupacion';
import { Solicitud_Estudio } from '../../../../../interfaces/solicitud_estudio';
import { SolicitudestudioService } from '../../../../../services/solicitudestudio.service';
import { DatePipe } from '@angular/common';
import { User } from 'src/app/interfaces/user';
import { LoginService } from 'src/app/services/login.service';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';
import { NivelEconomicoServicioService } from 'src/app/services/nivel-economico-servicio.service';
import { OcupacionServicioService } from 'src/app/services/ocupacion-servicio.service';
import { ProductoService } from 'src/app/services/producto.service';

@Component({
  selector: 'app-registrarsolicitud',
  templateUrl: './registrarsolicitud.component.html',
  styleUrls: ['./registrarsolicitud.component.css'],
  providers: [SolicitudestudioService]
})
export class RegistrarsolicitudComponent implements OnInit {


  registrarSolicitudForm: any;

  nivelEconomico: any; 
  ocupacion: any;
  productos: any;
  isWait = false;

  public user: User;
  public identity;
  public opcion = false;

  constructor(
    private fb: FormBuilder,
    private _solicitudEstudioService: SolicitudestudioService,
    private _nivelEconomicoService: NivelEconomicoServicioService,
    private _ocupacionService: OcupacionServicioService,
    private _productoService: ProductoService,
    public datepipe: DatePipe,
    public _loginService: LoginService,
    public _router: Router
  ) {

    this.identity = JSON.parse(_loginService.getIdentity());


    this.user = new User(
      this.identity.id,
      this.identity.nombreUsuario,
      this.identity.correo,
      this.identity.estado,
      this.identity.idRol
    )
   }

  ngOnInit(): void {
    this.buscarNivelEconomico();
    this.buscarOcupacion();
    this.buscarProductos(this.identity.id); //Recuerda pasar el id del user
    this.buildForm();
  }

  /* Estos dos metodos funcionan para mostrar los datos opcionales*/
  activaOpcion(){
    this.opcion=true;
  }

  desactivaOpcion(){
    this.opcion =false;
  }


/*Construcción del formulario para guardar la solicitud*/

  buildForm(): void {
    this.registrarSolicitudForm = this.fb.group({
     descripcionSolicitud: ["",
     Validators.compose([
       Validators.required]),
    ],
     generoPoblacional: ["",
     Validators.compose([
       Validators.required]),
    ],
    fechaPeticion: ["",
    Validators.compose([
      Validators.required]),
    ],
      edadMinimaPoblacion: ["",
    Validators.compose([
      Validators.required])
    ],
    edadMaximaPoblacion: ["",
    Validators.compose([
      Validators.required])
    ],
    cantidadHijos: [""
    ],
    generoHijos: [""
    ],
    edadMinimaHijos: [""
    ],
    edadMaximaHijos: [""
    ],
    conCuantasPersonasVive: [""
    ],
    disponibilidadEnLinea: [""
    ],
    nivelEconomicoDto: ["",
    Validators.compose([
      Validators.required])
    ],
    productoDto: ["",
    Validators.compose([
      Validators.required])
    ],
    ocupacionDto: ["",
    Validators.compose([
      Validators.required])
    ],
   });
 }

 // Obtener todos los niveles economicos de la base de datos
buscarNivelEconomico(){
  this._nivelEconomicoService.onCargarNivelE().subscribe(
    response => {
      this.nivelEconomico = response;
      console.log(this.nivelEconomico);
    }
  );
}

//Obtener todas las ocupaciones existentes en la bd
buscarOcupacion(){
  this._ocupacionService.onCargarOcupacion().subscribe(
    response => {
      this.ocupacion = response;
      console.log(this.ocupacion);
    }
  )
}


//Obtener todos los productos de ese cliente de la BD
buscarProductos(idUsuario: number){

  this._productoService.getProductosCliente(idUsuario).subscribe(
    response => {
      this.productos = response;
      console.log(this.productos);
    }
  )
}


//Método que guarda los datos de la solicitud

  guardar(){

    const NewS: Solicitud_Estudio = {
      id: 0,
      descripcionSolicitud: this.registrarSolicitudForm.get("descripcionSolicitud").value,
      generoPoblacional: this.registrarSolicitudForm.get("generoPoblacional").value,
      fechaPeticion: new Date(),
      edadMinimaPoblacion: this.registrarSolicitudForm.get("edadMinimaPoblacion").value,
      edadMaximaPoblacion: this.registrarSolicitudForm.get("edadMaximaPoblacion").value,
      estado: "A",
      cantidadHijos: this.registrarSolicitudForm.get("cantidadHijos").value,
      generoHijos: this.registrarSolicitudForm.get("generoHijos").value,
      edadMinimaHijos: this.registrarSolicitudForm.get("edadMinimaHijos").value,
      edadMaximaHijos: this.registrarSolicitudForm.get("edadMaximaHijos").value,
      conCuantasPersonasVive: this.registrarSolicitudForm.get("conCuantasPersonasVive").value,
      disponibilidadEnLinea: this.registrarSolicitudForm.get("disponibilidadEnLinea").value,
      solicitudNivelEconomicoDto: this.registrarSolicitudForm.get("nivelEconomicoDto").value,
      solicitudProductoDto: this.registrarSolicitudForm.get("productoDto").value,
      solicitudOcupacionDto: this.registrarSolicitudForm.get("ocupacionDto").value,
      solicitudUsuarioDto: this.user.id
    }
    console.log(NewS);

    this._solicitudEstudioService.registrarSolicitud(NewS).subscribe(
      response => {
        console.log(response);
        this._router.navigate(['vistaSolicitud']);
      }
    )


  }

}
