package ucab.dsw.dtos;

import java.util.Date;

public class HijoDto extends DtoBase{

    private String genero;

    private String estado;

    private String fechaNacimiento;

    private Dato_usuarioDto datoUsuarioDto;

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Dato_usuarioDto getDatoUsuarioDto() {
        return datoUsuarioDto;
    }

    public void setDatoUsuarioDto(Dato_usuarioDto datoUsuarioDto) {
        this.datoUsuarioDto = datoUsuarioDto;
    }

    public HijoDto()
    {
    }

    public HijoDto( long id ) throws Exception
    {
        super( id );
    }
}
