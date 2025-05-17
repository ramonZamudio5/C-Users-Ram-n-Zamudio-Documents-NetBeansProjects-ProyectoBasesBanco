/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import entidades.Cuenta;
import enums.EstadoCuenta;
import exception.PersistenciaException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public interface ICuentaDAO {
    /**
     * Agrega una nueva cuenta para un cliente en la base de datos.
     * 
     * @param cuenta La cuenta a agregar.
     * @return La cuenta con su ID generado.
     * @throws PersistenciaException Si ocurre un error durante la inserción o el cliente no existe.
     */
    public Cuenta agregarCuenta(Cuenta cuenta) throws PersistenciaException;
    /**
     * Consulta una cuenta por su identificador.
     * 
     * @param id El ID de la cuenta.
     * @return La cuenta encontrada o null si no existe.
     * @throws PersistenciaException Si ocurre un error al realizar la consulta.
     */
    public Cuenta consultarCuentaPorId(int id) throws PersistenciaException;
    /**
     * Obtiene todas las cuentas activas de un cliente específico.
     * 
     * @param idCliente El ID del cliente.
     * @return Lista de cuentas activas del cliente.
     * @throws PersistenciaException Si ocurre un error al realizar la consulta.
     */
    public List<Cuenta> obtenerTodasLasCuentas(int idCliente) throws PersistenciaException ;
    /**
     * Edita el estado de una cuenta (por ejemplo: ACTIVA, INACTIVA).
     * 
     * @param idCuenta El ID de la cuenta a modificar.
     * @param nuevoEstado El nuevo estado a establecer.
     * @return true si la operación fue exitosa.
     * @throws PersistenciaException Si ocurre un error al actualizar.
     */
    public boolean editarEstadoCuenta(int idCuenta, EstadoCuenta nuevoEstado) throws PersistenciaException;
    /**
     * Obtiene todas las cuentas de un cliente, sin importar el estado.
     * 
     * @param idCliente El ID del cliente.
     * @return Lista de todas las cuentas del cliente.
     * @throws PersistenciaException Si ocurre un error al realizar la consulta.
     */
    public List<Cuenta> obtenerTodasLasCuentasSinImporarEstado(int idCliente) throws PersistenciaException ;
}
