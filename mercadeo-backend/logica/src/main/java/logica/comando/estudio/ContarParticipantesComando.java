package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;

import java.util.List;

public class ContarParticipantesComando extends BaseComando {

    public Long participantes;
    public long id;

    public ContarParticipantesComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() {

        try{
            DaoEstudio dao= Fabrica.crear(DaoEstudio.class);
            participantes = dao.contarParticipantes(id);
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }


    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Cargando la cuenta de participantes");
        data.setObjeto(this.participantes);

        return data;
    }
}
