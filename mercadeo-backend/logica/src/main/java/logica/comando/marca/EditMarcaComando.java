package logica.comando.marca;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.MarcaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditMarcaComando extends BaseComando {

    public long _id;
    public Marca marca;

    public EditMarcaComando(long _id, Marca marca) {
        this._id = _id;
        this.marca = marca;
    }

    @Override
    public void execute() {
        try{
            DaoMarca dao = Fabrica.crear(DaoMarca.class);
            Marca resul = dao.update(this.marca);
            this.marca=resul;
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Marca actualizada")
                .add("marca_nombre",this.marca.get_id()).build();

        return data;
    }

}
