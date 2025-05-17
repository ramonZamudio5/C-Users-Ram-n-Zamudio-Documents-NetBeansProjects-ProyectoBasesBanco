/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import Interfaces.IClienteDAO;
import conexion.IConexion;
import daos.ClienteDAO;
import dtos.ClienteDTO;
import entidades.Cliente;
import excepciones.NegocioException;
import exception.PersistenciaException;
import interfaces.IClienteBO;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import mappers.ClienteMapper;

/**
 * Implementación de la lógica de negocio relacionada con clientes.
 * Realiza validaciones y delega las operaciones de persistencia al DAO correspondiente.
 * 
 * @author Ramón Zamudio
 */
public class ClienteBO implements IClienteBO {
    private IClienteDAO clienteDAO;

    /**
     * Constructor que inicializa el DAO de clientes usando una conexión proporcionada.
     * 
     * @param conexion Objeto que gestiona la conexión a la base de datos.
     */
    public ClienteBO(IConexion conexion) {
        this.clienteDAO = new ClienteDAO(conexion);
    }

    /**
     * Agrega un nuevo cliente al sistema después de realizar validaciones de negocio.
     * 
     * @param cliente Objeto DTO que contiene los datos del cliente.
     * @return ClienteDTO con los datos del cliente agregado.
     * @throws NegocioException Si alguno de los campos es inválido o ya existe un cliente igual.
     */
    @Override
    public ClienteDTO agregarCliente(ClienteDTO cliente) throws NegocioException {
        if (cliente == null) {
            throw new NegocioException("El cliente no puede ser nulo.");
        }

        if (esVacio(cliente.getNombre()) || esVacio(cliente.getApellidoPaterno()) || esVacio(cliente.getApellidoMaterno())) {
            throw new NegocioException("El nombre completo es obligatorio.");
        }

        if (esVacio(cliente.getColonia()) || esVacio(cliente.getCalle()) || esVacio(cliente.getCiudad()) ||
            esVacio(cliente.getCodigoPostal()) || esVacio(cliente.getEstado())) {
            throw new NegocioException("Todos los campos de domicilio son obligatorios.");
        }

        if (!cliente.getCodigoPostal().matches("\\d{5}")) {
            throw new NegocioException("El código postal debe contener exactamente 5 dígitos.");
        }

        Date fechaNacimiento = cliente.getFechaNacimiento();
        if (fechaNacimiento == null) {
            throw new NegocioException("La fecha de nacimiento es obligatoria.");
        }

        LocalDate nacimiento;
        if (fechaNacimiento instanceof java.sql.Date) {
            nacimiento = ((java.sql.Date) fechaNacimiento).toLocalDate();
        } else {
            nacimiento = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        LocalDate hoy = LocalDate.now();
        if (nacimiento.isAfter(hoy)) {
            throw new NegocioException("La fecha de nacimiento no puede ser futura.");
        }

        int edadCalculada = Period.between(nacimiento, hoy).getYears();
        if (edadCalculada < 18) {
            throw new NegocioException("El cliente debe ser mayor de edad.");
        }

        try {
            boolean existe = clienteDAO.existeCliente(
                cliente.getNombre(),
                cliente.getApellidoPaterno(),
                cliente.getApellidoMaterno(),
                cliente.getFechaNacimiento()
            );
            if (existe) {
                throw new NegocioException("El cliente ya está registrado.");
            }

            Cliente clienteAgregado = clienteDAO.agregarCliente(ClienteMapper.toEntity(cliente));
            return ClienteMapper.toDTO(clienteAgregado);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al agregar el cliente", e);
        }
    }

    /**
     * Obtiene todos los clientes registrados en el sistema.
     * 
     * @return Lista de objetos ClienteDTO.
     * @throws NegocioException Si ocurre un error al consultar la base de datos.
     */
    @Override
    public List<ClienteDTO> obtenerTodosLosClientes() throws NegocioException {
        try {
            return ClienteMapper.listToDTO(clienteDAO.obtenerTodosLosClientes());
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar con la base de datos", e);
        }
    }

    /**
     * Obtiene un cliente por su ID.
     * 
     * @param idCliente Identificador del cliente.
     * @return ClienteDTO con los datos del cliente encontrado.
     * @throws NegocioException Si ocurre un error al consultar la base de datos.
     */
    @Override
    public ClienteDTO obtenerClientePorId(int idCliente) throws NegocioException {
        try {
            return ClienteMapper.toDTO(clienteDAO.obtenerClientePorId(idCliente));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar con la base de datos", e);
        }
    }

    /**
     * Verifica si una cadena es nula o está vacía (solo espacios en blanco).
     * 
     * @param valor Cadena a verificar.
     * @return true si la cadena es nula o vacía, false en caso contrario.
     */
    private boolean esVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    /**
     * Valida si un cliente existe en la base de datos mediante su ID y contraseña.
     * 
     * @param id Identificador del cliente.
     * @param contrasenia Contraseña en formato numérico.
     * @return true si el cliente es válido, false en caso contrario.
     * @throws NegocioException Si ocurre un error al consultar la base de datos.
     */
    @Override
    public boolean validarCliente(int id, int contrasenia) throws NegocioException {
        try {
            return clienteDAO.validarCliente(id, contrasenia);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar con la base de datos", e);
        }
    }
}