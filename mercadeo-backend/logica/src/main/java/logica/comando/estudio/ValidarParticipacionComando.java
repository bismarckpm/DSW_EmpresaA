package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.excepciones.CustomException;

import java.util.List;

public class ValidarParticipacionComando extends BaseComando {

    public List<Respuesta> respuestas = null;
    public boolean aux;
    public long id1;
    public long id2;

    public ValidarParticipacionComando(long id1, long id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    /**
     * Este comando ejecuta la validación de la participación de un encuestado en un estudio
     */
    @Override
    public void execute() throws CustomException{

        try{
            DaoEstudio dao= Fabrica.crear(DaoEstudio.class);
            respuestas = dao.validarParticipacion(id1, id2);
            if (respuestas.isEmpty()){
                System.out.println("No ha participado en el estudio");
                aux = false;
            }
            else{
                System.out.println("Si participó en el estudio");
                aux = true;
            }
        }catch ( CustomException ex ) {
            throw ex;
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
