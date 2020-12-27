import { Component, OnInit } from '@angular/core';
import { EstudioService } from 'src/app/services/estudio.service';

@Component({
  selector: 'app-consulta-muestra-sin-responder',
  templateUrl: './consulta-muestra-sin-responder.component.html',
  styleUrls: ['./consulta-muestra-sin-responder.component.css']
})
export class ConsultaMuestraSinResponderComponent implements OnInit {

  // Estados
  isWait = false;

  //  Tabla
  displayedColumns: string[] = ['Encuestado', 'Estudio', 'Disponibilidad', 'Estatus', 'Opciones'];

  // Variables
  encuestados: any[] = [];
  oneExists : boolean = false;

  constructor(
    private estudioService: EstudioService,
  ) { }

  ngOnInit(): void {
    this.getEncuestadosSinR();

  }

  getEncuestadosSinR(): void {
    this.estudioService.getEncuestadosSinResolver(4).subscribe(data => { 
      this.encuestados = data;
      this.oneExists = this.encuestados.filter(item => item.details.id === 1).length > 0;
    })
  }

  

}
