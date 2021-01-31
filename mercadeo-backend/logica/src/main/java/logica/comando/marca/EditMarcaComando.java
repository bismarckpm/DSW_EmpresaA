package logica.comando.marca;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.MarcaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditMarcaComando extends BaseComando {

    public long _id;
    public MarcaDto marcaDto;

    public EditMarcaComando(long _id, MarcaDto marcaDto) {
        this._id = _id;
        this.marcaDto = marcaDto;
    }

    @Override
    public void execute() {
        try{
            DaoMarca dao = Fabrica.crear(DaoMarca.class);
            Marca marca= MarcaMapper.mapDtoToEntityUpdate(_id,marcaDto);
            Marca resul = dao.update(marca);
            this.marcaDto=MarcaMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Marca actualizada")
                .add("marca_nombre",this.marcaDto.getNombre()).build();

        return data;
    }

}
