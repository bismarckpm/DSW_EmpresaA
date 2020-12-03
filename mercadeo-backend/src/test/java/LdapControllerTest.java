import Controllers.LdapController;
import Dto.LoginDto;
import Dto.PersonDto;
import Model.RestApi;
import org.junit.Assert;
import org.junit.Test;

import javax.naming.NamingException;

public class LdapControllerTest {

    @Test
    public void createPersonToLdapTest() throws NamingException {
        PersonDto personDto = new PersonDto();
        LdapController ldapController = new LdapController();

        personDto.setEmail("gmlm60832@gmail.com");
        personDto.setPassword("12345");

        RestApi res = ldapController.createPersonLdap(personDto);

        Assert.assertEquals(200, res.getStatus());

    }

    @Test
    public void getPersonToLdapTest() throws NamingException {
        LoginDto loginDto = new LoginDto();
        LdapController ldapController = new LdapController();

        loginDto.setEmail("gmlm60832@gmail.com");

        RestApi res = ldapController.getPersonToLdap(loginDto);

        Assert.assertEquals("gmlm60832@gmail.com", res.getData());

    }

    @Test
    public void authenticationPersonToLdapTest() throws NamingException {
        LoginDto loginDto = new LoginDto();
        LdapController ldapController = new LdapController();

        loginDto.setEmail("gmlm60832@gmail.com");

        RestApi res = ldapController.authenticationPersonLdap(loginDto);

        Assert.assertEquals(1000, res.getCode());

    }


    @Test
    public void changePasswordPersonToLdapTest() throws NamingException {
        PersonDto personDto = new PersonDto();
        LdapController ldapController = new LdapController();

        personDto.setEmail("gmlm60832@gmail.com");
        personDto.setPassword("valeria");

        RestApi res = ldapController.changePasswordPersonLdap(personDto);

        Assert.assertEquals(1000, res.getCode());

    }






}
