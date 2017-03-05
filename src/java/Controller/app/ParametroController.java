package Controller.app;

import Entity.app.TblServicioServicio;
import Repository.ServicioRepository;
import java.math.BigDecimal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Parametro")
public class ParametroController {

    private static final ServicioRepository servicios = new ServicioRepository();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String parametro(Model m) {
        m.addAttribute("lstServicios", servicios.getAll());
        m.addAttribute("parametro", true);
        return "index";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("id") String id,
            @RequestParam("nombre") String nombre,
            @RequestParam("cuenta") String cuenta,
            @RequestParam("cuentaContable") String cuentaContable,
            @RequestParam("comision") String comision,
            @RequestParam("estado") String estado,
            @RequestParam("tamanoQR") String tamanoQR,
            @RequestParam("qrx") String qrx,
            @RequestParam("qry") String qry,
            Model m) {
        TblServicioServicio servicio = servicios.searchId(id);
        servicio.setTesDetalleVc(nombre);
        servicio.setTesCuentaVc(cuenta);
        servicio.setTesCuentaContableVc(cuentaContable);
        servicio.setTesComisionDc(BigDecimal.valueOf(Double.valueOf(comision)));
        servicio.setTesEstadoBt(Boolean.valueOf(estado));
        servicio.setQrScale(BigDecimal.valueOf(Double.valueOf(tamanoQR)));
        servicio.setQrX(BigDecimal.valueOf(Double.valueOf(qrx)));
        servicio.setQrY(BigDecimal.valueOf(Double.valueOf(qry)));
        servicios.Edit(servicio);
        m.addAttribute("lstServicios", servicios.getAll());
        m.addAttribute("parametro", true);
        return "Parametro";
    }
}
