package ucab.dsw.dtos;


import ucab.dsw.excepciones.CustomException;

public class DtoBase
{
    private long _id;

    public DtoBase( long id ) throws Exception { setId( id ); }

    public DtoBase() { }

    public long getId()
    {
        return _id;
    }

    public void setId( long id ) throws CustomException {
        if ( id >= 0 ) _id = id;
        else throw new CustomException( "008", "El id tiene valor 0");
    }
}
