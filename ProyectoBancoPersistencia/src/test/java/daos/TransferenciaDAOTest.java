/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package daos;

import conexion.Conexion;
import conexion.IConexion;
import entidades.Transferencia;
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
public class TransferenciaDAOTest {
    
    public TransferenciaDAOTest() {
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
     * Test of realizarTransferencia method, of class TransferenciaDAO.
     */
    @Test
    public void testRealizarTransferencia() throws Exception {
        System.out.println("realizarTransferencia");

        Transferencia transferencia = new Transferencia(
            LocalDateTime.now(),
            1000.0,
            1,  
            2   
        );
        IConexion conexion = new Conexion();
        TransferenciaDAO instance = new TransferenciaDAO(conexion);

        Transferencia result = instance.realizarTransferencia(transferencia);

        assertNotNull(result, "La transferencia resultante no debe ser null");
        assertTrue(result.getId() > 0, "El ID de la transferencia debe ser mayor que 0");

        assertEquals(transferencia.getMonto(), result.getMonto());
        assertEquals(transferencia.getIdOrigen(), result.getIdOrigen());
        assertNotNull(result.getFecha(), "La fecha no debe ser null");
    }
    
}
