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
import { Dato_Usuario } from './models/dato_usuario';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


/* import { ConsultarUsuarioComponent } from './components/consultar-usuario/consultar-usuario.component';
import { ModificarUsuarioComponent } from './components/modificar-usuario/modificar-usuario.component'; */
import { ErrorComponent } from './components/error/error.component';
import { ConsultarUsuarioComponent } from './components/consultar-usuario/consultar-usuario.component';
import { ModificarUsuarioComponent } from './components/modificar-usuario/modificar-usuario.component';
import { ModificarEstudioComponent } from './components/modificar-estudio/modificar-estudio.component';



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
  {path: '**', component: ErrorComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
