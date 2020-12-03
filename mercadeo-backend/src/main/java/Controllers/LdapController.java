package Controllers;

import Dto.LoginDto;
import Dto.PersonDto;
import Model.Login;
import Model.Person;
import Model.RestApi;
import Services.LdapService;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ldap")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LdapController {

    private LdapService ldapService = new LdapService();

    @POST
    @Path("/person")
    @Consumes("application/json")
    public RestApi getPersonToLdap(LoginDto loginDto) throws NamingException {
        Person person = ldapService.getPersonToLdap(loginDto);
        return new RestApi(1000, person.getName(), "200", "Usuario encontrado satisfactoriamente");

    }

    @POST
    @Path("/create")
    @Consumes("application/json")
    public RestApi createPersonLdap(PersonDto personDto) throws NamingException {

        ldapService.createPersonToLdap(personDto);

        return new RestApi(1000, "","200", "Creado Satisfactiramente en el directorio activo");

    }

    @POST
    @Path("/change/password")
    @Consumes("application/json")
    public RestApi changePasswordPersonLdap(PersonDto personDto) throws NamingException {

        ldapService.changePasswordToLdap(personDto);

        return new RestApi(1000, "","200", "Se cambio la clave satisfactoriemente");

    }

    @POST
    @Path("/authentication")
    @Consumes("application/json")
    public RestApi authenticationPersonLdap(LoginDto loginDto) throws NamingException {

        ldapService.authenticationToLdap(loginDto);

        return new RestApi(1000, "","200", "Se autentico satisfcatoriemnte");

    }
}
