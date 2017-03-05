/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.app;

import Common.app.QR;
import Entity.app.TblServicioFactura;
import Entity.app.TblServicioServicio;
import Repository.DireccionRepository;
import Repository.FacturaRepository;
import Repository.ServicioRepository;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Consulta")
public class ConsultaController {

    private static final FacturaRepository facturas = new FacturaRepository();
    private static final ServicioRepository servicios = new ServicioRepository();
    private static final DireccionRepository direcciones = new DireccionRepository();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String consulta(Model m) {
        List<TblServicioFactura> _facturas = facturas.getAll();
        for (TblServicioFactura item : _facturas) {
            item.setServicio(servicios.search(item.getTesCodigoSintesisBi().toString()).getTesDetalleVc());
            item.setDireccion(direcciones.search(item.getTestIdDireccionBi().toString()).getTesCiudadVc());
        }
        m.addAttribute("lstFacturas", _facturas);
        m.addAttribute("lstDirecciones", direcciones.getAll());
        m.addAttribute("lstServicios", servicios.getAll());
        m.addAttribute("consulta", true);
        SimpleDateFormat formats = new SimpleDateFormat("dd/MM/yyyy");
        Calendar now = Calendar.getInstance();
        m.addAttribute("end", formats.format(now.getTime()));
        now.add(Calendar.MONTH, -1);
        m.addAttribute("init", formats.format(now.getTime()));
        m.addAttribute("sucursal", "");
        //        facturaprint();
        return "index";
    }

    @RequestMapping(value = "/Search", method = RequestMethod.GET)
    public String search(@RequestParam("sucursal") String sucursal,
            @RequestParam("init") String init,
            @RequestParam("end") String end,
            @RequestParam("servicio") String servicio,
            Model m) {
        List<TblServicioFactura> _facturas = facturas.Search(sucursal, servicio);
        for (TblServicioFactura item : _facturas) {
            item.setServicio(servicios.search(item.getTesCodigoSintesisBi().toString()).getTesDetalleVc());
            item.setDireccion(direcciones.search(item.getTestIdDireccionBi().toString()).getTesCiudadVc());
        }
//        facturaprint();
        m.addAttribute("lstFacturas", _facturas);
        m.addAttribute("lstDirecciones", direcciones.getAll());
        m.addAttribute("lstServicios", servicios.getAll());
        m.addAttribute("consulta", true);
        SimpleDateFormat formats = new SimpleDateFormat("dd/MM/yyyy");
        Calendar now = Calendar.getInstance();
        m.addAttribute("end", formats.format(now.getTime()));
        now.add(Calendar.MONTH, -1);
        m.addAttribute("init", formats.format(now.getTime()));

        m.addAttribute("sucursal", sucursal);
        m.addAttribute("servicio", servicio);

        return "Consulta";
    }

    public void facturaprint() {
        PrintService services = PrintServiceLookup.lookupDefaultPrintService();
        if (services != null) {
            try {
                String printServiceName = services.getName();
                String streng = "Let's try and print this";
                DocFlavor _flavor = DocFlavor.STRING.TEXT_PLAIN;
                DocPrintJob _job = services.createPrintJob();
                PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
                DocAttributeSet das = new HashDocAttributeSet();
                Doc _doc = new SimpleDoc(streng, _flavor, das);
                _job.print(_doc, pras);
                System.out.println("Print Service Name is " + printServiceName);
            } catch (PrintException ex) {
                System.out.println("ERROR:" + ex.getMessage());
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("No default print service found");
        }
    }

    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    public void SearchFactura(@RequestParam("id") String id) {
        TblServicioFactura factura = facturas.Search(id);
        if (!"".equals(factura.getTesPagoResponse())) {
            createPDF(factura);
        }
    }

    public void createPDF(TblServicioFactura factura) {
        String data = factura.getTesPagoResponse();
        JSONObject obj = new JSONObject(data);
        JSONArray content = obj.getJSONArray("lineaFactura");
        try {
            File _file = File.createTempFile("temp_file", ".pdf");
            OutputStream file = new FileOutputStream(_file);
            Document doc = new Document();
            doc.setMargins(0f, 0f, 0f, 0f);
            PdfWriter.getInstance(doc, file);
            doc.open();
            BaseFont base = BaseFont.createFont("c:/windows/fonts/Consola.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            Font f = new Font(base, 7.0f, Font.NORMAL, BaseColor.BLACK);
            String qr = "";
            for (Object item : content) {
                Paragraph paragraph = new Paragraph(item.toString(), f);
                if (item.toString().contains("<b>")) {
                    Font bold = new Font(base, 7.0f, Font.BOLD, BaseColor.BLACK);
                    paragraph = new Paragraph(item.toString().replace("<b>", ""), bold);
                }
                if (item.toString().contains("<QR>")) {
                    TblServicioServicio servicio = servicios.search(factura.getTesCodigoSintesisBi().toString());
                    qr = item.toString().substring(4);
                    Image image = QR.create(qr);
                    image.setAbsolutePosition(servicio.getQrX().floatValue(), servicio.getQrY().floatValue());
                    image.scaleAbsolute(servicio.getQrScale().floatValue(), servicio.getQrScale().floatValue());
                    doc.add(image);
                    paragraph = new Paragraph("", f);
                }
                doc.add(paragraph);
            }
            doc.close();
            file.close();
            download(_file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConsultaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ConsultaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConsultaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void download(File downloadFile) {
        try {
            Desktop.getDesktop().open(downloadFile);
        } catch (IOException ex) {
            Logger.getLogger(ConsultaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
