/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import Interfaces.IClienteDAO;
import dtos.ClienteDTO;
import entidades.Cliente;
import excepciones.NegocioException;
import exception.PersistenciaException;
import interfaces.IClienteBO;
import java.util.LinkedList;
import java.util.List;
import mappers.ClienteMapper;

/**
 *
 * @author Ramón Zamudio
 */
public class ClienteBO implements IClienteBO{
    private IClienteDAO clienteDAO;

    public ClienteBO(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }
    
    @Override
    public ClienteDTO agregarCliente(ClienteDTO cliente) throws NegocioException{
        try{
            Cliente clienteAgregado = clienteDAO.agregarCliente(ClienteMapper.toEntity(cliente));
            return ClienteMapper.toDTO(clienteAgregado);
        }catch(PersistenciaException e){
            throw new NegocioException("Error al agregar el cliente", e);
        }
    }
    @Override
    public List<ClienteDTO> obtenerTodosLosClientes() throws NegocioException{
        try{
            return ClienteMapper.listToDTO(clienteDAO.obtenerTodosLosClientes());
        }catch(PersistenciaException e){
            throw new NegocioException("Error al consultar con la base de datos", e);
        }
    }
    @Override
    public ClienteDTO obtenerClientePorId(int idCliente) throws NegocioException{
        try{
            return ClienteMapper.toDTO(clienteDAO.obtenerClientePorId(idCliente));
        }catch(PersistenciaException e){
            throw new NegocioException("Error al consultar con la base de datos", e);
        }
    }
}
