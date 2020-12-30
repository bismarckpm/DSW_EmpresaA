import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { EstudioService } from 'src/app/services/estudio.service';
import { MuestraAnalistaService } from 'src/app/services/muestra-analista.service';


@Component({
  selector: 'app-analista-page',
  templateUrl: './analista-page.component.html',
  styleUrls: ['./analista-page.component.css']
})
export class AnalistaPageComponent implements OnInit {

  // Estados
  isWait = false;

  // Variable Estudio
  estudios: any[] = [];
  encuestados: any[] = [];
  encuestadosR: any[] = []

  // Tabla
  displayedColumns: string[] = ['Encuestado', 'Estudio'];
  dataSource!: MatTableDataSource<any>;
  dataSourceR!: MatTableDataSource<any>;

  constructor(
    private estudioService: EstudioService,
    private muestraService: MuestraAnalistaService,
    ) { }


  ngOnInit(): void {
    this.busquedaEstudios();
    this.getMuestra();
    this.getEncuestadosSinR();
  }

// Metodo para traer todos los estudios asignados al analista
busquedaEstudios() {
  this.isWait = true;
  this.estudioService.getEstudiosAnalista(4).subscribe(
    (estudios) => {
      this.estudios = estudios;
      this.isWait = false;
    }
  );
}

    
// Encuestados que han respondido
getMuestra(): void {
  this.isWait = true;
  this.muestraService.getMuestra(4).subscribe(data => {
    this.encuestadosR =data;
    this.dataSource = new MatTableDataSource<any>(this.encuestadosR);
    this.isWait = false;

  })
}


// Encuestados sin responder
getEncuestadosSinR(): void {
  this.estudioService.getEncuestadosSinResolver(4).subscribe(data => { 
    this.encuestados = data;
    this.dataSourceR = new MatTableDataSource<any>(this.encuestados);

  })
}

} 
