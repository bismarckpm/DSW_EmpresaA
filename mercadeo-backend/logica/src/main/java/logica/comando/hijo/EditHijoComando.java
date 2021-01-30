package logica.comando.hijo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.HijoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditHijoComando extends BaseComando {

    public long _id;
    public HijoDto hijoDto;

    public EditHijoComando(long _id, HijoDto hijoDto) {
        this._id = _id;
        this.hijoDto = hijoDto;
    }

    @Override
    public void execute() {
        try{
            DaoHijo dao = Fabrica.crear(DaoHijo.class);
            Hijo hijo= HijoMapper.mapDtoToEntityUpdate(_id,hijoDto);
            Hijo resul = dao.update(hijo);
            this.hijoDto=HijoMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Hijo actualizado")
                .add("id_hijo",this.hijoDto.getId()).build();

        return data;
    }

}
