package Controller.app;

import Common.app.ActiveDirectory;
import Model.app.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        ActiveDirectory AD = new ActiveDirectory();
        Usuario usuario = AD.GetPoliticas_Test();
        model.addAttribute("Usuario", usuario.NombreCompleto);
        model.addAttribute("home", true);
        return "index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model m) {
        m.addAttribute("home", true);
        return "index";
    }
}
