package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.TipoDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Tipo;
import ucab.dsw.excepciones.PruebaExcepcion;

public class TipoMapper {

    public static Tipo mapDtoToEntityInsert(TipoDto tipoDto )
    {
        Tipo tipo = new Tipo();

        tipo.set_nombre( tipoDto.getNombre() );
        tipo.set_descripcion( tipoDto.getDescripcion() );
        tipo.set_estado( tipoDto.getEstado() );

        return tipo;
    }

    public static Tipo mapDtoToEntityUpdate(long _id,TipoDto tipoDto )
    {
        DaoTipo daoTipo=new DaoTipo();

        Tipo tipo = daoTipo.find(_id,Tipo.class);

        tipo.set_nombre( tipoDto.getNombre() );
        tipo.set_descripcion( tipoDto.getDescripcion() );
        tipo.set_estado( tipoDto.getEstado() );

        return tipo;
    }

    public static TipoDto mapEntityToDto(  Tipo tipo ) throws PruebaExcepcion {
        TipoDto tipoDto = new TipoDto();

        DaoCategoria dao = new DaoCategoria();
        tipoDto.setId(tipo.get_id());
        tipoDto.setNombre( tipo.get_nombre() );
        tipoDto.setDescripcion( tipo.get_descripcion() );
        tipoDto.setEstado( tipo.get_estado() );

        return tipoDto;
    }
    
}
