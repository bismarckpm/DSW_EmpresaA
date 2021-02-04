package logica.comando.tipo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Tipo;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.TipoMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddTipoComando extends BaseComando {

    public Tipo tipo;

    public AddTipoComando(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public void execute() {

        try {
            DaoTipo dao = Fabrica.crear(DaoTipo.class);
            Tipo resul = dao.insert( this.tipo );
            this.tipo=resul;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Tipo añadido")
                .add("tipo_id",this.tipo.get_id()).build();

        return data;
    }

}
