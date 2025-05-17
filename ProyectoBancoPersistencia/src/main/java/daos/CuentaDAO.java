/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Interfaces.ICuentaDAO;
import conexion.IConexion;
import entidades.Cliente;
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
 * Implementación de la interfaz ICuentaDAO que proporciona operaciones
 * para gestionar las cuentas bancarias en la base de datos.
 * @author Ramón Zamudio
 */
public class CuentaDAO implements ICuentaDAO{
    IConexion conexion;
    /**
     * Constructor que inicializa el DAO con una conexión a la base de datos.
     * 
     * @param conexion La interfaz de conexión a utilizar.
     */
    public CuentaDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    /**
     * Agrega una nueva cuenta para un cliente en la base de datos.
     * 
     * @param cuenta La cuenta a agregar.
     * @return La cuenta con su ID generado.
     * @throws PersistenciaException Si ocurre un error durante la inserción o el cliente no existe.
     */
    @Override
    public Cuenta agregarCuenta(Cuenta cuenta) throws PersistenciaException {
        Cliente cliente = obtenerClientePorId(cuenta.getIdCliente());
        if (cliente == null) {
            throw new PersistenciaException("El cliente no existe.");
        }
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
    /**
     * Consulta una cuenta por su identificador.
     * 
     * @param id El ID de la cuenta.
     * @return La cuenta encontrada o null si no existe.
     * @throws PersistenciaException Si ocurre un error al realizar la consulta.
     */
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
     /**
     * Obtiene todas las cuentas activas de un cliente específico.
     * 
     * @param idCliente El ID del cliente.
     * @return Lista de cuentas activas del cliente.
     * @throws PersistenciaException Si ocurre un error al realizar la consulta.
     */
    @Override
    public List<Cuenta> obtenerTodasLasCuentas(int idCliente) throws PersistenciaException {
        String consultaSQL = "SELECT * FROM Cuenta WHERE id_cliente = ? and estado = 'ACTIVA'";
        List<Cuenta> cuentas = new ArrayList<>();

        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setInt(1, idCliente);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cuenta cuenta = new Cuenta();
                    cuenta.setId(rs.getInt("id_cuenta"));
                    cuenta.setFechaApertura(rs.getDate("fecha_apertura"));
                    cuenta.setSaldo(rs.getDouble("saldo"));
                    cuenta.setEstado(EstadoCuenta.valueOf(rs.getString("estado")));
                    cuenta.setIdCliente(rs.getInt("id_cliente"));

                    cuentas.add(cuenta);
                }
            }

        } catch (SQLException e) {
            throw new PersistenciaException("Error al consultar todas las cuentas del cliente", e);
        }

        return cuentas;
    }
     /**
     * Obtiene todas las cuentas de un cliente, sin importar el estado.
     * 
     * @param idCliente El ID del cliente.
     * @return Lista de todas las cuentas del cliente.
     * @throws PersistenciaException Si ocurre un error al realizar la consulta.
     */
    @Override
    public List<Cuenta> obtenerTodasLasCuentasSinImporarEstado(int idCliente) throws PersistenciaException {
        String consultaSQL = "SELECT * FROM Cuenta WHERE id_cliente = ?";
        List<Cuenta> cuentas = new ArrayList<>();

        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setInt(1, idCliente);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cuenta cuenta = new Cuenta();
                    cuenta.setId(rs.getInt("id_cuenta"));
                    cuenta.setFechaApertura(rs.getDate("fecha_apertura"));
                    cuenta.setSaldo(rs.getDouble("saldo"));
                    cuenta.setEstado(EstadoCuenta.valueOf(rs.getString("estado")));
                    cuenta.setIdCliente(rs.getInt("id_cliente"));

                    cuentas.add(cuenta);
                }
            }

        } catch (SQLException e) {
            throw new PersistenciaException("Error al consultar todas las cuentas del cliente", e);
        }

        return cuentas;
    }
    /**
     * Edita el estado de una cuenta (por ejemplo: ACTIVA, INACTIVA).
     * 
     * @param idCuenta El ID de la cuenta a modificar.
     * @param nuevoEstado El nuevo estado a establecer.
     * @return true si la operación fue exitosa.
     * @throws PersistenciaException Si ocurre un error al actualizar.
     */
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
    /**
     * Consulta los datos de un cliente por su ID.
     * 
     * @param idCliente El ID del cliente.
     * @return El cliente correspondiente o null si no existe.
     * @throws PersistenciaException Si ocurre un error al consultar.
     */
    public Cliente obtenerClientePorId(int idCliente) throws PersistenciaException {
        String consultaSQL = "SELECT id_cliente, nombre, apellidoPaterno, apellidoMaterno, calle, colonia, codigoPostal, estado, ciudad, fecha_nacimiento, TIMESTAMPDIFF(YEAR, fecha_nacimiento, CURDATE()) AS edad FROM cliente WHERE id_cliente = ?";
        Cliente cliente = null;

        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setInt(1, idCliente); 

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setId(rs.getInt("id_cliente"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellidoPaterno(rs.getString("apellidoPaterno"));
                    cliente.setApellidoMaterno(rs.getString("apellidoMaterno"));
                    cliente.setCalle(rs.getString("calle"));
                    cliente.setColonia(rs.getString("colonia"));
                    cliente.setCodigoPostal(rs.getString("codigoPostal"));
                    cliente.setEstado(rs.getString("estado"));
                    cliente.setCiudad(rs.getString("ciudad"));
                    cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                    cliente.setEdad(rs.getInt("edad")); 
                }

            } catch (SQLException e) {
                throw new PersistenciaException("Error al consultar el cliente por ID", e);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al preparar la consulta para obtener el cliente", e);
        }

        return cliente;
    }
}
