/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package conexion;

import exception.PersistenciaException;
import java.sql.Connection;

/**
 *
 * @author Ramón Zamudio
 */
public interface IConexion {
    public Connection crearConexion() throws PersistenciaException;
}
