/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Interfaces.ICuentaDAO;
import conexion.IConexion;
import entidades.Cuenta;
import enums.EstadoCuenta;
import exception.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public class CuentaDAO implements ICuentaDAO{
    IConexion conexion;

    public CuentaDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public Cuenta agregarCuenta(Cuenta cuenta) throws PersistenciaException {
        String consultaSQL = "INSERT INTO Cuenta(fecha_apertura, saldo, estado, id_cliente) VALUES (?, ?, ?, ?)";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDate(1, cuenta.getFechaApertura());
            ps.setDouble(2, cuenta.getSaldo());
            ps.setString(3, cuenta.getEstado().toString());
            ps.setInt(4, cuenta.getIdCliente()); 

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("La creación de la cuenta falló, no se insertó ninguna fila");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cuenta.setId(generatedKeys.getInt(1));
                } else {
                    throw new PersistenciaException("La creación de la cuenta falló, no se obtuvo el ID generado");
                }
            }

            return cuenta;
        } catch (SQLException e) {
            throw new PersistenciaException("Error al insertar la cuenta", e);
        }
    }
    
    @Override
    public Cuenta consultarCuentaPorId(int id) throws PersistenciaException {
        String consultaSQL = "SELECT * FROM Cuenta WHERE id_cuenta = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cuenta cuenta = new Cuenta();
                    cuenta.setId(rs.getInt("id_cuenta"));
                    cuenta.setFechaApertura(rs.getDate("fecha_apertura"));
                    cuenta.setSaldo(rs.getDouble("saldo"));
                    cuenta.setEstado(EstadoCuenta.valueOf(rs.getString("estado"))); // Convertir el String a enum
                    cuenta.setIdCliente(rs.getInt("id_cliente"));

                    return cuenta;
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al consultar la cuenta por ID", e);
        }
        return null;
    }
    
    @Override
    public List<Cuenta> obtenerTodasLasCuentas() throws PersistenciaException {
        String consultaSQL = "SELECT * FROM Cuenta";
        List<Cuenta> cuentas = new ArrayList<>();

        try (Connection con = conexion.crearConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(consultaSQL)) {

            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getInt("id_cuenta"));
                cuenta.setFechaApertura(rs.getDate("fecha_apertura"));
                cuenta.setSaldo(rs.getDouble("saldo"));
                cuenta.setEstado(EstadoCuenta.valueOf(rs.getString("estado"))); // Convertir el String a enum
                cuenta.setIdCliente(rs.getInt("id_cliente"));

                cuentas.add(cuenta);
            }

        } catch (SQLException e) {
            throw new PersistenciaException("Error al consultar todas las cuentas", e);
        }

        return cuentas;
    }
    
    @Override
    public boolean editarEstadoCuenta(int idCuenta, EstadoCuenta nuevoEstado) throws PersistenciaException {
        String consultaSQL = "UPDATE Cuenta SET estado = ? WHERE id_cuenta = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setString(1, nuevoEstado.toString()); 
            ps.setInt(2, idCuenta);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("No se pudo actualizar el estado de la cuenta");
            }
            return true;
        } catch (SQLException e) {
            throw new PersistenciaException("Error al actualizar el estado de la cuenta", e);
        }
    }
    
    
}
