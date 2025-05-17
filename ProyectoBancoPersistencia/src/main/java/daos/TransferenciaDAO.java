    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Interfaces.ITransferenciaDAO;
import conexion.IConexion;
import entidades.Transferencia;
import exception.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Implementación del DAO para manejar operaciones de transferencias entre cuentas.
 * Verifica el saldo, actualiza los saldos de origen y destino, e inserta el registro de la transferencia.
 * 
 * @author Ramón Zamudio
 */
public class TransferenciaDAO implements ITransferenciaDAO{
    IConexion conexion;
    /**
     * Constructor que recibe una conexión a la base de datos.
     * 
     * @param conexion Objeto que administra la conexión a la base de datos.
     */
    public TransferenciaDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    /**
     * Realiza una transferencia entre dos cuentas si hay suficiente saldo en la cuenta de origen.
     * 
     * @param transferencia Objeto que contiene los detalles de la transferencia.
     * @return El objeto Transferencia insertado.
     * @throws PersistenciaException Si no hay saldo suficiente o si ocurre un error durante el proceso.
     */
    @Override
    public Transferencia realizarTransferencia(Transferencia transferencia) throws PersistenciaException {
        if (verificarSaldoCuenta(transferencia.getIdOrigen(), transferencia.getMonto())) {
            actualizarSaldoCuenta(transferencia.getIdOrigen(), -transferencia.getMonto());
            actualizarSaldoCuenta(transferencia.getIdDestino(), transferencia.getMonto());
            insertarTransferencia(transferencia);
            return transferencia;
        } else {
            throw new PersistenciaException("Saldo insuficiente en la cuenta de origen para realizar la transferencia.");
        }
    }
    /**
     * Verifica si una cuenta tiene saldo suficiente para realizar una transferencia.
     * 
     * @param idCuenta ID de la cuenta de origen.
     * @param monto Monto que se desea transferir.
     * @return true si el saldo es suficiente, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante la consulta a la base de datos.
     */ 
    private boolean verificarSaldoCuenta(int idCuenta, Double monto) throws PersistenciaException {
        String consultaSQL = "SELECT saldo FROM Cuenta WHERE id_cuenta = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setInt(1, idCuenta);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double saldo = rs.getDouble("saldo");
                return saldo >= monto;
            }

            return false;
        } catch (SQLException e) {
            throw new PersistenciaException("Error al verificar saldo de la cuenta", e);
        }
    }
    /**
     * Actualiza el saldo de una cuenta sumando el monto proporcionado.
     * 
     * @param idCuenta ID de la cuenta a actualizar.
     * @param monto Monto a sumar (puede ser negativo para restar).
     * @throws PersistenciaException Si ocurre un error al ejecutar la actualización.
     */
    private void actualizarSaldoCuenta(int idCuenta, Double monto) throws PersistenciaException {
        String consultaSQL = "UPDATE Cuenta SET saldo = saldo + ? WHERE id_cuenta = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setDouble(1, monto);
            ps.setInt(2, idCuenta);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Error al actualizar saldo de la cuenta", e);
        }
    }
    /**
     * Inserta un nuevo registro de transferencia en la base de datos.
     * 
     * @param transferencia Objeto Transferencia con los datos a insertar.
     * @throws PersistenciaException Si ocurre un error durante la inserción.
     */
    private void insertarTransferencia(Transferencia transferencia) throws PersistenciaException {
        String consultaSQL = "INSERT INTO Transferencia (fecha, monto, id_origen, id_destino) VALUES (?, ?, ?, ?)";
        try (Connection con = conexion.crearConexion();
            PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setTimestamp(1, Timestamp.valueOf(transferencia.getFecha()));
            ps.setDouble(2, transferencia.getMonto());
            ps.setInt(3, transferencia.getIdOrigen());
            ps.setInt(4, transferencia.getIdDestino());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Error al insertar la transferencia", e);
        }
    }
}

