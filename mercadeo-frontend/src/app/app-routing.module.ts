import { Route } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './components/login/login.component';
import { ErrorComponent } from './components/error/error.component';

import { ConsultarEncuestadosEstudioComponent } from './components/consultar-encuestados-estudio/consultar-encuestados-estudio.component';
import { DatoUsuarioComponent } from './components/dato-usuario/dato-usuario.component';
import { DatosAdicionalesComponent } from './components/datos-adicionales/datos-adicionales.component';


import { RespuestapreguntaComponent } from './components/respuestapregunta/respuestapregunta.component';
import { ConsultarespuestaComponent } from './components/cliente/cliente_consulta_estudio/consultarespuesta/consultarespuesta.component';
import { RecuperarpasswordComponent } from './components/recuperarpassword/recuperarpassword.component';


import { ModificarUsuarioComponent } from './components/modificar-usuario/modificar-usuario.component';


// ADMIN
import { Sidebar2Component } from './components/landing_page/sidebar2.component';

import { CategoriaComponent } from './components/admin/admin_categoria/categoria/categoria.component';
import { AddcategoriaComponent } from './components/admin/admin_categoria/addcategoria/addcategoria.component';
import { CreateMarcaComponent } from './components/admin/admin_marca/create-marca/create-marca.component';
import { CreatePresentacionComponent } from './components/admin/admin_presentacion/create-presentacion/create-presentacion.component';
import { CreateSubcategoriaComponent } from './components/admin/admin_subcategoria/create-subcategoria/create-subcategoria.component';
import { CreateTipoComponent } from './components/admin/admin_tipo/create-tipo/create-tipo.component';
import { DashboardproductoComponent } from './components/admin/admin_producto/dashboardproducto/dashboardproducto.component';
import { MarcaComponent } from './components/admin/admin_marca/marca/marca.component';
import { PresentacionComponent } from './components/admin/admin_presentacion/presentacion/presentacion.component';
import { SubcategoriaComponent } from './components/admin/admin_subcategoria/subcategoria/subcategoria.component';
import { TipoComponent } from './components/admin/admin_tipo/tipo/tipo.component';

import { ConsultarEstudiosComponent } from './components/admin/admin_estudio/consultar-estudios/consultar-estudios.component';
import { ModificarEstudioComponent } from './components/admin/admin_estudio/modificar-estudio/modificar-estudio.component';
import { CrearEstudioComponent } from './components/admin/admin_estudio/crear-estudio/crear-estudio.component';

import { AsignarPreguntasEstudioComponent } from './components/admin/admin_pregunta/asignar-preguntas-estudio/asignar-preguntas-estudio.component';
import { ConsultaPreguntaComponent } from './components/admin/admin_pregunta/consulta-pregunta/consulta-pregunta.component';
import { RegistraPreguntaComponent } from './components/admin/admin_pregunta/registra-pregunta/registra-pregunta.component';

import { CrearUsuarioComponent } from './components/admin/admin_usuario/crear-usuario/crear-usuario.component';
import { ConsultarUsuarioComponent } from './components/admin/admin_usuario/consultar-usuario/consultar-usuario.component';
import { PreguntasGeneralesComponent } from './components/preguntas-generales/preguntas-generales.component';
import { PreguntasRecomendadasComponent } from './components/preguntas-recomendadas/preguntas-recomendadas.component';

// CLIENTE
import { DetalleProductoComponent } from './components/cliente/cliente_producto/detalle-producto/detalle-producto.component';
import { CreateProductoComponent } from './components/cliente/cliente_producto/create-producto/create-producto.component';

import { VistaestudiosComponent } from './components/cliente/cliente_consulta_estudio/vistaestudios/vistaestudios.component';
import { ResultadoestudioComponent } from './components/cliente/cliente_consulta_estudio/resultadoestudio/resultadoestudio.component';

import { VistasolicitudComponent } from './components/cliente/cliente_solicitud_estudio/solicitud_estudio/vistasolicitud/vistasolicitud.component';
import { EditasolicitudComponent } from './components/cliente/cliente_solicitud_estudio/solicitud_estudio/editasolicitud/editasolicitud.component';
import { RegistrarsolicitudComponent } from './components/cliente/cliente_solicitud_estudio/solicitud_estudio/registrarsolicitud/registrarsolicitud.component';


// ANALISTA
import { ConsultarEstudioAnalistaComponent } from './components/analista/analista_estudio_asignado/consultar-estudio-analista/consultar-estudio-analista.component';
import { ConsultaMuestraEstudioComponent } from './components/analista/analista_muestra/consulta-muestra-estudio/consulta-muestra-estudio.component';

// ENCUESTADO
import { ContestarEncuestaComponent } from './components/encuestado/encuestado_encuesta/contestar-encuesta/contestar-encuesta.component';
import { ConsultarEstudioEncuestadoComponent } from './components/encuestado/encuestado_estudio/consultar-estudio-encuestado/consultar-estudio-encuestado.component';
import { DesarrollarEntrevistaComponent } from './components/analista/analista_entrevista/desarrollar-entrevista/desarrollar-entrevista.component';
import { AnalistaPageComponent } from './components/analista/analista-page/analista-page.component';
import { ConsultaMuestraSinResponderComponent } from './components/analista/analista_muestra/consulta-muestra-sin-responder/consulta-muestra-sin-responder/consulta-muestra-sin-responder.component';
import { EditarEncuestadoComponent } from './components/editar-encuestado/editar-encuestado.component';


import { UserprofileComponent } from './components/userprofile/userprofile.component';
import { AnalistaencuestadoComponent } from './components/analista/analista_entrevista/analistaencuestado/analistaencuestado.component';

const routes: Routes = [

{path: 'userProfile', component: UserprofileComponent},

// ADMIN
{path:  'admin', component: Sidebar2Component },

{ path: 'categorias', component: CategoriaComponent },
{ path: 'categorias/create', component: AddcategoriaComponent },
{ path: 'subcategoria', component: SubcategoriaComponent },
{ path: 'subcategoria/create', component: CreateSubcategoriaComponent },
{ path: 'marca', component: MarcaComponent },
{ path: 'marca/create', component: CreateMarcaComponent },
{ path: 'tipo', component: TipoComponent },
{ path: 'tipo/create', component: CreateTipoComponent },
{ path: 'presentacion', component: PresentacionComponent },
{ path: 'presentacion/create', component: CreatePresentacionComponent },
{ path: 'producto', component: DashboardproductoComponent },

{ path: 'consultarestudios', component: ConsultarEstudiosComponent},
{ path: 'crearestudio', component: CrearEstudioComponent},
{ path: 'modificarestudio/:idEst', component: ModificarEstudioComponent},

{ path: 'asignarpreguntasaestudio/:idEstudio', component: AsignarPreguntasEstudioComponent},
{ path: 'listadoPregunta',component: ConsultaPreguntaComponent },
{ path: 'registraPregunta', component: RegistraPreguntaComponent},

{ path: 'consultarpersona', component: ConsultarUsuarioComponent},
{ path: 'crearusuario/:fk_datoUsuario', component: CrearUsuarioComponent},
{ path: 'preguntasgenerales/:idEstudio', component: PreguntasGeneralesComponent},
{ path: 'preguntasrecomendadas/:idEstudio', component: PreguntasRecomendadasComponent},

// CLIENTE
{ path: 'producto/create', component: CreateProductoComponent },
{ path: 'producto/detalle/:id', component: DetalleProductoComponent },

{ path:  'vistaEstudios', component: VistaestudiosComponent},
{ path:  'resultadosEstudio', component: ResultadoestudioComponent},

{ path:  'vistaSolicitud', component: VistasolicitudComponent},
{ path:  'editaSolicitud', component: EditasolicitudComponent},
{ path:  'registrarSolicitudEstudio', component: RegistrarsolicitudComponent },


// ANALISTA
{ path: 'analista', component: AnalistaPageComponent },
{ path: 'consultarestudioanalista', component: ConsultarEstudioAnalistaComponent},
{ path: 'entrevista', component: DesarrollarEntrevistaComponent},
{ path: 'muestra/:id', component: ConsultaMuestraEstudioComponent },
{ path: 'encuestados', component: ConsultaMuestraSinResponderComponent },
{ path: 'responderEncuesta/:idEstudio/:idUser', component: AnalistaencuestadoComponent },

// ENCUESTADO

{ path: 'contestarencuesta/:idEstudio', component: ContestarEncuestaComponent},
{ path: 'consultarestudioencuestado', component: ConsultarEstudioEncuestadoComponent },
{ path: 'editarencuestado/:idUsuario/:fkDatoUsuario', component: EditarEncuestadoComponent },

// OTROS

/* { path: '**', component: ErrorComponent}, */

{ path:  'login',component: LoginComponent},
{ path:  'logout/:sure' , component: LoginComponent},
{ path:  'recuperarContraseña', component: RecuperarpasswordComponent},

{ path: 'datousuario', component: DatoUsuarioComponent},
{ path: 'modificarpersona/:id', component: ModificarUsuarioComponent},
{ path: 'datosadicionales/:hijos/:phones/:id', component: DatosAdicionalesComponent},
{ path: 'consultarencuestadoestudio', component: ConsultarEncuestadosEstudioComponent},


{ path:  'respuestaPregunta', component: RespuestapreguntaComponent},
{ path:  'consultaRespuesta', component: ConsultarespuestaComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


export const appRoutingProviders: any[] = [];
export const routing: ModuleWithProviders<Route> = RouterModule.forRoot(routes);
