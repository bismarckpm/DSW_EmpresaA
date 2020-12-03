package Directorio_Activo;

import lombok.Getter;
import lombok.Setter;

import Exception.LdapException;


import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

@Getter
@Setter
public class Configuration {

    private DirContext _ldapContext;
    private String _url;
    private String _connType;
    private String _directory;
    private String _userDirectory;
    private String _user;
    private String _password;

    public Configuration(String _url, String _password) {

        this._url = _url;
        this._password = _password;

    }

    public void connectLDAP() throws LdapException, AuthenticationException {
        try {

            Hashtable<String, String> environment = new Hashtable<String, String>();
            environment.put( Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory" );
            environment.put( Context.PROVIDER_URL, _url );
            environment.put( Context.SECURITY_PRINCIPAL, String.format( "uid=admin,ou=system") );
            environment.put( Context.SECURITY_CREDENTIALS, _password );
            _ldapContext = new InitialDirContext( environment );

            System.out.print("Ldap conectado: " + _ldapContext);

        }catch(AuthenticationException ex) {

            throw  new AuthenticationException("Error al autenticarse");

        }
        catch ( Exception ex ) {

            throw  new LdapException("Error al conectar servidor Ldap", ex.getCause());

        }
    }

    public void disconnectLDAP() throws LdapException {
        try {

            _ldapContext.close();

        }
        catch ( Exception ex ) {

            throw  new LdapException("Error al desconectar servidor Ldap", ex.getCause());

        }
    }

}
