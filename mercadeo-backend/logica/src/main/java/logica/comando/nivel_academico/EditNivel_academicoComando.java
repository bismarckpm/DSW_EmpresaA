package logica.comando.nivel_academico;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoNivel_academico;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Nivel_academico;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.NivelAcademicoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditNivel_academicoComando extends BaseComando {
    
    public Nivel_academico nivel_academico;

    public EditNivel_academicoComando(Nivel_academico nivel_academico) {
        this.nivel_academico = nivel_academico;
    }

    @Override
    public void execute() throws CustomException {
        try{
            DaoNivel_academico dao = Fabrica.crear(DaoNivel_academico.class);
            dao.update(this.nivel_academico);
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
        data.setMensaje("Nivel_academico actualizado");
        data.setObjeto(this.nivel_academico.get_id());

        return data;
    }

}
