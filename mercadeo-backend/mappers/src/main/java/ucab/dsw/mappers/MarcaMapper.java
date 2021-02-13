package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Marca;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;

public class MarcaMapper {
    public static Marca mapDtoToEntityInsert(MarcaDto marcaDto )throws CustomException
    {
        Marca marca = new Marca();
        if (marcaDto.getNombre() == null || marcaDto.getNombre().equals(""))
            throw new CustomException("001", "El nombre de la marca no puede ser nulo ni vacío");
        if(marcaDto.getNombre().length() > 45)
            throw new CustomException("002", "El nombre de la categoría excede el máximo permitido");
        marca.set_nombre( marcaDto.getNombre() );
        marca.set_estado( marcaDto.getEstado() );

        return marca;
    }

    public static Marca mapDtoToEntityUpdate(long _id,MarcaDto marcaDto )throws CustomException
    {
        DaoMarca daoMarca=new DaoMarca();

        Marca marca = daoMarca.find(_id,Marca.class);
        if (marcaDto.getNombre() == null || marcaDto.getNombre().equals(""))
            throw new CustomException("001", "El nombre de la marca no puede ser nulo ni vacío");
        if(marcaDto.getNombre().length() > 45)
            throw new CustomException("002", "El nombre de la categoría excede el máximo permitido");
        marca.set_nombre( marcaDto.getNombre() );
        marca.set_estado( marcaDto.getEstado() );

        return marca;
    }

    public static MarcaDto mapEntityToDto(  Marca marca ) throws CustomException {
        MarcaDto marcaDto = new MarcaDto();
        if (marca == null)
            throw new CustomException("004", "La marca recibida es nula");
        if (marca.get_id() == 0 || marca.get_nombre()==""){
            throw new CustomException("001", "Existen atributos inválidos en la categoría");
        }
        marcaDto.setId(marca.get_id());
        marcaDto.setNombre( marca.get_nombre() );
        marcaDto.setEstado( marca.get_estado() );
        return marcaDto;
    }
}
