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

/**
 *
 * @author Ramón Zamudio
 */
public class TransferenciaDAO implements ITransferenciaDAO{
    IConexion conexion;

    public TransferenciaDAO(IConexion conexion) {
        this.conexion = conexion;
    }

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

    private void insertarTransferencia(Transferencia transferencia) throws PersistenciaException {
        String consultaSQL = "INSERT INTO Transferencia (fecha, monto, id_origen, id_destino) VALUES (?, ?, ?, ?)";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setDate(1, new java.sql.Date(transferencia.getFecha().getTime()));
            ps.setDouble(2, transferencia.getMonto());
            ps.setInt(3, transferencia.getIdOrigen());
            ps.setInt(4, transferencia.getIdDestino());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Error al insertar la transferencia", e);
        }
    }
}

