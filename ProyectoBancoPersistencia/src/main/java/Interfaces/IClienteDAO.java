/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import entidades.Cliente;
import exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public interface IClienteDAO {
    public Cliente agregarCliente(Cliente cliente) throws PersistenciaException;
    public List<Cliente> obtenerTodosLosClientes() throws PersistenciaException;
    public Cliente obtenerClientePorId(int idCliente) throws PersistenciaException;
    
}
