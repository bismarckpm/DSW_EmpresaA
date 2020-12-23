import { Component, OnInit, Input } from '@angular/core';
import { EstudioclienteService } from '../../../../services/estudiocliente.service';
import { Estudio } from '../../../../interfaces/estudio';
import { Location } from '@angular/common';
import { Usuario } from '../../../../interfaces/usuario';
import { Router } from '@angular/router';



@Component({
  selector: 'app-vistaestudios',
  templateUrl: './vistaestudios.component.html',
  styleUrls: ['./vistaestudios.component.css']
})
export class VistaestudiosComponent implements OnInit {

  currDiv: string = 'A';

  ShowDiv(divVal: string) {
    this.currDiv = divVal;
  }


  public estudio: Estudio[];

  public user: Usuario = {
    id: 2,
    nombreUsuario: 'chema', 
    correo: '',
    estado: 'A',
    codigoRecuperacion: '',
    password: '',
    rolDto: 0,
    datoUsuarioDto: 0
  }

  public identity:any;

  constructor(
    private _estudioService: EstudioclienteService,
    private location: Location,
    private _router: Router,
  ) {
    this.estudio = [];
     //Este usuario se debería obtener con LocalStorage.
    
    
    
   }

   
  ngOnInit(): void {
    this.obtenerEstudios(this.user.id);
  }


  obtenerEstudios(idUsuario: number | undefined){
    this._estudioService.getEstudios(idUsuario).subscribe(
      response => {
        this.estudio = response;
        console.log(this.estudio);
      } 
    )
  }

  verEstudio(estudio: number | undefined){
    console.log(estudio);
    this._router.navigate(['/resultadosEstudio'], { queryParams: {
      estudio: estudio
    }});

  }

}
