/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.ClienteDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public interface IClienteBO {
    public ClienteDTO agregarCliente(ClienteDTO cliente) throws NegocioException;
    public List<ClienteDTO> obtenerTodosLosClientes() throws NegocioException;
    public ClienteDTO obtenerClientePorId(int idCliente) throws NegocioException;
    public boolean validarCliente(int id, int contrasenia) throws NegocioException; 
}
