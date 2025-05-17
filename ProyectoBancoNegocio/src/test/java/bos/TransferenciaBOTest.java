/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package bos;

import dtos.TransferenciaDTO;
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
public class TransferenciaBOTest {
    
    public TransferenciaBOTest() {
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
     * Test of realizarTransferencia method, of class TransferenciaBO.
     */
    @Test
    public void testRealizarTransferencia() throws Exception {
        System.out.println("realizarTransferencia");
        TransferenciaDTO transferencia = null;
        TransferenciaBO instance = null;
        TransferenciaDTO expResult = null;
        TransferenciaDTO result = instance.realizarTransferencia(transferencia);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
