import { TelefonoServicioService } from './../../services/telefono-servicio.service';
import { HijoServicioService } from './../../services/hijo-servicio.service';
import { Telefono } from '../../interfaces/telefono';
import { Hijo } from '../../interfaces/hijo';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Dato_Usuario } from 'src/app/interfaces/dato_usuario';

@Component({
  selector: 'app-datos-adicionales',
  templateUrl: './datos-adicionales.component.html',
  styleUrls: ['./datos-adicionales.component.css']
})
export class DatosAdicionalesComponent implements OnInit {
  hijosF: string[] = [];
  hijosG: string[] = [];
  telefonos: string[] = [];

  hijos = 0;
  phones = 0;
  fkdatoU = 0;
  sons: number[] = [];
  phons: number[] = [];
  constructor(private route: ActivatedRoute, private hijo: HijoServicioService,
              private telefono: TelefonoServicioService,
              private navegacion: Router) { }

  ngOnInit(): void {
    this.hijos = this.route.snapshot.params['hijos'];
    console.log(this.hijos);
    for (let i = 1; i <= this.hijos; i++){
      this.sons[i] = i;
    }
    this.phones = this.route.snapshot.params['phones'];
    console.log(this.phones);
    for (let j = 1; j <= this.phones; j++){
      this.phons[j] = j;
    }
    this.fkdatoU = this.route.snapshot.params['id'];


  }

  guardarDatos() {

    console.log(this.hijosF);
    console.log(this.hijosG);
    console.log(this.telefonos);
    /* let fechaNac; */
    /* let datUser = new Dato_Usuario(Number(this.fkdatoU)); */

    if (this.sons.length !== 0){
      for (let i = 0; i < this.hijosF.length; i++) {
       /*  fechaNac = new Date(this.hijosF[i]); */
        let hijosT: Hijo = {
          fechaNacimiento: this.hijosF[i],
          genero: this.hijosG[i],
          estado: 'A',
          datoUsuarioDto: Number(this.fkdatoU)};

        this.hijo.createHijo(hijosT);
        console.log(hijosT);
      }
  }

    if (this.phons.length !== 0){
      for (let j = 0; j < this.telefonos.length; j++) {
        let TelefonosT: Telefono = {
          numero: this.telefonos[j],
          estado: 'A',
          datoUsuarioDto: Number(this.fkdatoU)};

        this.telefono.createTelefono(TelefonosT);
        console.log(TelefonosT);
      }
  }

    this.navegacion.navigate(['crearusuario', this.fkdatoU]);
  }
}
