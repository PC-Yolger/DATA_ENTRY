/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.app;

/**
 *
 * @author HP
 */
public class UsuarioRequest {

    public String usuario;
    public String password;
    public int idsistema;

    public UsuarioRequest(String usuario, String password, int idsistema) {
        this.usuario = usuario;
        this.password = password;
        this.idsistema = idsistema;
    }

    public UsuarioRequest() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdsistema() {
        return idsistema;
    }

    public void setIdsistema(int idsistema) {
        this.idsistema = idsistema;
    }

}
