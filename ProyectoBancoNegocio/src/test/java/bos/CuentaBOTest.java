/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package bos;

import dtos.CuentaDTO;
import java.util.List;
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
public class CuentaBOTest {
    
    public CuentaBOTest() {
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
     * Test of agregarCuenta method, of class CuentaBO.
     */
    @Test
    public void testAgregarCuenta() throws Exception {
        System.out.println("agregarCuenta");
        CuentaDTO cuenta = null;
        CuentaBO instance = null;
        CuentaDTO expResult = null;
        CuentaDTO result = instance.agregarCuenta(cuenta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of consultarCuentaPorId method, of class CuentaBO.
     */
    @Test
    public void testConsultarCuentaPorId() throws Exception {
        System.out.println("consultarCuentaPorId");
        int id = 0;
        CuentaBO instance = null;
        CuentaDTO expResult = null;
        CuentaDTO result = instance.consultarCuentaPorId(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerTodasLasCuentas method, of class CuentaBO.
     */
    @Test
    public void testObtenerTodasLasCuentas() throws Exception {
        System.out.println("obtenerTodasLasCuentas");
        int idCliente = 0;
        CuentaBO instance = null;
        List<CuentaDTO> expResult = null;
        List<CuentaDTO> result = instance.obtenerTodasLasCuentas(idCliente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editarEstadoCuenta method, of class CuentaBO.
     */
    @Test
    public void testEditarEstadoCuenta() throws Exception {
        System.out.println("editarEstadoCuenta");
        int idCuenta = 0;
        String nuevoEstado = "";
        CuentaBO instance = null;
        boolean expResult = false;
        boolean result = instance.editarEstadoCuenta(idCuenta, nuevoEstado);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerTodasLasCuentasSinImporarEstado method, of class CuentaBO.
     */
    @Test
    public void testObtenerTodasLasCuentasSinImporarEstado() throws Exception {
        System.out.println("obtenerTodasLasCuentasSinImporarEstado");
        int idCliente = 0;
        CuentaBO instance = null;
        List<CuentaDTO> expResult = null;
        List<CuentaDTO> result = instance.obtenerTodasLasCuentasSinImporarEstado(idCliente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
