package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.*;

import java.util.ArrayList;
import java.util.List;

public class ObtenerResultadosEncuestadoComando extends BaseComando {

    public List<Pregunta_estudio> preguntas_estudio = null;
    public List<PreguntaAux> preguntas_salida = new ArrayList<PreguntaAux>();
    public long id1;
    public long id2;

    public ObtenerResultadosEncuestadoComando(long id1, long id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    @Override
    public void execute() {

        try{
            DaoEstudio daoEstudio= Fabrica.crear(DaoEstudio.class);
            DaoPregunta_estudio daoPest = Fabrica.crear(DaoPregunta_estudio.class);
            preguntas_estudio = daoPest.getPreguntasEstudio(daoEstudio.find(id1, Estudio.class));

            for (Pregunta_estudio pregunta_estudio : preguntas_estudio) {
                PreguntaAux preguntaAux= new PreguntaAux();
                DaoPregunta_encuesta daoPenc = Fabrica.crear(DaoPregunta_encuesta.class);
                List<Pregunta_encuesta> pregunta_encuesta = daoPenc.getEnunciadoPregunta(pregunta_estudio);
                for (Pregunta_encuesta pregunta_encuestaAux : pregunta_encuesta) {
                    preguntaAux.set_enunciado(pregunta_estudio.get_pregunta());
                    preguntaAux.set_tipoPregunta(pregunta_encuestaAux.get_tipoPregunta());
                    preguntaAux.set_estado("A");
                }
                DaoRespuesta daoRespuesta = new DaoRespuesta();
                List<Respuesta> respuestas = daoRespuesta.getRespuestasDeEncuestado(pregunta_estudio, id2);
                List<RespuestaAux> lista_interna = new ArrayList<RespuestaAux>();
                for (Respuesta respuestaCiclo : respuestas) {
                    RespuestaAux respuestaAux = new RespuestaAux();
                    DaoRespuesta daoRespuestaCiclo = new DaoRespuesta();
                    if (preguntaAux.get_tipoPregunta().equals("Seleccion Simple"))
                        respuestaAux.set_descripcion(respuestaCiclo.get_respuestaSimple());
                    if (preguntaAux.get_tipoPregunta().equals("Seleccion Multiple"))
                        respuestaAux.set_descripcion(respuestaCiclo.get_respuestaMultiple());
                    if (preguntaAux.get_tipoPregunta().equals("Verdadero o Falso"))
                        respuestaAux.set_descripcion(respuestaCiclo.get_verdaderoFalso());
                    if (preguntaAux.get_tipoPregunta().equals("Escala"))
                        respuestaAux.set_descripcion(respuestaCiclo.get_escala());
                    if (preguntaAux.get_tipoPregunta().equals("Abierta"))
                        respuestaAux.set_descripcion(respuestaCiclo.get_respuestaAbierta());
                    respuestaAux.set_estado(respuestaCiclo.get_estado());
                    respuestaAux.set_valor(null);
                    lista_interna.add(respuestaAux);
                }
                preguntaAux.set_listaRespuestas(lista_interna);
                preguntas_salida.add(preguntaAux);
            }

        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }


    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Cargando resultados del encuestado");
        data.setObjeto(this.preguntas_salida);

        return data;
    }
}
