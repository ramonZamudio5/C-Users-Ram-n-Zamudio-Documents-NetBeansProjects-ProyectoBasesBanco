/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package daos;

import conexion.Conexion;
import conexion.IConexion;
import entidades.Cliente;
import java.sql.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para la clase ClienteDAO.
 * 
 * Realiza pruebas básicas sobre los métodos de acceso a datos de Cliente.
 * 
 * @author Ramón Zamudio
 */
public class ClienteDAOTest {
    
    private ClienteDAO clienteDAO;

    public ClienteDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        // Opcional: Código que corre antes de todas las pruebas (ej. cargar configuración).
    }
    
    @AfterAll
    public static void tearDownClass() {
        // Opcional: Código que corre después de todas las pruebas.
    }
    
    @BeforeEach
    public void setUp() {
        IConexion conexion = new Conexion();
        clienteDAO = new ClienteDAO(conexion); 
    }
    
    @AfterEach
    public void tearDown() {
        // Opcional: limpiar recursos después de cada prueba.
    }

    /**
     * Test del método agregarCliente de ClienteDAO.
     * Prueba agregar un cliente nuevo y verifica que se asigna un ID.
     */
    @Test
    public void testAgregarCliente() throws Exception {
        System.out.println("agregarCliente");
        Cliente cliente = new Cliente();
        cliente.setNombre("Ramon");
        cliente.setApellidoPaterno("Zamudio");
        cliente.setApellidoMaterno("Ayala");
        cliente.setCalle("Orion");
        cliente.setColonia("Las flores");
        cliente.setCiudad("Ciudad Obregon");
        cliente.setCodigoPostal("85150");
        cliente.setEstado("Sonora");
        cliente.setFechaNacimiento(Date.valueOf(LocalDate.of(2005, Month.NOVEMBER, 5)));
        cliente.setContrasenia(1234);

        Cliente result = clienteDAO.agregarCliente(cliente);
        assertNotNull(result);
        assertTrue(result.getId() > 0, "El ID del cliente debe ser mayor que 0");
        assertEquals(cliente.getNombre(), result.getNombre());
    }

    /**
     * Test del método obtenerTodosLosClientes de ClienteDAO.
     * Verifica que se devuelve una lista no nula de clientes.
     */
    @Test
    public void testObtenerTodosLosClientes() throws Exception {
        System.out.println("obtenerTodosLosClientes");
        List<Cliente> result = clienteDAO.obtenerTodosLosClientes();
        assertNotNull(result);
        assertTrue(result.size() >= 0);
    }

    /**
     * Test del método obtenerClientePorId de ClienteDAO.
     * Prueba obtener un cliente válido por su ID.
     */
    @Test
    public void testObtenerClientePorId() throws Exception {
        System.out.println("obtenerClientePorId");
        // Asumiendo que existe cliente con id = 1
        int idCliente = 1;
        Cliente result = clienteDAO.obtenerClientePorId(idCliente);
        assertNotNull(result);
        assertEquals(idCliente, result.getId());
    }

    /**
     * Test del método existeCliente de ClienteDAO.
     * Prueba verificar si un cliente existe por sus datos.
     */
    @Test
    public void testExisteCliente() throws Exception {
        System.out.println("existeCliente");
        String nombre = "Ramon";
        String apellidoPaterno = "Zamudio";
        String apellidoMaterno = "Ayala";
        Date fechaNacimiento = Date.valueOf(LocalDate.of(2005, Month.NOVEMBER, 5));
        
        boolean result = clienteDAO.existeCliente(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento);
        assertTrue(result, "El cliente debe existir");
    }

    /**
     * Test del método validarCliente de ClienteDAO.
     * Prueba validar el cliente con id y contraseña correctos.
     */
    @Test
    public void testValidarCliente() throws Exception {
        System.out.println("validarCliente");
        int id = 1;
        int contrasenia = 1234; 
        boolean result = clienteDAO.validarCliente(id, contrasenia);
        assertTrue(result, "La validación debe ser exitosa");
    }
    
}