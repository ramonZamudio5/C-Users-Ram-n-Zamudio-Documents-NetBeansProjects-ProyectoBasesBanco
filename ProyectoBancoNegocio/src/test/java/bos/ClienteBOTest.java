/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package bos;

import dtos.ClienteDTO;
import interfaces.IClienteBO;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
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
public class ClienteBOTest {
    
    public ClienteBOTest() {
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
     * Test of agregarCliente method, of class ClienteBO.
     */
    @Test
    public void testAgregarCliente() throws Exception {
        System.out.println("agregarCliente");
        ClienteDTO cliente = new ClienteDTO("Ramon", "Zamudio", "ayala", "orion", "las flores", "ciudad Obregon", "85150", "Sonora", Date.valueOf(LocalDate.of(2005, Month.NOVEMBER, 5)));
        IClienteBO instance = FabricaBOs.getInstanceClienteBO();
        ClienteDTO result = instance.agregarCliente(cliente);
        result.setContrasenia(0);
        cliente.setId(result.getId());
        cliente.setContrasenia(cliente.getContrasenia());
        ClienteDTO expResult = cliente;
        assertEquals(expResult, result);
    }

    /**
     * Test of obtenerTodosLosClientes method, of class ClienteBO.
     */
    @Test
    public void testObtenerTodosLosClientes() throws Exception {
        System.out.println("obtenerTodosLosClientes");
        IClienteBO instance = FabricaBOs.getInstanceClienteBO();
        List<ClienteDTO> lista = instance.obtenerTodosLosClientes();
        List<ClienteDTO> expResult = lista;
        List<ClienteDTO> result = instance.obtenerTodosLosClientes();
        assertEquals(expResult, result);
    }

    /**
     * Test of obtenerClientePorId method, of class ClienteBO.
     */
    @Test
    public void testObtenerClientePorId() throws Exception {
        System.out.println("obtenerClientePorId");
        int idCliente = 1;
        IClienteBO instance = FabricaBOs.getInstanceClienteBO();
        ClienteDTO expResult = instance.obtenerClientePorId(idCliente);
        ClienteDTO result = instance.obtenerClientePorId(idCliente);
        assertEquals(expResult, result);
    }

    /**
     * Test of validarCliente method, of class ClienteBO.
     */
    @Test
    public void testValidarCliente() throws Exception {
        System.out.println("validarCliente");
        IClienteBO instance = FabricaBOs.getInstanceClienteBO();
        ClienteDTO cliente = instance.obtenerClientePorId(1);
        int id = 1;
        int contrasenia =cliente.getContrasenia();
        boolean expResult = true;
        boolean result = instance.validarCliente(id, contrasenia);
        assertEquals(expResult, result);
    }
    
}
