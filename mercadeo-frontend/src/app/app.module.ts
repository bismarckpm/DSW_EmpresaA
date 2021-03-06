import { MedioComunicacionService } from './services/medio-comunicacion.service';
import { HijoServicioService } from './services/hijo-servicio.service';
import { TelefonoServicioService } from './services/telefono-servicio.service';
import { EstudioService } from './services/estudio.service';
import { RolServicioService } from './services/rol-servicio.service';


import { NivelEconomicoServicioService } from './services/nivel-economico-servicio.service';

import { OcupacionServicioService } from './services/ocupacion-servicio.service';

import { NivelAcademicoServicioService } from './services/nivel-academico-servicio.service';
import { UsuarioServicioService } from './services/usuario-servicio.service';

import { LugarServicioService } from './services/lugar-servicio.service';

import { SidebarComponent } from './components/sidebar/sidebar.component';

import { EncuestadoServicioService } from './services/encuestado-servicio.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { routing, appRoutingProviders } from './app-routing.module';
import { CommonModule, DatePipe } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClient, HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RouterModule } from '@angular/router';

// Components
import { AppComponent } from './app.component';
import { DashboardproductoComponent } from './components/admin/admin_producto/dashboardproducto/dashboardproducto.component';
import { CategoriaComponent } from './components/admin/admin_categoria/categoria/categoria.component';
import { SubcategoriaComponent } from './components/admin/admin_subcategoria/subcategoria/subcategoria.component';
import { DialogsubcategoriaComponent } from './components/admin/admin_subcategoria/dialogsubcategoria/dialogsubcategoria.component';
import { DialogcategoriaComponent } from './components/admin/admin_categoria/dialogcategoria/dialogcategoria.component';
import { MarcaComponent } from './components/admin/admin_marca/marca/marca.component';
import { DialogmarcaComponent } from './components/admin/admin_marca/dialogmarca/dialogmarca.component';
import { TipoComponent } from './components/admin/admin_tipo/tipo/tipo.component';
import { PresentacionComponent } from './components/admin/admin_presentacion/presentacion/presentacion.component';
import { DialogtipoComponent } from './components/admin/admin_tipo/dialogtipo/dialogtipo.component';
import { CreatePresentacionComponent } from './components/admin/admin_presentacion/create-presentacion/create-presentacion.component';
import { AddcategoriaComponent } from './components/admin/admin_categoria/addcategoria/addcategoria.component';
import { CreateSubcategoriaComponent } from './components/admin/admin_subcategoria/create-subcategoria/create-subcategoria.component';
import { CreateMarcaComponent } from './components/admin/admin_marca/create-marca/create-marca.component';
import { CreateTipoComponent } from './components/admin/admin_tipo/create-tipo/create-tipo.component';
import { LoginComponent } from './components/login/login.component';
import { RegistraPreguntaComponent } from './components/admin/admin_pregunta/registra-pregunta/registra-pregunta.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { ConsultaPreguntaComponent } from './components/admin/admin_pregunta/consulta-pregunta/consulta-pregunta.component';
import { DialogpresentacionComponent } from './components/admin/admin_presentacion/dialogpresentacion/dialogpresentacion.component';
import { CreateProductoComponent } from './components/cliente/cliente_producto/create-producto/create-producto.component';
import { DetalleProductoComponent } from './components/cliente/cliente_producto/detalle-producto/detalle-producto.component';
import { VistaestudiosComponent } from './components/cliente/cliente_consulta_estudio/vistaestudios/vistaestudios.component';
// import { DialogestudiocliComponent } from './components/dialog/dialogestudiocli/dialogestudiocli.component';
import { ResultadoestudioComponent } from './components/cliente/cliente_consulta_estudio/resultadoestudio/resultadoestudio.component';
import { RespuestapreguntaComponent } from './components/respuestapregunta/respuestapregunta.component';
import { ConsultarespuestaComponent } from './components/cliente/cliente_consulta_estudio/consultarespuesta/consultarespuesta.component';
import { DialogopcionComponent } from './components/dialog/dialogopcion/dialogopcion.component';
import { RecuperarpasswordComponent } from './components/recuperarpassword/recuperarpassword.component';
import { RegistrarsolicitudComponent } from './components/cliente/cliente_solicitud_estudio/solicitud_estudio/registrarsolicitud/registrarsolicitud.component';
import { VistasolicitudComponent } from './components/cliente/cliente_solicitud_estudio/solicitud_estudio/vistasolicitud/vistasolicitud.component';
import { EditasolicitudComponent } from './components/cliente/cliente_solicitud_estudio/solicitud_estudio/editasolicitud/editasolicitud.component';
import { ConsultaMuestraEstudioComponent } from './components/analista/analista_muestra/consulta-muestra-estudio/consulta-muestra-estudio.component';
import { AnalistaPageComponent } from './components/analista/analista-page/analista-page.component';

// Routing
import { AppRoutingModule } from './app-routing.module';
import { DatoUsuarioComponent } from './components/dato-usuario/dato-usuario.component';


import { FormsModule, ReactiveFormsModule } from '@angular/forms';


import { ErrorComponent } from './components/error/error.component';
import { ConsultarUsuarioComponent } from './components/admin/admin_usuario/consultar-usuario/consultar-usuario.component';
import { ModificarUsuarioComponent } from './components/modificar-usuario/modificar-usuario.component';
import { ConsultarEstudiosComponent } from './components/admin/admin_estudio/consultar-estudios/consultar-estudios.component';
import { ModificarEstudioComponent } from './components/admin/admin_estudio/modificar-estudio/modificar-estudio.component';
import { DialogconsultarestudioComponent } from './components/admin/admin_estudio/dialogconsultarestudio/dialogconsultarestudio.component';
import { CrearUsuarioComponent } from './components/admin/admin_usuario/crear-usuario/crear-usuario.component';
import { DatosAdicionalesComponent } from './components/datos-adicionales/datos-adicionales.component';
import { CrearEstudioComponent } from './components/admin/admin_estudio/crear-estudio/crear-estudio.component';
import { ConsultarEstudioAnalistaComponent } from './components/analista/analista_estudio_asignado/consultar-estudio-analista/consultar-estudio-analista.component';
import { ConsultarEstudioEncuestadoComponent } from './components/encuestado/encuestado_estudio/consultar-estudio-encuestado/consultar-estudio-encuestado.component';
import { DesarrollarEntrevistaComponent } from './components/analista/analista_entrevista/desarrollar-entrevista/desarrollar-entrevista.component';
import { ContestarEncuestaComponent } from './components/encuestado/encuestado_encuesta/contestar-encuesta/contestar-encuesta.component';
import { DialogMostrarUsuarioComponent } from './components/admin/admin_usuario/dialog-mostrar-usuario/dialog-mostrar-usuario.component';
import { ConsultarEncuestadosEstudioComponent } from './components/consultar-encuestados-estudio/consultar-encuestados-estudio.component';
import { AsignarPreguntasEstudioComponent } from './components/admin/admin_pregunta/asignar-preguntas-estudio/asignar-preguntas-estudio.component';
import { DialogProductoTipoPresentacionComponent } from './components/admin/admin_producto/dialog-producto-tipo-presentacion/dialog-producto-tipo-presentacion.component';
import { Sidebar2Component } from './components/landing_page/sidebar2.component';



// Material imports
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatDialogModule} from '@angular/material/dialog';
import {MatSelectModule} from '@angular/material/select';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatStepperModule} from '@angular/material/stepper';
import {MatListModule} from '@angular/material/list';
import {MatDividerModule} from '@angular/material/divider';
import {MatBadgeModule} from '@angular/material/badge';
import {MatNativeDateModule, MatRippleModule} from '@angular/material/core';
import {MatExpansionModule} from '@angular/material/expansion';
import { MatSliderModule } from '@angular/material/slider';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatMenuModule} from '@angular/material/menu';
import { PageComponent } from './components/page/page.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatRadioModule} from '@angular/material/radio';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatFormFieldModule} from '@angular/material/form-field';
import { HighchartsChartModule } from 'highcharts-angular';
import {MatTableModule} from '@angular/material/table';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import {MatTabsModule} from '@angular/material/tabs';
import { DialogAsignarPreguntaComponent } from './components/dialog-asignar-pregunta/dialog-asignar-pregunta.component';
import { PreguntasGeneralesComponent } from './components/preguntas-generales/preguntas-generales.component';
import { PreguntasRecomendadasComponent } from './components/preguntas-recomendadas/preguntas-recomendadas.component';
import { EditarEncuestadoComponent } from './components/editar-encuestado/editar-encuestado.component';
import { UserprofileComponent } from './components/userprofile/userprofile.component';
import { EncuestaRespondidaComponent } from './components/encuesta-respondida/encuesta-respondida.component';
// tslint:disable-next-line: max-line-length
import { DialogoGestionarPoblacionComponent } from './components/analista/dialogo-gestionar-poblacion/dialogo-gestionar-poblacion.component';
import { DialogEstatusComponent } from './components/analista/dialog-estatus/dialog-estatus.component';
import { AnalistaencuestadoComponent } from './components/analista/analista_entrevista/analistaencuestado/analistaencuestado.component';
import { ListaSolicitudesComponent } from './components/lista-solicitudes/lista-solicitudes.component';
import { RecomendarEstudiosComponent } from './components/recomendar-estudios/recomendar-estudios.component';
import { RecomendacionEstudiosComponent } from './components/recomendacion-estudios/recomendacion-estudios.component';
import { RegistrarusuarioComponent } from './components/encuestado/registrarusuario/registrarusuario.component';
import { DialogSolicitudesComponent } from './components/dialog-solicitudes/dialog-solicitudes.component';

import { DateTimeFormatPipe } from './pipes/date-time-format.pipe';
import { HomeClienteComponent } from './components/cliente/home-cliente/home-cliente.component';
import { HomeEncuestadoComponent } from './components/encuestado/home-encuestado/home-encuestado.component';
import { DialogConsultaSolicitudComponent } from './components/cliente/dialog-consulta-solicitud/dialog-consulta-solicitud.component';


// PIPES
import { Pipe, PipeTransform } from '@angular/core';

// Alertas
import { AlertComponent } from './components/alert/alert.component';
import { DialogPreviewestudioComponent } from './components/dialog-previewestudio/dialog-previewestudio.component';
import { DialogCrearestudiorecomendadoComponent } from './components/dialog-crearestudiorecomendado/dialog-crearestudiorecomendado.component';
import { DialogoGestionarUserComponent } from './components/analista/dialogo-gestionar-user/dialogo-gestionar-user.component';
import { DialogoPoblacionComponent } from './components/admin/admin_estudio/dialogo-poblacion/dialogo-poblacion.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistraPreguntaComponent,
    NavigationComponent,
    ConsultaPreguntaComponent,
    CategoriaComponent,
    DashboardproductoComponent,
    SubcategoriaComponent,
    AddcategoriaComponent,
    DialogcategoriaComponent,
    CreateSubcategoriaComponent,
    MarcaComponent,
    CreateMarcaComponent,
    DialogmarcaComponent,
    DialogsubcategoriaComponent,
    TipoComponent,
    PresentacionComponent,
    CreateTipoComponent,
    DialogtipoComponent,
    CreatePresentacionComponent,
    DialogpresentacionComponent,
    CreateProductoComponent,
    DetalleProductoComponent,
    DialogProductoTipoPresentacionComponent,
    DatoUsuarioComponent,
    ConsultarUsuarioComponent,
    ModificarUsuarioComponent,
    ErrorComponent,
    SidebarComponent,
    ConsultarEstudiosComponent,
    ModificarEstudioComponent,
    DialogconsultarestudioComponent,
    CrearUsuarioComponent,
    DatosAdicionalesComponent,
    CrearEstudioComponent,
    ConsultarEstudioAnalistaComponent,
    ConsultarEstudioEncuestadoComponent,
    DesarrollarEntrevistaComponent,
    ContestarEncuestaComponent,
    DialogMostrarUsuarioComponent,
    ConsultarEncuestadosEstudioComponent,
    AsignarPreguntasEstudioComponent,
    Sidebar2Component,
    VistaestudiosComponent,
    //DialogestudiocliComponent,
    ResultadoestudioComponent,
    RespuestapreguntaComponent,
    ConsultarespuestaComponent,
    DialogopcionComponent,
    RecuperarpasswordComponent,
    RegistrarsolicitudComponent,
    VistasolicitudComponent,
    EditasolicitudComponent,
    ConsultaMuestraEstudioComponent,
    PageComponent,
    AnalistaPageComponent,
    DialogAsignarPreguntaComponent,
    PreguntasGeneralesComponent,
    PreguntasRecomendadasComponent,
    EditarEncuestadoComponent,
    UserprofileComponent,
    EncuestaRespondidaComponent,
    DialogoGestionarPoblacionComponent,
    DialogEstatusComponent,
    AnalistaencuestadoComponent,
    ListaSolicitudesComponent,
    RecomendarEstudiosComponent,
    RecomendacionEstudiosComponent,
    RegistrarusuarioComponent,
    DialogSolicitudesComponent,
    DateTimeFormatPipe,
    HomeClienteComponent,
    HomeEncuestadoComponent,
    AlertComponent,
    DialogConsultaSolicitudComponent,
    DialogPreviewestudioComponent,
    DialogCrearestudiorecomendadoComponent,
    DialogoGestionarUserComponent,
    DialogoPoblacionComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgbModule,
    routing,
    CommonModule,

    // Material imports
    MatCardModule,
    MatIconModule,
    MatInputModule,
    MatDialogModule,
    MatSelectModule,
    MatProgressSpinnerModule,
    MatStepperModule,
    MatListModule,
    MatDividerModule,
    MatBadgeModule,
    MatRippleModule,
    MatSliderModule,
    MatButtonModule,
    MatExpansionModule,
    MatToolbarModule,
    MatSnackBarModule,
    MatTooltipModule,
    MatSidenavModule,
    MatMenuModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatRadioModule,
    MatCheckboxModule,
    MatFormFieldModule,
    HighchartsChartModule,
    MatTableModule,
    MatProgressBarModule,
    MatSortModule,
    MatPaginatorModule,
    MatTabsModule,

  ],
  exports:[],
  providers: [appRoutingProviders, DatePipe, EncuestadoServicioService, LugarServicioService,
              UsuarioServicioService, NivelAcademicoServicioService,
              OcupacionServicioService, NivelEconomicoServicioService,
              RolServicioService, EstudioService, TelefonoServicioService,
              HijoServicioService, MedioComunicacionService],

  bootstrap: [AppComponent]
})
export class AppModule { }
