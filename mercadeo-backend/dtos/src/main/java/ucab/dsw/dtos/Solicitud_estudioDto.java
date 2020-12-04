package ucab.dsw.dtos;

import java.util.Date;

public class Solicitud_estudioDto extends DtoBase{

    private String estado;

    private String descripcionSolicitud;

    private String generoPoblacional;

    private Date fechaPeticion;

    private String edadMinimaPoblacion;

    private String edadMaximaPoblacion;

    private String cantidadHijos;

    private String generoHijos;

    private String edadMinimaHijos;

    private String edadMaximaHijos;

    private String conCuantasPersonasVive;

    private String disponibilidadEnLinea;

    private Nivel_economicoDto nivelEconomicoDto;

    private OcupacionDto ocupacionDto;

    private UsuarioDto usuarioDto;

    private ProductoDto productoDto;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcionSolicitud() {
        return descripcionSolicitud;
    }

    public void setDescripcionSolicitud(String descripcionSolicitud) {
        this.descripcionSolicitud = descripcionSolicitud;
    }

    public String getGeneroPoblacional() {
        return generoPoblacional;
    }

    public void setGeneroPoblacional(String generoPoblacional) {
        this.generoPoblacional = generoPoblacional;
    }

    public Date getFechaPeticion() {
        return fechaPeticion;
    }

    public void setFechaPeticion(Date fechaPeticion) {
        this.fechaPeticion = fechaPeticion;
    }

    public String getEdadMinimaPoblacion() {
        return edadMinimaPoblacion;
    }

    public void setEdadMinimaPoblacion(String edadMinimaPoblacion) {
        this.edadMinimaPoblacion = edadMinimaPoblacion;
    }

    public String getEdadMaximaPoblacion() {
        return edadMaximaPoblacion;
    }

    public void setEdadMaximaPoblacion(String edadMaximaPoblacion) {
        this.edadMaximaPoblacion = edadMaximaPoblacion;
    }

    public String getCantidadHijos() {
        return cantidadHijos;
    }

    public void setCantidadHijos(String cantidadHijos) {
        this.cantidadHijos = cantidadHijos;
    }

    public String getGeneroHijos() {
        return generoHijos;
    }

    public void setGeneroHijos(String generoHijos) {
        this.generoHijos = generoHijos;
    }

    public String getEdadMinimaHijos() {
        return edadMinimaHijos;
    }

    public void setEdadMinimaHijos(String edadMinimaHijos) {
        this.edadMinimaHijos = edadMinimaHijos;
    }

    public String getEdadMaximaHijos() {
        return edadMaximaHijos;
    }

    public void setEdadMaximaHijos(String edadMaximaHijos) {
        this.edadMaximaHijos = edadMaximaHijos;
    }

    public String getConCuantasPersonasVive() {
        return conCuantasPersonasVive;
    }

    public void setConCuantasPersonasVive(String conCuantasPersonasVive) {
        this.conCuantasPersonasVive = conCuantasPersonasVive;
    }

    public String getDisponibilidadEnLinea() {
        return disponibilidadEnLinea;
    }

    public void setDisponibilidadEnLinea(String disponibilidadEnLinea) {
        this.disponibilidadEnLinea = disponibilidadEnLinea;
    }

    public Nivel_economicoDto getNivelEconomicoDto() {
        return nivelEconomicoDto;
    }

    public void setNivelEconomicoDto(Nivel_economicoDto nivelEconomicoDto) {
        this.nivelEconomicoDto = nivelEconomicoDto;
    }

    public OcupacionDto getOcupacionDto() {
        return ocupacionDto;
    }

    public void setOcupacionDto(OcupacionDto ocupacionDto) {
        this.ocupacionDto = ocupacionDto;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public ProductoDto getProductoDto() {
        return productoDto;
    }

    public void setProductoDto(ProductoDto productoDto) {
        this.productoDto = productoDto;
    }

    public Solicitud_estudioDto()
    {
    }

    public Solicitud_estudioDto( long id ) throws Exception
    {
        super( id );
    }
}
