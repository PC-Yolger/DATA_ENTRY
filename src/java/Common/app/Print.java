/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.app;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import org.json.JSONArray;

/**
 *
 * @author HP
 */
public class Print implements Printable {

    private JSONArray data;

    public Print(JSONArray data) {
        this.data = data;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        Font font = new Font("Serif", Font.PLAIN, 8);
        FontMetrics metrics = graphics.getFontMetrics(font);
        int largeline = metrics.getHeight();
        int y = 0;
        for (Object value : data) {
            y += largeline;
            graphics.drawString(value.toString(), 0, y);
        }
        return PAGE_EXISTS;
    }

    public void printFile() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (Exception e) {
            }
        }
    }
}
