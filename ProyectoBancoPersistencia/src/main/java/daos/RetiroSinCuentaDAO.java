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
 * Implementación del DAO para operaciones relacionadas con retiros sin cuenta.
 * Permite insertar y verificar retiros usando folios y contraseñas encriptadas.
 */
public class RetiroSinCuentaDAO implements IRetiroSinCuentaDAO{
    IConexion conexion;
    /**
     * Constructor que recibe una conexión a la base de datos.
     * 
     * @param conexion Objeto que maneja la conexión a la base de datos.
     */
    public RetiroSinCuentaDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    /**
     * Agrega un nuevo retiro sin cuenta, generando un folio y contraseña aleatoria.
     *
     * @param retiro Objeto RetiroSinCuenta con los datos del retiro.
     * @return El objeto RetiroSinCuenta actualizado con folio y contraseña generados.
     * @throws PersistenciaException Si ocurre un error al insertar en la base de datos.
     */
    @Override
    public RetiroSinCuenta agregarRetiroSinCuenta(RetiroSinCuenta retiro) throws PersistenciaException {
        int folioGenerado = generarFolioAleatorio();
        String contraseniaGenerada = generarContraseniaAleatoria();
        retiro.setFolio(folioGenerado);
        retiro.setContrasenia(contraseniaGenerada);
        try{
            insertarRetiroSinCuenta(retiro);
            return retiro;
        }catch(PersistenciaException e){
            throw new PersistenciaException("error al agregar el retiro en la base de datos", e);
        }
    }
    /**
     * Genera un número de folio aleatorio.
     *
     * @return Un número entero entre 0 y 999999.
     */
    private int generarFolioAleatorio() {
        return (int) (Math.random() * 1000000);
    }
    /**
     * Genera una contraseña numérica aleatoria de 8 dígitos.
     *
     * @return Una cadena de 8 dígitos.
     */
    private String generarContraseniaAleatoria() {
        return String.format("%08d", (int) (Math.random() * 100000000));
    }
    /**
     * Resta un monto al saldo de una cuenta específica.
     *
     * @param idCuenta ID de la cuenta.
     * @param monto Monto a retirar.
     * @throws PersistenciaException Si ocurre un error al actualizar el saldo.
     */
   private void actualizarSaldoCuenta(int idCuenta, Double monto) throws PersistenciaException {
        String consultaSQL = "UPDATE cuenta SET saldo = saldo - ? WHERE id_cuenta = ?";
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
     * Inserta un nuevo retiro sin cuenta en la base de datos.
     *
     * @param retiro Objeto RetiroSinCuenta a insertar.
     * @throws PersistenciaException Si ocurre un error durante la inserción.
     */
    private void insertarRetiroSinCuenta(RetiroSinCuenta retiro) throws PersistenciaException {
        String consultaSQL = "INSERT INTO RetiroSinCuenta (folio, fecha, contrasenia, monto, id_cuenta) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setInt(1, retiro.getFolio());
            ps.setTimestamp(2, Timestamp.valueOf(retiro.getFecha()));
            String contraseniaEncriptada = encriptarSHA256(retiro.getContrasenia());
            ps.setString(3, contraseniaEncriptada);
            ps.setDouble(4, retiro.getMonto());
            ps.setInt(5, retiro.getIdCuenta());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Error al insertar el retiro sin cuenta", e);
        }
    }
    /**
     * Verifica que el folio y la contraseña correspondan a un retiro válido.
     *
     * @param folio Número de folio del retiro.
     * @param contrasenia Contraseña proporcionada.
     * @return Objeto RetiroSinCuenta correspondiente si los datos son válidos.
     * @throws PersistenciaException Si la verificación falla o ocurre un error de conexión.
     */
    private RetiroSinCuenta verificarDatos(int folio, String contrasenia) throws PersistenciaException {
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
                    Timestamp timestamp = rs.getTimestamp("fecha");
                    if (timestamp != null) {
                        retiro.setFecha(timestamp.toLocalDateTime());
                    }

                    retiro.setMonto(rs.getDouble("monto"));
                    retiro.setIdCuenta(rs.getInt("id_cuenta"));
                    retiro.setContrasenia(contrasenia);

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
    /**
     * Realiza un retiro si el folio y la contraseña coinciden con un registro válido.
     *
     * @param folio Folio del retiro.
     * @param contrasenia Contraseña del retiro.
     * @return true si el retiro se realiza correctamente, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante el proceso.
     */
    @Override
    public boolean realizarRetiro(int folio, String contrasenia) throws PersistenciaException {
        RetiroSinCuenta retiro = verificarDatos(folio, contrasenia);
        try {
            if (retiro != null) {
                Double monto = obtenerMontoPorFolio(folio); 
                if (monto != null) {
                    actualizarSaldoCuenta(retiro.getIdCuenta(), monto);
                    System.out.println("funciono");
                    return true;
                }
            }
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al realizar el retiro", e);
        }
        return false;
    }
    /**
     * Encripta una contraseña utilizando el algoritmo SHA-256.
     *
     * @param contrasenia Contraseña en texto plano.
     * @return Cadena encriptada en formato hexadecimal.
     */
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
    /**
     * Obtiene el monto asociado a un retiro según el folio.
     *
     * @param folio Número de folio del retiro.
     * @return Monto del retiro o null si no se encuentra.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    private Double obtenerMontoPorFolio(int folio) throws PersistenciaException {
        String consultaSQL = "SELECT monto FROM retirosincuenta WHERE folio = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setInt(1, folio);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("monto");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al obtener monto del retiro con folio: " + folio, e);
        }
    }
}
