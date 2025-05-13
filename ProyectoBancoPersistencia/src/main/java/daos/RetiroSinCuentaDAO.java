/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Interfaces.IRetiroSinCuentaDAO;
import conexion.IConexion;
import entidades.RetiroSinCuenta;
import exception.PersistenciaException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Ramón Zamudio
 */
public class RetiroSinCuentaDAO implements IRetiroSinCuentaDAO{
    IConexion conexion;

    public RetiroSinCuentaDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public RetiroSinCuenta agregarRetiroSinCuenta(RetiroSinCuenta retiro) throws PersistenciaException {
        int folioGenerado = generarFolioAleatorio();
        String contraseniaGenerada = generarContraseniaAleatoria();
        retiro.setFolio(folioGenerado);
        retiro.setContrasenia(contraseniaGenerada);
        Integer idCuenta = obtenerCuentaConSaldoSuficiente(retiro.getIdCliente(), retiro.getMonto());

        if (idCuenta != null) {
            insertarRetiroSinCuenta(retiro);
            return retiro;
        } else {
            throw new PersistenciaException("Saldo insuficiente en las cuentas del cliente para realizar el retiro.");
        }
    }

    private int generarFolioAleatorio() {
        return (int) (Math.random() * 1000000);
    }

    private String generarContraseniaAleatoria() {
        return String.format("%08d", (int) (Math.random() * 100000000));
    }

    private void actualizarSaldoCuenta(int idCuenta, Double monto) throws PersistenciaException {
        String consultaSQL = "UPDATE cuenta SET saldo = saldo + ? WHERE id_cuenta = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setDouble(1, monto);
            ps.setInt(2, idCuenta);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Error al actualizar saldo de la cuenta", e);
        }
    }

    private void insertarRetiroSinCuenta(RetiroSinCuenta retiro) throws PersistenciaException {
        String consultaSQL = "INSERT INTO RetiroSinCuenta (folio, fecha, contrasenia, monto, id_cliente) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setInt(1, retiro.getFolio());
            ps.setTimestamp(2, Timestamp.valueOf(retiro.getFecha()));
            String contraseniaEncriptada = encriptarSHA256(retiro.getContrasenia());
            ps.setString(3, contraseniaEncriptada);
            ps.setDouble(4, retiro.getMonto());
            ps.setInt(5, retiro.getIdCliente());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Error al insertar el retiro sin cuenta", e);
        }
    }

    @Override
    public RetiroSinCuenta verificarDatos(int folio, String contrasenia) throws PersistenciaException {
        String consultaSQL = "SELECT * FROM RetiroSinCuenta WHERE folio = ?";

        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setInt(1, folio);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String contraseniaAlmacenada = rs.getString("contrasenia");

                if (encriptarSHA256(contrasenia).equals(contraseniaAlmacenada)) {
                    RetiroSinCuenta retiro = new RetiroSinCuenta();
                    retiro.setFolio(rs.getInt("folio"));

                    // Convertimos Timestamp a LocalDateTime
                    Timestamp timestamp = rs.getTimestamp("fecha");
                    if (timestamp != null) {
                        retiro.setFecha(timestamp.toLocalDateTime());
                    }

                    retiro.setMonto(rs.getDouble("monto"));
                    retiro.setIdCliente(rs.getInt("id_cliente"));
                    retiro.setContrasenia(contrasenia); // Mejor no devolver la contraseña encriptada

                    return retiro;
                } else {
                    throw new PersistenciaException("Contraseña incorrecta.");
                }
            } else {
                throw new PersistenciaException("Folio no encontrado.");
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al solicitar el retiro", e);
        }
    }

    @Override
    public boolean realizarRetiro(int folio, String contrasenia, double monto) throws PersistenciaException {
        RetiroSinCuenta retiro = verificarDatos(folio, contrasenia);
        if (retiro != null) {
            Integer idCuenta = obtenerCuentaConSaldoSuficiente(retiro.getIdCliente(), monto);
            if (idCuenta != null) {
                actualizarSaldoCuenta(idCuenta, -monto); 
                return true;
            } else {
                throw new PersistenciaException("Saldo insuficiente en la cuenta para realizar el retiro.");
            }
        } else {
            throw new PersistenciaException("Folio o contraseña incorrectos.");
        }
    }

    private Integer obtenerCuentaConSaldoSuficiente(int idCliente, double monto) throws PersistenciaException {
        String consultaSQL = "SELECT id_cuenta, saldo FROM Cuenta WHERE id_cliente = ? AND saldo >= ? LIMIT 1";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setInt(1, idCliente);
            ps.setDouble(2, monto);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_cuenta");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al buscar cuenta con saldo suficiente", e);
        }
    }

    private String encriptarSHA256(String contrasenia) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(contrasenia.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al encriptar la contraseña", e);
        }
    }
}