/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import Interfaces.ICuentaDAO;
import conexion.IConexion;
import daos.CuentaDAO;
import dtos.CuentaDTO;
import entidades.Cuenta;
import enums.EstadoCuenta;
import excepciones.NegocioException;
import exception.PersistenciaException;
import interfaces.ICuentaBO;
import java.time.LocalDate;
import java.util.List;
import mappers.CuentaMapper;

/**
 * Implementación de la lógica de negocio para la gestión de cuentas.
 * Valida los datos y delega la persistencia al DAO correspondiente.
 * 
 * @author Ramón Zamudio
 */
public class CuentaBO implements ICuentaBO {
    private ICuentaDAO cuentaDAO;

    /**
     * Constructor que inicializa el DAO de cuentas con la conexión proporcionada.
     * 
     * @param conexion Objeto que permite crear conexiones a la base de datos.
     */
    public CuentaBO(IConexion conexion) {
        this.cuentaDAO = new CuentaDAO(conexion);
    }

    /**
     * Agrega una nueva cuenta al sistema después de validar sus datos.
     * 
     * @param cuenta Objeto DTO con los datos de la cuenta a agregar.
     * @return CuentaDTO con los datos de la cuenta registrada.
     * @throws NegocioException Si hay errores de validación o de persistencia.
     */
    @Override
    public CuentaDTO agregarCuenta(CuentaDTO cuenta) throws NegocioException {
        if (cuenta == null) {
            throw new NegocioException("La cuenta no puede ser nula.");
        }

        if (cuenta.getSaldo() == null || cuenta.getSaldo() < 0) {
            throw new NegocioException("El saldo inicial no puede ser nulo ni negativo.");
        }

        if (cuenta.getFechaApertura() == null) {
            throw new NegocioException("La fecha de apertura es obligatoria.");
        }

        LocalDate apertura = cuenta.getFechaApertura().toLocalDate();
        if (apertura.isAfter(LocalDate.now())) {
            throw new NegocioException("La fecha de apertura no puede ser futura.");
        }

        if (cuenta.getEstado() == null) {
            throw new NegocioException("El estado de la cuenta es obligatorio.");
        }

        if (cuenta.getIdCliente() <= 0) {
            throw new NegocioException("ID de cliente inválido.");
        }

        try {
            Cuenta cuentaAgregada = cuentaDAO.agregarCuenta(CuentaMapper.toEntity(cuenta));
            return CuentaMapper.toDTO(cuentaAgregada);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al agregar la cuenta", e);
        }
    }

    /**
     * Consulta los datos de una cuenta por su ID.
     * 
     * @param id Identificador de la cuenta.
     * @return CuentaDTO con los datos de la cuenta encontrada.
     * @throws NegocioException Si ocurre un error al consultar la base de datos.
     */
    @Override
    public CuentaDTO consultarCuentaPorId(int id) throws NegocioException {
        try {
            return CuentaMapper.toDTO(cuentaDAO.consultarCuentaPorId(id));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar con la base de datos", e);
        }
    }

    /**
     * Obtiene todas las cuentas activas de un cliente.
     * 
     * @param idCliente Identificador del cliente.
     * @return Lista de objetos CuentaDTO asociadas al cliente.
     * @throws NegocioException Si el ID es inválido o hay un error de persistencia.
     */
    @Override
    public List<CuentaDTO> obtenerTodasLasCuentas(int idCliente) throws NegocioException {
        if (idCliente <= 0) {
            throw new NegocioException("El id no puede ser menor a 0");
        }

        try {
            return CuentaMapper.listToDTO(cuentaDAO.obtenerTodasLasCuentas(idCliente));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar con la base de datos", e);
        }
    }

    /**
     * Edita el estado de una cuenta (por ejemplo, activar o cancelar).
     * 
     * @param idCuenta ID de la cuenta a modificar.
     * @param nuevoEstado Nuevo estado de la cuenta ("ACTIVA" o "CANCELADO").
     * @return true si el cambio fue exitoso, false si no se pudo realizar.
     * @throws NegocioException Si los datos son inválidos o la cuenta ya tiene el estado deseado.
     */
    @Override
    public boolean editarEstadoCuenta(int idCuenta, String nuevoEstado) throws NegocioException {
        if (nuevoEstado == null) {
            throw new NegocioException("El nuevo estado no puede ser nulo.");
        }

        EstadoCuenta enumEstado = nuevoEstado.equals("ACTIVA") ? EstadoCuenta.ACTIVA : EstadoCuenta.CANCELADO;

        try {
            Cuenta cuenta = cuentaDAO.consultarCuentaPorId(idCuenta);
            if (cuenta == null) {
                throw new NegocioException("La cuenta no existe.");
            }

            if (cuenta.getEstado() == enumEstado) {
                throw new NegocioException("La cuenta ya tiene el estado especificado.");
            }

            return cuentaDAO.editarEstadoCuenta(idCuenta, enumEstado);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al editar estado de cuenta", e);
        }
    }

    /**
     * Obtiene todas las cuentas de un cliente sin importar su estado (activas o canceladas).
     * 
     * @param idCliente Identificador del cliente.
     * @return Lista de objetos CuentaDTO con todas las cuentas.
     * @throws NegocioException Si el ID es inválido o hay un error en la consulta.
     */
    @Override
    public List<CuentaDTO> obtenerTodasLasCuentasSinImporarEstado(int idCliente) throws NegocioException {
        if (idCliente <= 0) {
            throw new NegocioException("El id no puede ser menor a 0");
        }

        try {
            return CuentaMapper.listToDTO(cuentaDAO.obtenerTodasLasCuentasSinImporarEstado(idCliente));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar con la base de datos", e);
        }
    }
}