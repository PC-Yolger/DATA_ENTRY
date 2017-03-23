/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller.app;

import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.ui.Model;

/**
 *
 * @author HP
 */
public class ConsultaControllerTest {
    
    public ConsultaControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of consulta method, of class ConsultaController.
     */
    @Test
    public void testConsulta() {
        System.out.println("consulta");
        Model m = null;
        ConsultaController instance = new ConsultaController();
        String expResult = "";
        String result = instance.consulta(m);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of search method, of class ConsultaController.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        String sucursal = "";
        String init = "";
        String end = "";
        String servicio = "";
        Model m = null;
        ConsultaController instance = new ConsultaController();
        String expResult = "";
        String result = instance.search(sucursal, init, end, servicio, m);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SearchFactura method, of class ConsultaController.
     */
    @Test
    public void testSearchFactura() {
        System.out.println("SearchFactura");
        HttpServletResponse response = null;
        String id = "11";
        ConsultaController instance = new ConsultaController();
        instance.SearchFactura(response, id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of PrintFactura method, of class ConsultaController.
     */
    @Test
    public void testPrintFactura() {
        System.out.println("PrintFactura");
        String id = "";
        ConsultaController instance = new ConsultaController();
        String expResult = "";
        String result = instance.PrintFactura(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
