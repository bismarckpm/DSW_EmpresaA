import { Component, ElementRef, HostListener, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import { GetEstudio, GetEstudioEncuestado } from 'src/app/interfaces/estudio';
import { User } from 'src/app/interfaces/user';
import { EncuestadoServicioService } from 'src/app/services/encuestado-servicio.service';
import { EstudioService } from 'src/app/services/estudio.service';
import { EstudioclienteService } from 'src/app/services/estudiocliente.service';
import { LoginService } from 'src/app/services/login.service';
import { PreguntaEncuestaServiceService } from 'src/app/services/pregunta-encuesta-service.service';
import { ProductoService } from 'src/app/services/producto.service';
import { SolicitudestudioService } from 'src/app/services/solicitudestudio.service';

@Component({
  selector: 'app-home-encuestado',
  templateUrl: './home-encuestado.component.html',
  styleUrls: ['./home-encuestado.component.css'],
  providers: [NgbCarouselConfig]
})
export class HomeEncuestadoComponent implements OnInit {

  // VARIABLES
  menuPosition: any;

  @HostListener('window:scroll', ['$event'])
    handleScroll(){
        const windowScroll = window.pageYOffset;
        if(windowScroll >= this.menuPosition){
            this.sticky = true;
        } else {
            this.sticky = false;
      }
  }

  @ViewChild('stickyMenu') menuElement!: ElementRef;

  // images = [700, 533, 807, 124].map((n) => `https://picsum.photos/id/${n}/900/500`);
  // Es una pagina que posee imagenes, en esta genera aleatoriamente una imagen
  // Existen mejores formas de colocarlos, pero en este momento no se como aun
  images= [`https://picsum.photos/1200/500?random=1`,`https://picsum.photos/1200/500?random=2`,`https://picsum.photos/1200/500?random=3`,
  `https://picsum.photos/1200/500?random=4`,`https://picsum.photos/1200/500?random=5`,`https://picsum.photos/1200/500?random=6`,
  `https://picsum.photos/1200/500?random=7`,`https://picsum.photos/1200/500?random=8`,`https://picsum.photos/1200/500?random=9`,
  `https://picsum.photos/1200/500?random=10`,`https://picsum.photos/1200/500?random=11`]

  // Estados
  isWait: boolean = false;
  sticky: boolean = false;
  isEmpty = false;
  isEmpty2 = false;
  estado = '';

  // Iconos
  icono = '';
  icono2 = '';

  // Usuarios
  public identity: any;
  isUser = false;
  public user: User = {
    id:0,
    nombreUsuario:'',
    correo:'',
    estado:'',
    idRol:0
  };

  // Encuestas
  disponibilidad: any;
  encuestaRespondida: any;
  cantidad: any;
  estudios: GetEstudio[] = [];
  amount: any;



  constructor(
    private _loginService: LoginService,
    private _solicitudService: SolicitudestudioService,
    private _estudioService: EstudioclienteService,
    private _productoService: ProductoService,
    private _datoService: EncuestadoServicioService,
    private _pe: PreguntaEncuestaServiceService,
    private estudio: EstudioService,
    private navegacion: Router,
    config: NgbCarouselConfig,
  ) {
    config.interval = 10000;
    config.keyboard = false;
    config.pauseOnHover = false;
    config.showNavigationIndicators = false;
  }

  ngOnInit(): void {
    this.getUser();
    this.estudiosRespondidos();
    this.busquedaEstudios();
    this.buscarDisponibilidad();
  }

  ngAfterViewInit(){
    this.menuPosition = this.menuElement.nativeElement.offsetTop;
 }

  // METODOS

 estudiosRespondidos(){
  this.estudio.getEncuestaRespondida(this.user.id).subscribe(
    response => {
      this.encuestaRespondida = response;

      this.cantidad = this.encuestaRespondida.length;


        // Si esta vacio el array
        // isEmpty = true
      if (this.encuestaRespondida.length == 0) {
          this.isEmpty = true;
        } else {
          this.isEmpty = false;
        }

      console.log('respondidos',  this.encuestaRespondida);
    }, error => {
      console.log(<any>error);
    }
  )
}


// Estudios/Encuesta por Responder
// Solo ando usando esto para obtener la cantidad de estudios que falten

  busquedaEstudios() {
    this.estudio.getEstudios(this.user.id).subscribe(
      (estudios: GetEstudio[]) => {
        this.estudios = estudios;
        console.log('Estudios por responder', this.estudios);

        this.amount = this.estudios.length;

        for (let i = 0; i < this.estudios.length; i++){
          this.validarEncuesta(this.estudios[i]._id!);
        }
        // Si esta vacio el array
        // isEmpty2 = true
        if (this.estudios.length == 0) {
          this.isEmpty2 = true;
        } else {
          this.isEmpty2 = false;
        }

      },
      error => {
        console.log(<any>error)},

    );
  }


  validarEncuesta(idE: number) {
    console.log(idE);
    console.log(this.user.id);
    this._pe.validarPreguntas(idE, this.user.id).subscribe(
     response => {
       this.estado = response;
       console.log(this.estado);

       if (this.estado === 'En Espera') {
         this.icono = 'input';
         console.log(this.icono);
       }
       else if (this.estado === 'En Proceso'){
         this.icono = 'edit';
         console.log(this.icono);
       }
       else {
         this.icono2 = 'search';
         console.log(this.icono2);
       }
     }
   );



 }


  // Para navegar a las respuestas
  encuestaContestada(id: number){
    this.navegacion.navigate(['encuestarespondida', id, this.user.id]);
  }

  // Para contestar una encuesta disponible
  encuesta(id: number) {
    this.navegacion.navigate(['contestarencuesta', id, this.user.id]);
  }

  // Scrolling
  scroll(element: any) {
    element.scrollIntoView({behavior: "smooth", block: "start", inline: "nearest"})
}


  // Obtener el User
  getUser(): void {
    this.identity = JSON.parse(this._loginService.getIdentity());
    this.user = new User(
      this.identity.id,
      this.identity.nombreUsuario,
      this.identity.correo,
      this.identity.estado,
      this.identity.idRol )

    if (this.user) {
        this.isUser = true;
        console.log(this.user)
    }
}

buscarDisponibilidad() {
  this._datoService.getDatoUsuarioPorIdUsuario(this.user.id).subscribe(
    (response) => {
      this.disponibilidad = response;
      console.log(this.disponibilidad)

    }
  );
}


}
