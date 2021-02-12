package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Response.ListaEncuestasE;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ObtenerEstudiosRecomendadosComando extends BaseComando {

    public List<ListaEncuestasE> estudios = null;
    public long id;

    public ObtenerEstudiosRecomendadosComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() {

        try{
            DaoSolicitud_estudio dao= Fabrica.crear(DaoSolicitud_estudio.class);
            List<Object[]> Lista = dao.listarEstudiosRecomendados(id);

            estudios = new ArrayList<>(Lista.size());

            for (Object[] r : Lista) {
                estudios.add(new ListaEncuestasE((long)r[0], (String)r[1], (String)r[2], (Date)r[3] ));
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
        data.setMensaje("Cargando estudios recomendados");
        data.setObjeto(estudios);

        return data;
    }
}
