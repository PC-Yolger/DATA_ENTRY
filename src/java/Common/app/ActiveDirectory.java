/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Common.app;

import Model.app.Politica;
import Model.app.Usuario;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/**
 *
 * @author HP
 */
public class ActiveDirectory {
    
    static DirContext ldapContext;
    
    public Usuario GetPoliticas(){
        try {
            Hashtable<String, String> ldapEnv = new Hashtable<String, String>(11);
            ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            ldapEnv.put(Context.PROVIDER_URL,  "ldap://localhost:389");
            ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
            ldapEnv.put(Context.SECURITY_PRINCIPAL, "cn=jean paul blanc,ou=MonOu,dc=dom,dc=fr");
            ldapEnv.put(Context.SECURITY_CREDENTIALS, "pwd");
            //ldapEnv.put(Context.SECURITY_PROTOCOL, "ssl");
            //ldapEnv.put(Context.SECURITY_PROTOCOL, "simple");
            ldapContext = new InitialDirContext(ldapEnv);

            SearchControls searchCtls = new SearchControls();
            String returnedAtts[]={"sn","givenName", "samAccountName"};
            searchCtls.setReturningAttributes(returnedAtts);
            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String searchFilter = "(&(objectClass=user))";
            String searchBase = "dc=dom,dc=fr";
            int totalResults = 0;
            NamingEnumeration<SearchResult> answer = ldapContext.search(searchBase, searchFilter, searchCtls);
            while (answer.hasMoreElements())
            {
              SearchResult sr = (SearchResult)answer.next();
              totalResults++;
              System.out.println(">>>" + sr.getName());
              Attributes attrs = sr.getAttributes();
              System.out.println(">>>>>>" + attrs.get("samAccountName"));
            }
            System.out.println("Total results: " + totalResults);
            ldapContext.close();
        } catch (Exception e) {
            String a = e.getMessage();
        }
        return new Usuario();
    }
    
    public Usuario GetPoliticas_Test(){
        Usuario usuario = new Usuario();
        usuario.NombreCompleto = "Yolger Perez Conde";
        usuario.Email = "yolger_pc522@hotmal.com";
        usuario.Politicas = new ArrayList<Politica>();
        usuario.Politicas.add(new Politica("GD_CONSUTA", "CONSULTA"));
        usuario.Politicas.add(new Politica("GD_IMPRIMIR", "IMPRIMIR"));
        usuario.Politicas.add(new Politica("GD_CONCIALIZACION", "CONCIALIZACION"));
        usuario.Politicas.add(new Politica("GD_REPORTE", "REPORTE"));
        return usuario;
    }
}
