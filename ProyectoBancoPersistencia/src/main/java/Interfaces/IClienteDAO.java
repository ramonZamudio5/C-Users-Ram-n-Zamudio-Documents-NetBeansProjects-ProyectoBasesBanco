/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import entidades.Cliente;
import exception.PersistenciaException;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public interface IClienteDAO {
    /**
     * Agrega un nuevo cliente a la base de datos, generando automáticamente una contraseña.
     * @param cliente cliente Objeto Cliente con la información a guardar.
     * @return Cliente con su ID y contraseña generada asignados.
     * @throws PersistenciaException Si ocurre un error al insertar el cliente.
     */
    public Cliente agregarCliente(Cliente cliente) throws PersistenciaException;
    /**
     * Obtiene una lista con todos los clientes almacenados en la base de datos.
     * @return Lista de objetos Cliente
     * @throws PersistenciaException Si ocurre un error al consultar los clientes.
     */
    public List<Cliente> obtenerTodosLosClientes() throws PersistenciaException;
    /**
     * Obtiene un cliente a partir de su ID.
     * @param idCliente Identificador único del cliente.
     * @return si se encuentra
     * @throws PersistenciaException Si ocurre un error al consultar el cliente.
     */
    public Cliente obtenerClientePorId(int idCliente) throws PersistenciaException;
    /**
     * Verifica si ya existe un cliente en la base de datos con los datos proporcionados.
     * @param nombre Nombre del cliente.
     * @param apellidoPaterno Apellido paterno del cliente.
     * @param apellidoMaterno Apellido materno del cliente.
     * @param fechaNacimiento Fecha de nacimiento del cliente.
     * @return true si el cliente existe, false si no.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    public boolean existeCliente(String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento) throws PersistenciaException;
    /**
     * Valida las credenciales de un cliente mediante su ID y contraseña.
     * @param id ID del cliente.
     * @param contrasenia Contraseña generada del cliente.
     * @return true si las credenciales son válidas, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    public boolean validarCliente(int id, int contrasenia) throws PersistenciaException;
    
}
