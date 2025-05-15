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
    public Cuenta agregarCuenta(Cuenta cuenta) throws PersistenciaException;
    public Cuenta consultarCuentaPorId(int id) throws PersistenciaException;
    public List<Cuenta> obtenerTodasLasCuentas(int idCliente) throws PersistenciaException ;
    public boolean editarEstadoCuenta(int idCuenta, EstadoCuenta nuevoEstado) throws PersistenciaException;
}
