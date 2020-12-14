import { Component, OnInit } from '@angular/core';
import { Estudio } from 'src/app/models/estudio';
import { EstudioService } from 'src/app/services/estudio.service';

@Component({
  selector: 'app-consultar-estudio-encuestado',
  templateUrl: './consultar-estudio-encuestado.component.html',
  styleUrls: ['./consultar-estudio-encuestado.component.css']
})
export class ConsultarEstudioEncuestadoComponent implements OnInit {

  idE: number = 0;
  idR: number = 0;
  estudios: Estudio[] = [];
  constructor(private estudio: EstudioService) { }

  ngOnInit(): void {
    this.idE = 4;
    this.idR = 4;
  }


  busquedaEstudios() {
    if (this.idR === 4) {
    this.estudio.getEstudios(this.idE).subscribe(
      (estudios: Estudio[]) => {
        this.estudios = estudios;
      }
    );
  }
}
}
