/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package daos;

import conexion.Conexion;
import conexion.IConexion;
import entidades.Cliente;
import entidades.Cuenta;
import enums.EstadoCuenta;
import java.sql.Date;
import java.time.LocalDate;
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
public class CuentaDAOTest {

    private CuentaDAO cuentaDAO;

    public CuentaDAOTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        // Código previo a todas las pruebas (opcional)
    }

    @AfterAll
    public static void tearDownClass() {
        // Código después de todas las pruebas (opcional)
    }

    @BeforeEach
    public void setUp() {
        IConexion conexion = new Conexion();
        cuentaDAO = new CuentaDAO(conexion);
    }

    @AfterEach
    public void tearDown() {
        // Limpieza después de cada prueba (opcional)
    }

    @Test
    public void testAgregarCuenta() throws Exception {
        System.out.println("agregarCuenta");
        Cuenta cuenta = new Cuenta();
        cuenta.setFechaApertura(Date.valueOf(LocalDate.now()));
        cuenta.setSaldo(1000.0);
        cuenta.setEstado(EstadoCuenta.ACTIVA); 
        cuenta.setIdCliente(1); 

        Cuenta result = cuentaDAO.agregarCuenta(cuenta);
        assertNotNull(result);
        assertTrue(result.getId() > 0, "El id debe ser mayor que 0");
        assertEquals(cuenta.getSaldo(), result.getSaldo());
    }

    @Test
    public void testConsultarCuentaPorId() throws Exception {
        System.out.println("consultarCuentaPorId");
        int id = 1; 
        Cuenta result = cuentaDAO.consultarCuentaPorId(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    public void testObtenerTodasLasCuentas() throws Exception {
        System.out.println("obtenerTodasLasCuentas");
        int idCliente = 1; 
        List<Cuenta> result = cuentaDAO.obtenerTodasLasCuentas(idCliente);
        assertNotNull(result);
        assertTrue(result.size() >= 0);
    }

    @Test
    public void testObtenerTodasLasCuentasSinImporarEstado() throws Exception {
        System.out.println("obtenerTodasLasCuentasSinImporarEstado");
        int idCliente = 1;
        List<Cuenta> result = cuentaDAO.obtenerTodasLasCuentasSinImporarEstado(idCliente);
        assertNotNull(result);
        assertTrue(result.size() >= 0);
    }

    @Test
    public void testEditarEstadoCuenta() throws Exception {
        System.out.println("editarEstadoCuenta");
        int idCuenta = 1; // Cuenta existente
        EstadoCuenta nuevoEstado = EstadoCuenta.CANCELADO;
        boolean result = cuentaDAO.editarEstadoCuenta(idCuenta, nuevoEstado);
        assertTrue(result, "El estado debería haberse actualizado correctamente");

        Cuenta cuenta = cuentaDAO.consultarCuentaPorId(idCuenta);
        assertEquals(nuevoEstado, cuenta.getEstado());
    }

    @Test
    public void testObtenerClientePorId() throws Exception {
        System.out.println("obtenerClientePorId");
        int idCliente = 1; 
        Cliente result = cuentaDAO.obtenerClientePorId(idCliente);
        assertNotNull(result);
        assertEquals(idCliente, result.getId());
    }

}