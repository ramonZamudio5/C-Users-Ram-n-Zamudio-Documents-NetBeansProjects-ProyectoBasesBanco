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
    /**
     * Agrega un nuevo retiro sin cuenta, generando un folio y contraseña aleatoria.
     *
     * @param retiro Objeto RetiroSinCuenta con los datos del retiro.
     * @return El objeto RetiroSinCuenta actualizado con folio y contraseña generados.
     * @throws PersistenciaException Si ocurre un error al insertar en la base de datos.
     */
    public RetiroSinCuenta agregarRetiroSinCuenta(RetiroSinCuenta retiro) throws PersistenciaException;
    /**
     * Realiza un retiro si el folio y la contraseña coinciden con un registro válido.
     *
     * @param folio Folio del retiro.
     * @param contrasenia Contraseña del retiro.
     * @return true si el retiro se realiza correctamente, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante el proceso.
     */
    public boolean realizarRetiro(int folio, String contrasenia) throws PersistenciaException;
}
