/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package bos;

import dtos.RetiroSinCuentaDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ramón Zamudio
 */
public class RetiroSinCuentaBOTest {
    
    public RetiroSinCuentaBOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of agregarRetiroSinCuenta method, of class RetiroSinCuentaBO.
     */
    @Test
    public void testAgregarRetiroSinCuenta() throws Exception {
        System.out.println("agregarRetiroSinCuenta");
        RetiroSinCuentaDTO retiro = null;
        RetiroSinCuentaBO instance = null;
        RetiroSinCuentaDTO expResult = null;
        RetiroSinCuentaDTO result = instance.agregarRetiroSinCuenta(retiro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of realizarRetiro method, of class RetiroSinCuentaBO.
     */
    @Test
    public void testRealizarRetiro() throws Exception {
        System.out.println("realizarRetiro");
        int folio = 0;
        String contrasenia = "";
        RetiroSinCuentaBO instance = null;
        boolean expResult = false;
        boolean result = instance.realizarRetiro(folio, contrasenia);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
