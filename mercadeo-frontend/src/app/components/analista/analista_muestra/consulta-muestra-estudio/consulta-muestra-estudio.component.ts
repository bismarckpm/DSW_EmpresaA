import { Component, OnInit } from '@angular/core';
import { MuestraAnalistaService } from 'src/app/services/muestra-analista.service';

@Component({
  selector: 'app-consulta-muestra-estudio',
  templateUrl: './consulta-muestra-estudio.component.html',
  styleUrls: ['./consulta-muestra-estudio.component.css']
})
export class ConsultaMuestraEstudioComponent implements OnInit {

  // Estados
  isWait = false;

  //  Tabla
  displayedColumns: string[] = ['Encuestado', 'Estudio', 'Fecha Inicio', 'Fecha Fin', 'Opciones'];


  // Variables de Servicios
  encuestados: any[] = [];

  constructor(
    private muestraService: MuestraAnalistaService,
    ) { }

  ngOnInit(): void {
    this.getMuestra();
  }

  getMuestra(): void {
    this.isWait = true;
    this.muestraService.getMuestra(4).subscribe(data => {
      this.encuestados = data;
      this.isWait = false;
    })
  }

}
