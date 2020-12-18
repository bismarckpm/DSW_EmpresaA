import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Solicitud_Estudio } from 'src/app/modelos/solicitud_estudio';
import { User } from 'src/app/modelos/user';
import { LoginService } from 'src/app/services/login.service';
import { SolicitudestudioService } from 'src/app/services/solicitudestudio.service';

@Component({
  selector: 'app-editasolicitud',
  templateUrl: './editasolicitud.component.html',
  styleUrls: ['./editasolicitud.component.css']
})
export class EditasolicitudComponent implements OnInit {


  editarSolicitudForm: any;

  nivelEconomico: any; 
  ocupacion: any;
  productos: any;
  isWait = false;

  public user: User;
  public identity;

  public idSolicitud: any;
  public Solicitud: any; 

  constructor(
    private fb: FormBuilder,
    private _solicitudEstudioService: SolicitudestudioService,
    public _loginService: LoginService,
    private _route: ActivatedRoute,
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

    this._route.queryParams.subscribe(
      response => {
        console.log(response);
        this.idSolicitud = response;
        console.log(this.idSolicitud);
        this.buscaSolicitud(this.idSolicitud.idSolicitud);
      }
    );
    this.buscarNivelEconomico();
    this.buscarOcupacion();
    this.buscarProductos(this.identity.id); //Recuerda pasar el id del user
    this.buildForm();

  }

  buildForm(): void {
    this.editarSolicitudForm = this.fb.group({
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
    cantidadHijos: ["",
    Validators.compose([
      Validators.required])
    ],
    generoHijos: ["",
    Validators.compose([
      Validators.required])
    ],
    edadMinimaHijos: ["",
    Validators.compose([
      Validators.required])
    ],
    edadMaximaHijos: ["",
    Validators.compose([
      Validators.required])
    ],
    conCuantasPersonasVive: ["",
    Validators.compose([
      Validators.required])
    ],
    disponibilidadEnLinea: ["",
    Validators.compose([
      Validators.required])
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

buscarNivelEconomico(){
  this._solicitudEstudioService.getNivelEconomico().subscribe(
    response => {
      this.nivelEconomico = response;
      console.log(this.nivelEconomico);
    }
  );
}

buscarOcupacion(){
  this._solicitudEstudioService.getOcupacion().subscribe(
    response => {
      this.ocupacion = response;
      console.log(this.ocupacion);
    }
  )
}

buscarProductos(idUsuario: number){

  this._solicitudEstudioService.getProductos(idUsuario).subscribe(
    response => {
      this.productos = response;
    }
  )
}

buscaSolicitud(idSolicitud: number){

  this._solicitudEstudioService.getSolicitud(idSolicitud).subscribe(
    response => {
      this.Solicitud = response;
      console.log(this.Solicitud);
    }
  )
}

guardar(){

  const NewS: Solicitud_Estudio = {
    id: this.idSolicitud.idSolicitud,
    descripcionSolicitud: this.editarSolicitudForm.get("descripcionSolicitud").value,
    generoPoblacional: this.editarSolicitudForm.get("generoPoblacional").value,
    fechaPeticion: new Date(),
    edadMinimaPoblacion: this.editarSolicitudForm.get("edadMinimaPoblacion").value,
    edadMaximaPoblacion: this.editarSolicitudForm.get("edadMaximaPoblacion").value,
    estado: "A",
    cantidadHijos: this.editarSolicitudForm.get("cantidadHijos").value,
    generoHijos: this.editarSolicitudForm.get("generoHijos").value,
    edadMinimaHijos: this.editarSolicitudForm.get("edadMinimaHijos").value,
    edadMaximaHijos: this.editarSolicitudForm.get("edadMaximaHijos").value,
    conCuantasPersonasVive: this.editarSolicitudForm.get("conCuantasPersonasVive").value,
    disponibilidadEnLinea: this.editarSolicitudForm.get("disponibilidadEnLinea").value,
    nivelEconomicoDto: this.editarSolicitudForm.get("nivelEconomicoDto").value,
    productoDto: this.editarSolicitudForm.get("productoDto").value,
    ocupacionDto: this.editarSolicitudForm.get("ocupacionDto").value,
    usuarioDto: this.user.id
  }
  console.log(NewS);

  this._solicitudEstudioService.actualizarSolicitud(NewS).subscribe(
    response => {
      console.log(response);
      //location.reload();
    }
  )


}

}