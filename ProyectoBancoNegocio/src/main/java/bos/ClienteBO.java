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
 *
 * @author Ramón Zamudio
 */
public class ClienteBO implements IClienteBO{
    private IClienteDAO clienteDAO;

    public ClienteBO(IConexion conexion) {
        this.clienteDAO = new ClienteDAO(conexion);
    }
    
    @Override
    public ClienteDTO agregarCliente(ClienteDTO cliente) throws NegocioException{
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
        try{
            boolean existe = clienteDAO.existeCliente(cliente.getNombre(), cliente.getApellidoPaterno(), cliente.getApellidoMaterno(), cliente.getFechaNacimiento());
            if (existe) {
                throw new NegocioException("El cliente ya está registrado.");
            }
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
    
    private boolean esVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    @Override
    public boolean validarCliente(int id, int contrasenia) throws NegocioException {
        try{
            return clienteDAO.validarCliente(id, contrasenia);
        }catch(PersistenciaException e){
            throw new NegocioException("Error al consultar con la base de datos", e);
        }
    }
    
}
