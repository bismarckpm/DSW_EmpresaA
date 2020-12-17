import { ContestarEncuestaComponent } from './components/contestar-encuesta/contestar-encuesta.component';
import { AsignarPreguntasEstudioComponent } from './components/asignar-preguntas-estudio/asignar-preguntas-estudio.component';
import { ConsultarEncuestadosEstudioComponent } from './components/consultar-encuestados-estudio/consultar-encuestados-estudio.component';
import { ConsultarEstudioEncuestadoComponent } from './components/consultar-estudio-encuestado/consultar-estudio-encuestado.component';
import { ConsultarEstudioAnalistaComponent } from './components/consultar-estudio-analista/consultar-estudio-analista.component';
import { CrearEstudioComponent } from './components/crear-estudio/crear-estudio.component';
import { DatosAdicionalesComponent } from './components/datos-adicionales/datos-adicionales.component';
import { CrearUsuarioComponent } from './components/crear-usuario/crear-usuario.component';
import { ConsultarEstudiosComponent } from './components/consultar-estudios/consultar-estudios.component';
import { DatoUsuarioComponent } from './components/dato-usuario/dato-usuario.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CategoriaComponent } from './components/categoria/categoria.component';
import { AddcategoriaComponent } from './components/create_components/addcategoria/addcategoria.component';
import { CreateMarcaComponent } from './components/create_components/create-marca/create-marca.component';
import { CreatePresentacionComponent } from './components/create_components/create-presentacion/create-presentacion.component';
import { CreateSubcategoriaComponent } from './components/create_components/create-subcategoria/create-subcategoria.component';
import { CreateTipoComponent } from './components/create_components/create-tipo/create-tipo.component';
import { DashboardproductoComponent } from './components/dashboardproducto/dashboardproducto.component';
import { MarcaComponent } from './components/marca/marca.component';
import { PresentacionComponent } from './components/presentacion/presentacion.component';
import { SubcategoriaComponent } from './components/subcategoria/subcategoria.component';
import { TipoComponent } from './components/tipo/tipo.component';
import {Route} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';
import { DetalleProductoComponent } from './components/producto/detalle-producto/detalle-producto.component';
import { VistaestudiosComponent } from './components/estudio-cliente/vistaestudios/vistaestudios.component';
import { ResultadoestudioComponent } from './components/estudio-cliente/resultadoestudio/resultadoestudio.component';
import { RespuestapreguntaComponent } from './components/respuestapregunta/respuestapregunta.component';
import { ConsultarespuestaComponent } from './components/consultarespuesta/consultarespuesta.component';
import { RecuperarpasswordComponent } from './components/recuperarpassword/recuperarpassword.component';
import { RegistrarsolicitudComponent } from './components/solicitud_estudio/registrarsolicitud/registrarsolicitud.component';

import { CreateProductoComponent } from './components/create_components/create-producto/create-producto.component';


import { ErrorComponent } from './components/error/error.component';
import { ConsultarUsuarioComponent } from './components/consultar-usuario/consultar-usuario.component';
import { ModificarUsuarioComponent } from './components/modificar-usuario/modificar-usuario.component';
import { ModificarEstudioComponent } from './components/modificar-estudio/modificar-estudio.component';
import { LoginComponent } from './components/login/login.component';
import { ConsultaPreguntaComponent } from './components/pregunta/consulta-pregunta/consulta-pregunta.component';
import { RegistraPreguntaComponent } from './components/pregunta/registra-pregunta/registra-pregunta.component';


const routes: Routes = [
  {path: '', component: DatoUsuarioComponent},
  {path: 'datousuario', component: DatoUsuarioComponent},
  {path: 'consultarpersona', component: ConsultarUsuarioComponent},
  {path: 'modificarpersona/:id/:fk_datoUsuarios', component: ModificarUsuarioComponent},
  {path: 'consultarestudios', component: ConsultarEstudiosComponent},
  {path: 'modificarestudio/:idEst', component: ModificarEstudioComponent},
  {path: 'crearusuario', component: CrearUsuarioComponent},
  {path: 'datosadicionales/:hijos/:phones/:id', component: DatosAdicionalesComponent},
  {path: 'crearestudio', component: CrearEstudioComponent},
  {path: 'consultarestudioanalista', component: ConsultarEstudioAnalistaComponent},
  {path: 'consultarestudioencuestado', component: ConsultarEstudioEncuestadoComponent},
  {path: 'consultarencuestadoestudio', component: ConsultarEncuestadosEstudioComponent},
  {path: 'asignarpreguntasaestudio', component: AsignarPreguntasEstudioComponent},
  {path: 'contestarencuesta', component: ContestarEncuestaComponent},
  //{path: '**', component: ErrorComponent},
  //{ path: '', redirectTo: '/', pathMatch: 'full' },
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
  { path: 'producto/create', component: CreateProductoComponent },
  { path: 'producto/detalle/:id', component: DetalleProductoComponent },
  {path:  'login',component: LoginComponent},
  {path:  'logout/:sure' , component: LoginComponent},
  {path:  'registraPregunta', component: RegistraPreguntaComponent},
  {path:  'listadoPregunta',component: ConsultaPreguntaComponent },
  {path:  'vistaEstudios', component: VistaestudiosComponent},
  {path:  'resultadosEstudio', component: ResultadoestudioComponent},
  {path:  'respuestaPregunta', component: RespuestapreguntaComponent},
  {path:  'consultaRespuesta', component: ConsultarespuestaComponent},
  {path:  'recuperarContraseña', component: RecuperarpasswordComponent},
  {path:  'registrarSolicitudEstudio', component: RegistrarsolicitudComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


export const appRoutingProviders: any[] = [];
export const routing: ModuleWithProviders<Route> = RouterModule.forRoot(routes);
