package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;
import ucab.dsw.entidades.Nivel_economico;
import ucab.dsw.entidades.Ocupacion;
import ucab.dsw.entidades.Producto;
import ucab.dsw.entidades.Usuario;


@Getter
@Setter
public class SolicitarEstudioResponse {
   private long id;
   private String descripcionSolicitud;
   private String generacionPoblacional;
   private String fechaPeticion;
   private String edadMin;
   private String edadMax;
   private String estado;
   private String cantidadHijo;
   private String generoHijo;
   private String edadMinHijo;
   private String edadMaxHijo;
   private String cuantasPersonasVive;
   private String disponibilidadEnLinea;
   private String nombreProducto;
   private long idProducto;
   private long idusuario;
   private String correoUsuario;
   private long idOcupacion;
   private String nombreOcupacion;
   private long idnivelEconomico;
   private String nombreNivelEconomico;



    public SolicitarEstudioResponse(long id, String descripcionSolicitud, String generacionPoblacional, String fechaPeticion,
                                    String edadMin, String edadMax, String estado, String cantidadHijo, String generoHijo,
                                    String edadMinHijo, String edadMaxHijo, String cuantasPersonasVive,
                                    String disponibilidadEnLinea, Producto producto , Usuario usuario, Ocupacion ocupacion,
                                    Nivel_economico nivel_economico) {
        this.id = id;
        this.descripcionSolicitud = descripcionSolicitud;
        this.generacionPoblacional = generacionPoblacional;
        this.fechaPeticion = fechaPeticion;
        this.edadMin = edadMin;
        this.edadMax = edadMax;
        this.estado = estado;
        this.cantidadHijo = cantidadHijo;
        this.generoHijo = generoHijo;
        this.edadMinHijo = edadMinHijo;
        this.edadMaxHijo = edadMaxHijo;
        this.cuantasPersonasVive = cuantasPersonasVive;
        this.disponibilidadEnLinea = disponibilidadEnLinea;
        this.idOcupacion = ocupacion.get_id();
        this.nombreOcupacion = ocupacion.get_nombre();
        this.idProducto = ocupacion.get_id();
        this.nombreProducto = ocupacion.get_nombre();
        this.idnivelEconomico = nivel_economico.get_id();
        this.nombreNivelEconomico = nivel_economico.get_nivel();
        this.idusuario = usuario.get_id();
        this.correoUsuario = usuario.get_correo();
    }
}
