package logica.comando.nivel_economico;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoNivel_economico;
import ucab.dsw.dtos.Nivel_economicoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Nivel_economico;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.NivelEconomicoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarNivel_economicoComando extends BaseComando {

    public Nivel_economico nivel_economico;
    public long _id;

    public ConsultarNivel_economicoComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoNivel_economico dao = new DaoNivel_economico();
            this.nivel_economico = dao.find(_id, Nivel_economico.class);

        }catch ( Exception ex )
        {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Nivel economico consultado");
        data.setObjeto(this.nivel_economico);

        return data;
    }
}
