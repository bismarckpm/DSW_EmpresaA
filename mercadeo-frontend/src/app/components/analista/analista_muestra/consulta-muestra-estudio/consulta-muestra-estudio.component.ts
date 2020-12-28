import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MuestraAnalistaService } from 'src/app/services/muestra-analista.service';

@Component({
  selector: 'app-consulta-muestra-estudio',
  templateUrl: './consulta-muestra-estudio.component.html',
  styleUrls: ['./consulta-muestra-estudio.component.css']
})
export class ConsultaMuestraEstudioComponent implements OnInit, AfterViewInit {

  // Estados
  isWait = false;

  // Varialbes
  encuestados: any[] = []

  // Tabla
  displayedColumns: string[] = ['Encuestado', 'Estudio', 'Fecha Inicio', 'Fecha Fin', 'Opciones'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatSort, { static: false })
  sort: MatSort = new MatSort;

  @ViewChild(MatPaginator, { static: false })
  paginator!: MatPaginator;


  constructor(
    private muestraService: MuestraAnalistaService,
    ) { }

  ngOnInit(): void {
    this.getMuestra();
  }

  ngAfterViewInit() {

  }

  // Suscribe la data en la tabla 
  getMuestra(): void {
    this.isWait = true;
    this.muestraService.getMuestra(4).subscribe(data => {
      this.encuestados =data;
      this.dataSource = new MatTableDataSource<any>(this.encuestados);
      this.isWait = false;

 
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
 

    })
  }

  // Filtro
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    console.log('FILTER', filterValue)
  }

}
