/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.ClienteDTO;
import entidades.Cliente;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase encargada de mapear entre las entidades Cliente y ClienteDTO.
 * Proporciona métodos para convertir objetos individuales y listas entre ambas representaciones.
 * 
 * @author Ramón Zamudio
 */
public class ClienteMapper {

    /**
     * Convierte un ClienteDTO a una entidad Cliente.
     * 
     * @param cliente DTO con los datos del cliente.
     * @return Objeto Cliente correspondiente.
     */
    public static Cliente toEntity(ClienteDTO cliente){
        return new Cliente(cliente.getNombre(), cliente.getApellidoPaterno(), cliente.getApellidoMaterno(), cliente.getColonia(),
                cliente.getCalle(), cliente.getCiudad(), cliente.getCodigoPostal(), cliente.getEstado(), cliente.getFechaNacimiento());
    }
    
    /**
     * Convierte una entidad Cliente a un ClienteDTO.
     * 
     * @param cliente Entidad cliente con los datos.
     * @return DTO Cliente con los datos correspondientes.
     */
    public static ClienteDTO toDTO(Cliente cliente){
        ClienteDTO clienteNuevo = new ClienteDTO(cliente.getId(), cliente.getNombre(), cliente.getApellidoPaterno(), cliente.getApellidoMaterno(), 
                cliente.getColonia(), cliente.getCalle(), cliente.getCiudad(), cliente.getCodigoPostal(), cliente.getEstado(), 
                cliente.getFechaNacimiento(), cliente.getEdad());
        clienteNuevo.setContrasenia(cliente.getContrasenia());
        return clienteNuevo;
    }
    
    /**
     * Convierte una lista de ClienteDTO a una lista de entidades Cliente.
     * 
     * @param clientes Lista de DTOs Cliente.
     * @return Lista de entidades Cliente.
     */
    public static List<Cliente> listToEntity(List<ClienteDTO> clientes){
        LinkedList<Cliente> listaClientes = new LinkedList<>();
        for(ClienteDTO cliente : clientes){
            listaClientes.add(toEntity(cliente));
        }
        return listaClientes;
    }
    
    /**
     * Convierte una lista de entidades Cliente a una lista de ClienteDTO.
     * 
     * @param clientes Lista de entidades Cliente.
     * @return Lista de DTOs Cliente.
     */
    public static List<ClienteDTO> listToDTO(List<Cliente> clientes){
        LinkedList<ClienteDTO> listaClientes = new LinkedList<>();
        for(Cliente cliente : clientes){
            listaClientes.add(toDTO(cliente));
        }
        return listaClientes;
    }
}
    

