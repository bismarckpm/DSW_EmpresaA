import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {Route} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';


//Import de componentes
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
import { LoginComponent } from './components/login/login.component';
import { RegistraPreguntaComponent } from './components/pregunta/registra-pregunta/registra-pregunta.component';
import { ConsultaPreguntaComponent } from './components/pregunta/consulta-pregunta/consulta-pregunta.component';
import { VistaestudiosComponent } from './components/estudio-cliente/vistaestudios/vistaestudios.component';
import { ResultadoestudioComponent } from './components/estudio-cliente/resultadoestudio/resultadoestudio.component';
import { RespuestapreguntaComponent } from './components/respuestapregunta/respuestapregunta.component';
import { ConsultarespuestaComponent } from './components/consultarespuesta/consultarespuesta.component';




//Rutas de la app
const routes: Routes = [
  { path: '', redirectTo: '/producto', pathMatch: 'full' },
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
  {path:  'login',component: LoginComponent},
  {path:  'registraPregunta', component: RegistraPreguntaComponent},
  {path:  'listadoPregunta',component: ConsultaPreguntaComponent },
  {path:  'vistaEstudios', component: VistaestudiosComponent},
  {path:  'resultadosEstudio', component: ResultadoestudioComponent},
  {path:  'respuestaPregunta', component: RespuestapreguntaComponent},
  {path:  'consultaRespuesta', component: ConsultarespuestaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


export const appRoutingProviders: any[] = [];
export const routing: ModuleWithProviders<Route> = RouterModule.forRoot(routes);