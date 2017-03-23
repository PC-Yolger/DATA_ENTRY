/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.app;

import Common.app.Print;
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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
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
        Collections.sort(_facturas, new Comparator<TblServicioFactura>() {
            @Override
            public int compare(TblServicioFactura o1, TblServicioFactura o2) {
                return o1.getTesIdFacturaBi() - o2.getTesIdFacturaBi();
            }
        });
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
        Collections.sort(_facturas, new Comparator<TblServicioFactura>() {
            @Override
            public int compare(TblServicioFactura o1, TblServicioFactura o2) {
                return o1.getTesIdFacturaBi() - o2.getTesIdFacturaBi();
            }
        });
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

    @RequestMapping(value = "/pdf", method = RequestMethod.GET)

    public void SearchFactura(HttpServletResponse response, @RequestParam("id") String id) {
        TblServicioFactura factura = facturas.Search(id);
        File file = null;
        if (!"".equals(factura.getTesPagoResponse())) {
            try {
                file = createPDF(factura);
                if (!file.exists()) {
                    return;
                }
                String mimeType = URLConnection.guessContentTypeFromName(file.getName());
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }
                response.setContentType(mimeType);
                response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
                response.setContentLength((int) file.length());
                InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                FileCopyUtils.copy(inputStream, response.getOutputStream());
            } catch (IOException e) {
                Logger.getLogger(ConsultaController.class.getName()).log(Level.INFO, null, e);
            }
        }
    }

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public String PrintFactura(@RequestParam("id") String id) {
        TblServicioFactura factura = facturas.Search(id);
        if (!"".equals(factura.getTesPagoResponse())) {
            try {

                String data = factura.getTesPagoResponse();
                JSONObject obj = new JSONObject(data);
                JSONArray content = obj.getJSONArray("lineaFactura");
                Print print = new Print(content);
                print.printFile();
//                File file = createPDF(factura);
//                FileInputStream fis = new FileInputStream(file);
//                DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
//                Doc pdfDoc = new SimpleDoc(fis, flavor, null);
//                PrintService service = PrintServiceLookup.lookupDefaultPrintService();
//                DocPrintJob printJob = service.createPrintJob();
//                PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
//                printJob.print(pdfDoc, attributeSet);
//                fis.close();
            } catch (Exception ex) {
                Logger.getLogger(ConsultaController.class.getName()).log(Level.INFO, null, ex);
                return "ERROR: " + ex.getMessage();
            }
        }
        return "COMPLETADO";
    }

    private File createPDF(TblServicioFactura factura) throws IOException {
        String data = factura.getTesPagoResponse();
        JSONObject obj = new JSONObject(data);
        JSONArray content = obj.getJSONArray("lineaFactura");
        String temp = "";
        File _file = null;
        Document doc = null;
        OutputStream file = null;
        try {
            _file = File.createTempFile("temp_file", ".pdf");
            TblServicioServicio servicio = servicios.search(factura.getTesCodigoSintesisBi().toString());
            file = new FileOutputStream(_file);
            doc = new Document(PageSize.LETTER);
            doc.setMargins(servicio.getMarginLeft().floatValue(), servicio.getMarginRight().floatValue(), servicio.getMarginTop().floatValue(), servicio.getMarginBottom().floatValue());
            PdfWriter.getInstance(doc, file);
            doc.open();
//            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            ClassLoader classloader = getClass().getClassLoader();
            URL url = classloader.getResource("cour.ttf");
            BaseFont base = null;
            if (url == null) {
                base = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            } else {
                String path = url.getPath();
                if ("/".equals(path.substring(0, 1))) {
                    path = path.substring(1);
                }
                Logger.getLogger(ConsultaController.class.getName()).log(Level.INFO, path);
                base = BaseFont.createFont(path, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            }
            Font f = new Font(base, servicio.getFontSize(), Font.NORMAL, BaseColor.BLACK);
            String qr = "";
            float line = 0;
            for (Object item : content) {
                Paragraph paragraph = new Paragraph(item.toString(), f);
                if (servicio.getTesDetalleVc().trim().equals("PAGO ENTEL")) {
                    if (item.toString().contains("P X#X&$#&K##")) {
                        paragraph = new Paragraph(" ", f);
                        doc.add(paragraph);
                        doc.add(paragraph);
                        paragraph = new Paragraph(item.toString(), f);
                    }
                }
                if (item.toString().contains("<b>")) {
                    Font bold = new Font(base, 7.0f, Font.BOLD, BaseColor.BLACK);
                    paragraph = new Paragraph(item.toString().replace("<b>", ""), bold);
                }
                if (item.toString().contains("<QR>") || item.toString().contains("<QR_ENT_G>")) {
                    qr = item.toString().replaceAll("<QR>", "");
                    if (servicio.getTesDetalleVc().trim().equals("PAGO ENTEL")) {
                        qr = item.toString().replaceAll("<QR_ENT_G>", "");
                        Image image = QR.create(qr);
                        image.scaleAbsolute(75, 75);
                        image.setAlignment(Image.ALIGN_RIGHT);
                        image.setAbsolutePosition(doc.getPageSize().getWidth() - (110 - servicio.getMarginLeft().floatValue()), doc.getPageSize().getHeight() - (line * 12.7f));
                        doc.add(image);
                        paragraph = new Paragraph(" ", f);
                    }
                }
                if (servicio.getDelimitador().equals(item.toString().trim())) {
                    doc.newPage();
                } else {
                    line += 1f;
                    doc.add(paragraph);
                }
                temp += item.toString();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConsultaController.class.getName()).log(Level.INFO, null, ex);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(ConsultaController.class.getName()).log(Level.INFO, null, ex);
        }
        doc.close();
        file.close();
        return _file;
    }
}
