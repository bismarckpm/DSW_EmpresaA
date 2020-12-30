import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { User } from 'src/app/interfaces/user';
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

  // Usuarios
  public identity: any;
  public user: User;

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
  this.estudioService.getEstudiosAnalista(1).subscribe(
    (estudios) => {
      this.estudios = estudios;
      this.isWait = false;
    }
  );
}

    
// Encuestados que han respondido
getMuestra(): void {
  this.isWait = true;
  this.muestraService.getMuestra(1).subscribe(data => {
    this.encuestadosR =data;
    this.dataSource = new MatTableDataSource<any>(this.encuestadosR);
    this.isWait = false;

  })
}


// Encuestados sin responder
getEncuestadosSinR(): void {
  this.estudioService.getEncuestadosSinResolver(1).subscribe(data => { 
    this.encuestados = data;
    this.dataSourceR = new MatTableDataSource<any>(this.encuestados);

  })
}

} 
