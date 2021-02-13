package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Respuesta;

import java.util.List;

public class ValidarContestadoComando extends BaseComando {

    public List<Respuesta> respuestas = null;
    public boolean aux;
    public long id;

    public ValidarContestadoComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() {

        try{
            DaoEstudio dao= Fabrica.crear(DaoEstudio.class);
            respuestas = dao.validarContestado(id);
            if (respuestas.isEmpty()){
                System.out.println("No han participado en el estudio");
                aux = false;
            }
            else{
                System.out.println("Si participaron en el estudio");
                aux = true;
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
        data.setMensaje("Cargando Validacion");
        data.setObjeto(this.aux);

        return data;
    }
}
