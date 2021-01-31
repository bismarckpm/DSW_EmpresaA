package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoNivel_economico;
import ucab.dsw.dtos.Nivel_economicoDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Nivel_economico;
import ucab.dsw.excepciones.PruebaExcepcion;

public class NivelEconomicoMapper {

    public static Nivel_economico mapDtoToEntityInsert(Nivel_economicoDto nivel_economicoDto )
    {
        Nivel_economico nivel_economico = new Nivel_economico();

        nivel_economico.set_nivel( nivel_economicoDto.getNivel() );
        nivel_economico.set_estado( nivel_economicoDto.getEstado() );

        return nivel_economico;
    }

    public static Nivel_economico mapDtoToEntityUpdate(long _id,Nivel_economicoDto nivel_economicoDto )
    {
        DaoNivel_economico daoNivel_economico=new DaoNivel_economico();

        Nivel_economico nivel_economico = daoNivel_economico.find(_id,Nivel_economico.class);

        nivel_economico.set_nivel( nivel_economicoDto.getNivel() );
        nivel_economico.set_estado( nivel_economicoDto.getEstado() );

        return nivel_economico;
    }

    public static Nivel_economicoDto mapEntityToDto(  Nivel_economico nivel_economico ) throws PruebaExcepcion {
        Nivel_economicoDto nivel_economicoDto = new Nivel_economicoDto();


        nivel_economicoDto.setId(nivel_economico.get_id());
        nivel_economicoDto.setNivel( nivel_economico.get_nivel() );
        nivel_economicoDto.setEstado( nivel_economico.get_estado() );
        return nivel_economicoDto;
    }
}
