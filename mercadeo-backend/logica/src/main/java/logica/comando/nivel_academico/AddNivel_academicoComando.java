package logica.comando.nivel_academico;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoNivel_academico;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Nivel_academico;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.NivelAcademicoMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddNivel_academicoComando extends BaseComando {

    public Nivel_academico nivel_academico;

    public AddNivel_academicoComando(Nivel_academico nivel_academico) {
        this.nivel_academico = nivel_academico;
    }

    @Override
    public void execute() throws CustomException{

        try {
            DaoNivel_academico dao = Fabrica.crear(DaoNivel_academico.class);
            dao.insert( this.nivel_academico );
        } catch ( CustomException ex ) {
            throw ex;
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Nivel_academico Añadido");
        data.setObjeto(this.nivel_academico);

        return data;
    }

}
