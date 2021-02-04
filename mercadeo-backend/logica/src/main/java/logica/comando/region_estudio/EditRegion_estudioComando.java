package logica.comando.region_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRegion_estudio;
import ucab.dsw.entidades.Region_estudio;

import javax.json.Json;
import javax.json.JsonObject;

public class EditRegion_estudioComando extends BaseComando {

    public long _id;
    public Region_estudio region_estudio;

    public EditRegion_estudioComando(long _id, Region_estudio region_estudio) {
        this._id = _id;
        this.region_estudio = region_estudio;
    }

    @Override
    public void execute() {
        try{
            DaoRegion_estudio dao = Fabrica.crear(DaoRegion_estudio.class);
            Region_estudio resul = dao.update(this.region_estudio);
            this.region_estudio=resul;
        }catch (Exception ex) {
            ex.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Region_estudio actualizada")
                .add("region_estudio_nombre",this.region_estudio.get_id()).build();

        return data;
    }

}
