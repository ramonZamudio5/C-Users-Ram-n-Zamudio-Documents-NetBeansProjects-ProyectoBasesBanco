 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Interfaces.IClienteDAO;
import conexion.IConexion;
import entidades.Cliente;
import exception.PersistenciaException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase que implementa la interfaz IClienteDAO para realizar operaciones de persistencia
 * relacionadas con la entidad Cliente
 * @author Ramón Zamudio
 */
public class ClienteDAO implements IClienteDAO{
    IConexion conexion;
    /**
     * Constructor que recibe una implementación de IConexion para la gestión de conexiones a la base de datos
     * @param conexion Objeto que permite crear conexiones a la base de datos
     */
    public ClienteDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    /**
     * Agrega un nuevo cliente a la base de datos, generando automáticamente una contraseña.
     * @param cliente cliente Objeto Cliente con la información a guardar.
     * @return Cliente con su ID y contraseña generada asignados.
     * @throws PersistenciaException Si ocurre un error al insertar el cliente.
     */
    @Override
    public Cliente agregarCliente(Cliente cliente) throws PersistenciaException {
        int contraseniaGenerada = generarContraseniaAleatoria();
        cliente.setContrasenia(contraseniaGenerada);

        String consultaSQL = "INSERT INTO cliente(nombre, apellidoMaterno, apellidoPaterno, calle, colonia, codigoPostal, estado, ciudad, fecha_nacimiento, contrasenia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidoPaterno());
            ps.setString(3, cliente.getApellidoMaterno());
            ps.setString(4, cliente.getCalle());
            ps.setString(5, cliente.getColonia());
            ps.setString(6, cliente.getCodigoPostal());
            ps.setString(7, cliente.getEstado());
            ps.setString(8, cliente.getCiudad());
            ps.setDate(9, cliente.getFechaNacimiento());
            ps.setInt(10, contraseniaGenerada); 

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("La creación del cliente falló, no se insertó ninguna fila");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cliente.setId(generatedKeys.getInt(1));
                } else {
                    throw new PersistenciaException("La creación del cliente falló, no se obtuvo el ID generado");
                }
            }

            return cliente;
        } catch (SQLException e) {
            throw new PersistenciaException("La creación del cliente falló", e);
        }
    }
    /**
     * Genera una contraseña aleatoria de 8 dígitos para el cliente
     * @return Número entero que representa la contraseña
     */
    private int generarContraseniaAleatoria() {
        Random random = new Random();
        int numero = 10000000 + random.nextInt(90000000);
        return numero;
    }
    /**
     * Obtiene una lista con todos los clientes almacenados en la base de datos.
     * @return Lista de objetos Cliente
     * @throws PersistenciaException Si ocurre un error al consultar los clientes.
     */
    @Override
    public List<Cliente> obtenerTodosLosClientes() throws PersistenciaException {
        String consultaSQL = "SELECT id_cliente, nombre, apellidoPaterno, apellidoMaterno, calle, colonia, codigoPostal, estado, ciudad, fecha_nacimiento, TIMESTAMPDIFF(YEAR, fecha_nacimiento, CURDATE()) AS edad FROM cliente";
        List<Cliente> clientes = new ArrayList<>();

        try (Connection con = conexion.crearConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(consultaSQL)) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
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

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            throw new PersistenciaException("Error al consultar los clientes", e);
        }

        return clientes;
    }
    /**
     * Obtiene un cliente a partir de su ID.
     * @param idCliente Identificador único del cliente.
     * @return si se encuentra
     * @throws PersistenciaException Si ocurre un error al consultar el cliente.
     */
    @Override
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
    /**
     * Verifica si ya existe un cliente en la base de datos con los datos proporcionados.
     * @param nombre Nombre del cliente.
     * @param apellidoPaterno Apellido paterno del cliente.
     * @param apellidoMaterno Apellido materno del cliente.
     * @param fechaNacimiento Fecha de nacimiento del cliente.
     * @return true si el cliente existe, false si no.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    @Override
    public boolean existeCliente(String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento) throws PersistenciaException {
        String sql = "SELECT COUNT(*) FROM cliente WHERE nombre = ? AND apellidoPaterno = ? AND apellidoMaterno = ? AND fecha_nacimiento = ?";

        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, apellidoPaterno);
            ps.setString(3, apellidoMaterno);
            ps.setDate(4, new java.sql.Date(fechaNacimiento.getTime()));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            } catch (SQLException e) {
                throw new PersistenciaException("Error al ejecutar la consulta de existencia del cliente", e);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al preparar la consulta de existencia del cliente", e);
        }

        return false;
    }
    /**
     * Valida las credenciales de un cliente mediante su ID y contraseña.
     * @param id ID del cliente.
     * @param contrasenia Contraseña generada del cliente.
     * @return true si las credenciales son válidas, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    @Override
    public boolean validarCliente(int id, int contrasenia) throws PersistenciaException {
        String sql = "SELECT COUNT(*) FROM cliente WHERE id_cliente = ? AND contrasenia = ?";

        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setInt(2, contrasenia);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; 
                }
            } catch (SQLException e) {
                throw new PersistenciaException("Error al ejecutar la consulta de validación del cliente", e);
            }

        } catch (SQLException e) {
            throw new PersistenciaException("Error al preparar la consulta de validación del cliente", e);
        }

        return false;
    }
    
}
