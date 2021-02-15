package Implementation;

import ConfigurationLdap.Configuration;
import Interface.ILdap;
import ucab.dsw.dtos.LoginDto;
import ucab.dsw.dtos.PersonDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Usuario;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ImpLdap implements ILdap {

    Configuration confLdap =  new Configuration("ldap://localhost:10389","secret");

    @Override
    public void createPerson(Usuario usuario) throws NamingException {

        DirContext connection = confLdap.connectLDAP();
        try {

            Attribute oc = new BasicAttribute( "objectClass" );
            oc.add( "inetOrgPerson" );
            SimpleDateFormat format = new SimpleDateFormat( "yyyyMMddHHmm" );
            BasicAttributes entry = new BasicAttributes();
            entry.put( oc );
            entry.put( new BasicAttribute( "cn", usuario.get_correo() ) );
            entry.put( new BasicAttribute( "sn", Long.toString(usuario.get_id())));
            entry.put( new BasicAttribute( "pwdLastSuccess", format.format( new Date() ) + "Z" ) );
            connection.createSubcontext( String.format( "cn=%s" + "," + "ou=users,o=empresaa", usuario.get_correo() ), entry );

        }catch(Exception exception) {

            exception.printStackTrace();

        }finally {
            confLdap.disconnectLDAP();
        }

    }

    @Override
    public PersonDto getPerson(LoginDto loginDto) throws NamingException {

        DirContext connection = confLdap.connectLDAP();

        PersonDto person = new PersonDto();

        try {

            SearchControls searcCon = new SearchControls();
            searcCon.setSearchScope( SearchControls.SUBTREE_SCOPE );
            NamingEnumeration results =
                    connection.search( "ou=users,o=empresaa", String.format("cn=%s", loginDto.getEmail()), searcCon );
            if ( results != null ){
                while ( results.hasMore()) {
                    SearchResult res = (SearchResult) results.next();
                    Attributes atbs = res.getAttributes();
                    Attribute atb1 = atbs.get("cn");
                    Attribute atb2 = atbs.get("sn");

                    String mail = (String) atb1.get();
                    String id = (String) atb2.get();

                    person.setEmail(mail);
                    person.setId(id);
                }
            }

        }catch (Exception exception){

            exception.printStackTrace();

        }finally{

            confLdap.disconnectLDAP();

        }
        return person;
    }


    @Override
    public void deletePerson(Usuario usuario) throws NamingException {
        DirContext connection = confLdap.connectLDAP();

        try{

            connection.destroySubcontext(String.format("cn=%s" + "," +  "ou=users,o=empresaa",usuario.get_correo()));

        }catch(Exception exception){

            exception.printStackTrace();

        }finally{

            confLdap.disconnectLDAP();

        }
    }


}
