/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.CuentaDTO;
import entidades.Cuenta;
import enums.EstadoCuenta;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public interface ICuentaBO {
    public CuentaDTO agregarCuenta(Cuenta cuenta)throws NegocioException;
    public CuentaDTO consultarCuentaPorId(int id) throws NegocioException;
    public List<CuentaDTO> obtenerTodasLasCuentas() throws NegocioException;
    public boolean editarEstadoCuenta(int idCuenta, EstadoCuenta nuevoEstado) throws NegocioException;
}
