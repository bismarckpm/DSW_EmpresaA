package logica.comando.nivel_economico;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoNivel_economico;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Nivel_economico;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.NivelEconomicoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditNivel_economicoComando extends BaseComando {
    
    public Nivel_economico nivel_economico;

    public EditNivel_economicoComando(Nivel_economico nivel_economico) {
        this.nivel_economico = nivel_economico;
    }

    @Override
    public void execute() throws CustomException {
        try{
            DaoNivel_economico dao = Fabrica.crear(DaoNivel_economico.class);
            dao.update(this.nivel_economico);
        }catch ( CustomException ex ) {
            throw ex;
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Nivel_economico actualizado");
        data.setObjeto(this.nivel_economico.get_id());

        return data;
    }

}
