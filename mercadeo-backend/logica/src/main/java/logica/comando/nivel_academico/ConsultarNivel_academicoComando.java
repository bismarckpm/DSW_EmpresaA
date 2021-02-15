package logica.comando.nivel_academico;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoNivel_academico;
import ucab.dsw.dtos.Nivel_academicoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Nivel_academico;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.NivelAcademicoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarNivel_academicoComando extends BaseComando {

    public Nivel_academico nivel_academico;
    public long _id;

    public ConsultarNivel_academicoComando(long _id){
        this._id=_id;
    }

    /**
     * Este comando ejecuta la consulta de un nivel_académico
     */
    @Override
    public void execute() throws CustomException{
        try{
            DaoNivel_academico dao = new DaoNivel_academico();
            this.nivel_academico = dao.find(_id, Nivel_academico.class);

        }catch ( CustomException ex ) {
            throw ex;
        }catch ( Exception ex )
        {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Nivel academico consultado");
        data.setObjeto(this.nivel_academico);

        return data;
    }
}
