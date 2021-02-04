package logica.comando.nivel_economico;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoNivel_economico;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Nivel_economico;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.NivelEconomicoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditNivel_economicoComando extends BaseComando {

    public long _id;
    public Nivel_economico nivel_economico;

    public EditNivel_economicoComando(long _id, Nivel_economico nivel_economico) {
        this._id = _id;
        this.nivel_economico = nivel_economico;
    }

    @Override
    public void execute() {
        try{
            DaoNivel_economico dao = Fabrica.crear(DaoNivel_economico.class);
            Nivel_economico resul = dao.update( this.nivel_economico);
            this.nivel_economico=resul;
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Nivel_economico actualizado")
                .add("nivel_economico_nombre",this.nivel_economico.get_id()).build();

        return data;
    }

}
