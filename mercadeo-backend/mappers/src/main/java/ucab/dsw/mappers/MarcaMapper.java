package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Marca;
import ucab.dsw.excepciones.PruebaExcepcion;

public class MarcaMapper {
    public static Marca mapDtoToEntityInsert(MarcaDto marcaDto )
    {
        Marca marca = new Marca();

        marca.set_nombre( marcaDto.getNombre() );
        marca.set_estado( marcaDto.getEstado() );

        return marca;
    }

    public static Marca mapDtoToEntityUpdate(long _id,MarcaDto marcaDto )
    {
        DaoMarca daoMarca=new DaoMarca();

        Marca marca = daoMarca.find(_id,Marca.class);

        marca.set_nombre( marcaDto.getNombre() );
        marca.set_estado( marcaDto.getEstado() );

        return marca;
    }

    public static MarcaDto mapEntityToDto(  Marca marca ) throws PruebaExcepcion {
        MarcaDto marcaDto = new MarcaDto();

        marcaDto.setId(marca.get_id());
        marcaDto.setNombre( marca.get_nombre() );
        marcaDto.setEstado( marca.get_estado() );
        return marcaDto;
    }
}
