import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {Route} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';

//Imports de los componentes.

import { LoginComponent } from './components/login/login.component';
import { RegistraPreguntaComponent } from './components/pregunta/registra-pregunta/registra-pregunta.component';
import { ConsultaPreguntaComponent } from './components/pregunta/consulta-pregunta/consulta-pregunta.component';

//Rutas de la app
const routes: Routes = [
  {path: 'login',component: LoginComponent},
  {path: 'registraPregunta', component: RegistraPreguntaComponent},
  {path: 'listadoPregunta',component: ConsultaPreguntaComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


export const appRoutingProviders: any[] = [];
export const routing: ModuleWithProviders<Route> = RouterModule.forRoot(routes);