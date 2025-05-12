/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.RetiroSinCuentaDTO;
import excepciones.NegocioException;

/**
 *
 * @author Ramón Zamudio
 */
public interface IRetiroSinCuentaBO {
    public RetiroSinCuentaDTO agregarRetiroSinCuenta(RetiroSinCuentaDTO retiro) throws NegocioException;
    public RetiroSinCuentaDTO solicitarRetiro(int folio, String contrasenia) throws NegocioException;
    public boolean realizarRetiro(int folio, String contrasenia, double monto) throws NegocioException;
}
