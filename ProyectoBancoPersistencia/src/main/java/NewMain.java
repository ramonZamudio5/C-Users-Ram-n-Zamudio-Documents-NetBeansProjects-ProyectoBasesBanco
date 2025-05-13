
import conexion.Conexion;
import conexion.IConexion;
import daos.ClienteDAO;
import daos.CuentaDAO;
import daos.RetiroSinCuentaDAO;
import daos.TransferenciaDAO;
import entidades.Cliente;
import entidades.Cuenta;
import entidades.RetiroSinCuenta;
import entidades.Transferencia;
import enums.EstadoCuenta;
import exception.PersistenciaException;
import java.sql.Date;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Ramón Zamudio
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PersistenciaException, SQLException {
        // TODO code application logic here
        IConexion conexion = new Conexion();
        ClienteDAO clienteDAO = new ClienteDAO(conexion);
        
        Cliente cliente = new Cliente("rauñ", "xamudio", "dddd", "las flores", "orion", "ciudad", "850150", "sonora", 
                new Date(112, 3, 11));
        clienteDAO.agregarCliente(cliente);
//        System.out.println(clienteDAO.obtenerClientePorId(1));
//        System.out.println(clienteDAO.obtenerTodosLosClientes());
//        
//        CuentaDAO cuentaDAO = new CuentaDAO(conexion);
//        Cuenta cuenta = new Cuenta(new Date(112, 3, 11), 5000.0, EstadoCuenta.ACTIVA, 1);
//        cuentaDAO.agregarCuenta(cuenta);
//        System.out.println(cuentaDAO.consultarCuentaPorId(1));
//        System.out.println(cuentaDAO.obtenerTodasLasCuentas());
//        System.out.println(cuentaDAO.editarEstadoCuenta(1, EstadoCuenta.CANCELADO));
//        
//        RetiroSinCuentaDAO rscDAO = new RetiroSinCuentaDAO(conexion);
////        RetiroSinCuenta retiro = new RetiroSinCuenta(new Date(112, 3, 11),  1.0, 1);
////        rscDAO.agregarRetiroSinCuenta(retiro);
//        
////        TransferenciaDAO transDAO = new TransferenciaDAO(conexion);
////        Transferencia trans = new Transferencia(new Date(112, 3, 11), 4000.0, 1, 2);
////        transDAO.realizarTransferencia(trans);
//        
//        RetiroSinCuenta retiro = new RetiroSinCuenta();
//        retiro.setFecha(new Date(112, 3, 11));
//        retiro.setMonto(10.0);
//        retiro.setIdCliente(1);
//        RetiroSinCuenta retiroGenerado = rscDAO.agregarRetiroSinCuenta(retiro);
//        rscDAO.realizarRetiro(retiroGenerado.getFolio(), retiroGenerado.getContrasenia(), 10.0);
//    }
    }
    
}
