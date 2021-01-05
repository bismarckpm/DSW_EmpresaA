import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { EstudioclienteService } from 'src/app/services/estudiocliente.service';

@Component({
  selector: 'app-recomendar-estudios',
  templateUrl: './recomendar-estudios.component.html',
  styleUrls: ['./recomendar-estudios.component.css']
})
export class RecomendarEstudiosComponent implements OnInit {

  // Obtener ID Estudio
  estudios: any[] = [];
  estudio: any;

  // Estados
  isWait = false;


  // Tabla
  columnsToDisplay: string[] = ['ID', 'Nombre', 'Estatus', 'Fecha', 'Opciones'];
  dataSource!: MatTableDataSource<any>;
  @ViewChild(MatSort, { static: false })
  sort: MatSort = new MatSort;

  @ViewChild(MatPaginator, { static: false })
  paginator!: MatPaginator;

  id: any = 0; /* any = 3; */

  constructor(
    private estudioService: EstudioclienteService,
    private route: ActivatedRoute,
  ) { }

  //  NOTA PARA ABEL, AQUI RECIBO POR QUERY PARAMS EL ID DE LA SOLICITUD
  // FAVOR DESCOMENTAR Y PASARLE ESE ID
  ngOnInit(): void {
      /* this.route.queryParams.subscribe(
      response =>
      {
        this.id = response;
        console.log(this.id);
      }); */
    this.id = this.route.snapshot.params['idSolicitud'];
    this.getEstudiosRecomendado(this.id);

    console.log(this.id);
  }

  getEstudiosRecomendado(id: number): void {

    this.isWait = true;
    this.estudioService.getEstudioRecomendados(id).subscribe( (data) =>{
      this.estudios = data;
      console.log('Estudios= ' , this.estudios)

      this.dataSource = new MatTableDataSource<any>(this.estudios);
      this.isWait = false;

      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
  })
  }

  getEstudio(idEstu : number) : void {
    this.estudioService.getEstudioEspecifico(idEstu).subscribe(data => {
       this.estudio = data;
       const est: any = {
         id: this.estudio._id,
         nombre: this.estudio._nombre,
         estado: this.estudio._estado,
         estatus: this.estudio._estatus,
         fechaInicio: this.estudio._fechaInicio,
         fechaFin: this.estudio._fechaFin,
         solicitudEstudio: this.estudio._solicitudEstudio._id,
         usuario: this.estudio._usuario._id

       }
       console.log(est);

       console.log(this.estudio);
       this.estudioService.createEstudioRecomendado(this.id, est);


    });

  }

}


