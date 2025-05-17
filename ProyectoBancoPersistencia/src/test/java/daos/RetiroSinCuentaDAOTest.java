/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package daos;

import conexion.Conexion;
import conexion.IConexion;
import entidades.RetiroSinCuenta;
import java.time.LocalDateTime;
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
public class RetiroSinCuentaDAOTest {

    private RetiroSinCuentaDAO retiroDAO;

    public RetiroSinCuentaDAOTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        IConexion conexion = new Conexion();
        retiroDAO = new RetiroSinCuentaDAO(conexion);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAgregarRetiroSinCuenta() throws Exception {
        System.out.println("agregarRetiroSinCuenta");

        RetiroSinCuenta retiro = new RetiroSinCuenta();
        retiro.setFecha(LocalDateTime.now());
        retiro.setContrasenia("1234");
        retiro.setMonto(500.0);
        retiro.setIdCuenta(1); 

        RetiroSinCuenta result = retiroDAO.agregarRetiroSinCuenta(retiro);

        assertNotNull(result);
        assertTrue(result.getFolio() > 0, "El folio debe ser mayor a 0");
        assertEquals(retiro.getMonto(), result.getMonto());
        assertEquals(retiro.getIdCuenta(), result.getIdCuenta());
    }

    @Test
    public void testRealizarRetiro() throws Exception {
        System.out.println("realizarRetiro");

        int folio = 1;
        String contrasenia = "1234";

        boolean result = retiroDAO.realizarRetiro(folio, contrasenia);

        assertTrue(result, "El retiro debería realizarse correctamente");
    }

}