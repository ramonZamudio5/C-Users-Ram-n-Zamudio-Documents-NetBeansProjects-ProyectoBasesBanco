/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import exception.PersistenciaException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ramón Zamudio
 */
public class Conexion implements IConexion{

    
    final String USUARIO = "root";
    final String PASS = "ramonsebas1";

    final String CADENA_CONEXION = "jdbc:mysql://localhost:3306/banco";

    @Override
    public Connection crearConexion() throws PersistenciaException {

        try {
            Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASS);
            return conexion;
        } catch (SQLException ex) {

            throw new PersistenciaException("Error al conectar a la base de datos", ex);

        }

    }
}
