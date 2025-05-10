/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.IConexion;
import entidades.Cliente;
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
public class ClienteDAO {
    IConexion conexion;

    public ClienteDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    
    public Cliente agregarCliente(Cliente cliente) throws PersistenciaException{
        String consultaSQL = "insert into cliente(nombre, apellidoMaterno, apellidoPaterno, calle, colonia, codigoPostal, estado, ciudad, fecha_nacimiento)values(?,?,?,?,?,?,?,?,?)";
        try(Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(consultaSQL,Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidoPaterno());
            ps.setString(3, cliente.getApellidoMaterno());
            ps.setString(4, cliente.getCalle());
            ps.setString(5, cliente.getColonia());
            ps.setString(6, cliente.getCodigoPostal());
            ps.setString(7, cliente.getEstado());
            ps.setString(8, cliente.getCiudad());
            ps.setDate(9, cliente.getFechaNacimiento());
            int filasAfectadas = ps.executeUpdate();
            if(filasAfectadas == 0){
                throw new PersistenciaException("la creacion de la consulta fallo no se inserto ninguna fila");
            }
            try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                if(generatedKeys.next()){
                    cliente.setId(generatedKeys.getInt(1));
                }else{
                    throw new PersistenciaException("la creacion de la consulta fallo no se inserto ninguna fila");
                }
            }
            return cliente;
        }catch(SQLException e){
            throw new PersistenciaException("la creacion del cliente fallo no se inserto ninguna fila",e);
        }       
    }
    
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
                cliente.setEdad(rs.getInt("edad")); // Asignar la edad calculada

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            throw new PersistenciaException("Error al consultar los clientes", e);
        }

        return clientes;
    }

    public Cliente obtenerClientePorId(int idCliente) throws PersistenciaException {
        String consultaSQL = "SELECT id_cliente, nombre, apellidoPaterno, apellidoMaterno, calle, colonia, codigoPostal, estado, ciudad, fecha_nacimiento, TIMESTAMPDIFF(YEAR, fecha_nacimiento, CURDATE()) AS edad FROM cliente WHERE id_cliente = ?";
        Cliente cliente = null;

        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setInt(1, idCliente);  // Asignar el parámetro id_cliente

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
                    cliente.setEdad(rs.getInt("edad"));  // Establecer la edad calculada desde la consulta SQL
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
