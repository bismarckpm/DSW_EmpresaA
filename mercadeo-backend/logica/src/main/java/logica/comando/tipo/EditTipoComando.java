package logica.comando.tipo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Tipo;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.TipoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditTipoComando extends BaseComando {

    public long _id;
    public Tipo tipo;

    public EditTipoComando(long _id, Tipo Tipo) {
        this._id = _id;
        this.tipo = tipo;
    }

    @Override
    public void execute() {
        try{
            DaoTipo dao = Fabrica.crear(DaoTipo.class);
            Tipo resul = dao.update(tipo);
            this.tipo=resul;
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Tipo actualizado")
                .add("tipo_nombre",this.tipo.get_id()).build();

        return data;
    }

}
