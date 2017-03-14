/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.app;

import Model.app.UsuarioRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author HP
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @ModelAttribute("user")
    public UsuarioRequest getUsuarioRequestObject() {
        return new UsuarioRequest();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "Login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("user") UsuarioRequest request, ModelMap m, HttpSession session) {
        if (request.getUsuario().equals("admin") && request.getPassword().equals("admin")) {
            session.setAttribute("user", request.getUsuario());
            return "redirect:/Home/";
        } else {
            String msg = "El usuario o contraseña es incorrecto.";
            if (request.getUsuario().equals("")) {
                msg = "El campo Usuario no puede estar vacio.";
            }
            if (request.getPassword().equals("")) {
                msg = "El campo Contraseña no puede estar vacio.";
            }
            m.put("message", msg);
            return "Login";
        }
    }
}
