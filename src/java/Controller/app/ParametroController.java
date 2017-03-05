package Controller.app;

import Repository.ServicioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Parametro")
public class ParametroController {

    private static final ServicioRepository servicios = new ServicioRepository();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String parametro(Model m) {
        m.addAttribute("Parametros", servicios.getAll());
        m.addAttribute("parametro", true);
        return "index";
    }
}
