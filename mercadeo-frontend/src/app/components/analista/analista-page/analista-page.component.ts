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
  //public user: User;

  // Tabla
  displayedColumns: string[] = ['Encuestado', 'Estudio'];
  dataSource!: MatTableDataSource<any>;
  dataSourceR!: MatTableDataSource<any>;

  constructor(
    private estudioService: EstudioService,
    private muestraService: MuestraAnalistaService,
    ) { }


  ngOnInit(): void {
    this.busquedaEstudios()
  }

// Metodo para traer todos los estudios asignados al analista
busquedaEstudios() {
  this.isWait = true;
  this.estudioService.getEstudiosAnalista(7).subscribe(
    (estudios) => {
      this.estudios = estudios;
      this.isWait = false;
    }
  );
}

  

} 
