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
 *
 * @author Ramón Zamudio
 */
public class ClienteMapper {
    public static Cliente toEntity(ClienteDTO cliente){
        return new Cliente(cliente.getNombre(), cliente.getApellidoPaterno(), cliente.getApellidoMaterno(), cliente.getColonia()
                , cliente.getCalle(), cliente.getCiudad(), cliente.getCodigoPostal(), cliente.getEstado(), cliente.getFechaNacimiento());
    }
    
    public static ClienteDTO toDTO(Cliente cliente){
        return new ClienteDTO(cliente.getId(),cliente.getNombre(), cliente.getApellidoPaterno(), cliente.getApellidoMaterno(), cliente.getColonia()
                , cliente.getCalle(), cliente.getCiudad(), cliente.getCodigoPostal(), cliente.getEstado(), cliente.getFechaNacimiento(),cliente.getEdad());
    }
    
    public static List<Cliente> listToEntity(List<ClienteDTO> clientes){
        LinkedList<Cliente> listaClientes = new LinkedList<>();
        for(ClienteDTO cliente : clientes){
            listaClientes.add(toEntity(cliente));
        }
        return listaClientes;
    }
    
    public static List<ClienteDTO> listToDTO(List<Cliente> clientes){
        LinkedList<ClienteDTO> listaClientes = new LinkedList<>();
        for(Cliente cliente : clientes){
            listaClientes.add(toDTO(cliente));
        }
        return listaClientes;
    }
    
}
