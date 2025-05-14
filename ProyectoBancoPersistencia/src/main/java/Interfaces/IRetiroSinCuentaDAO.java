/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import entidades.RetiroSinCuenta;
import exception.PersistenciaException;

/**
 *
 * @author Ramón Zamudio
 */
public interface IRetiroSinCuentaDAO {
    public RetiroSinCuenta agregarRetiroSinCuenta(RetiroSinCuenta retiro) throws PersistenciaException;
    public boolean realizarRetiro(int folio, String contrasenia) throws PersistenciaException;
}
