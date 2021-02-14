package logica.comando.poblacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPoblacion;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Poblacion;
import ucab.dsw.excepciones.CustomException;

public class EditPoblacionComando extends BaseComando {

    public Poblacion poblacion;

    public EditPoblacionComando(Poblacion poblacion) {
        this.poblacion = poblacion;
    }

    @Override
    public void execute() throws CustomException{
        try{
            DaoPoblacion dao = Fabrica.crear(DaoPoblacion.class);
            dao.update(this.poblacion);
        }catch ( CustomException ex ) {
            throw ex;
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Poblacion actualizada");
        data.setObjeto(this.poblacion);

        return data;
    }
}
