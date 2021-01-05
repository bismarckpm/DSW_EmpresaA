import { trigger, state, style, transition, animate } from '@angular/animations';
import { AfterViewInit, Component, OnInit, ViewChild, Input } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { EstudioService } from 'src/app/services/estudio.service';
import { MuestraAnalistaService } from 'src/app/services/muestra-analista.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-consulta-muestra-estudio',
  templateUrl: './consulta-muestra-estudio.component.html',
  styleUrls: ['./consulta-muestra-estudio.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ConsultaMuestraEstudioComponent implements OnInit, AfterViewInit {

  // Obtener ID Estudio

  idEstudio: any;

  // Estados
  isWait = false;

  // Varialbes
  encuestados: any[] = []
  public age: number = 0;
  // ID
  id = +this.route.snapshot.paramMap.get('id')!;

  // Tabla
  columnsToDisplay: string[] = ['ID', 'Encuestado', 'Correo', 'Rol', 'Opciones'];
  dataSource!: MatTableDataSource<any>;
  expandedElement: any | null;

  @ViewChild(MatSort, { static: false })
  sort: MatSort = new MatSort;

  @ViewChild(MatPaginator, { static: false })
  paginator!: MatPaginator;


  constructor(
    private estudioService: EstudioService,
    private route: ActivatedRoute,
    private location: Location,
    ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(
      response =>
      {
        this.idEstudio = response;
        console.log(this.idEstudio);
      });

    this.getMuestra();
  }

  ngAfterViewInit() {

  }

  // Suscribe la data en la tabla 
  getMuestra(): void {
    this.isWait = true;
    this.estudioService.getPoblacion(this.idEstudio.solicitud).subscribe(data => {
      this.encuestados =data;

      console.log( 'id',this.idEstudio.solicitud,'ENCUESTADOOS',  this.encuestados, 'estudio', this.idEstudio.estudio)
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


  goBack(): void {
    this.location.back();
  }

  
}
