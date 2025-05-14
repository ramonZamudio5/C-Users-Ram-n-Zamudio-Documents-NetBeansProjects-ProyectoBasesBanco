
import bos.ClienteBO;
import bos.CuentaBO;
import bos.RetiroSinCuentaBO;
import bos.TransferenciaBO;
import conexion.Conexion;
import conexion.IConexion;
import dtos.ClienteDTO;
import dtos.CuentaDTO;
import dtos.RetiroSinCuentaDTO;
import dtos.TransferenciaDTO;
import entidades.Cliente;
import enums.EstadoCuenta;
import excepciones.NegocioException;
import interfaces.IClienteBO;
import interfaces.ICuentaBO;
import interfaces.IRetiroSinCuentaBO;
import interfaces.ITransferenciaBO;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

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
    public static void main(String[] args) throws NegocioException {
        try{
            IConexion conexion = new Conexion();
//            IClienteBO clienteBO = new ClienteBO(conexion);
//            ClienteDTO cliente = new ClienteDTO("rradduñ", "xamudio", "dddd", "las flores", "orion", "ciudad", "85150", "sonora", 
//                  new Date(105, 10, 5));
//            System.out.println(clienteBO.agregarCliente(cliente));
//            System.out.println(clienteBO.obtenerClientePorId(1));
//            System.out.println(clienteBO.obtenerTodosLosClientes());
//            ICuentaBO cuentaBO = new CuentaBO(conexion);
//            CuentaDTO cuenta = new CuentaDTO(new Date(115, 10, 5), 1500.0, EstadoCuenta.ACTIVA, 1);
//            cuentaBO.agregarCuenta(cuenta);
//            System.out.println(cuentaBO.consultarCuentaPorId(1));
////            System.out.println(cuentaBO.editarEstadoCuenta(1, EstadoCuenta.ACTIVA));
//            System.out.println(cuentaBO.obtenerTodasLasCuentas());
            IRetiroSinCuentaBO retiroSinCuentaBO = new RetiroSinCuentaBO(conexion);
//            RetiroSinCuentaDTO retiro = new RetiroSinCuentaDTO(LocalDateTime.now(), 10.0, 1);
//            System.out.println(retiroSinCuentaBO.agregarRetiroSinCuenta(retiro));
//           System.out.println(retiroSinCuentaBO.verificarDatos(672630, "61521126"));
            System.out.println(retiroSinCuentaBO.realizarRetiro(511152, "49081656"));
//            ITransferenciaBO transferenciaBO = new TransferenciaBO(conexion);
//            TransferenciaDTO transferencia = new TransferenciaDTO(LocalDateTime.now(), 1500.0, 2, 1);
//            transferenciaBO.realizarTransferencia(transferencia);
            
        }catch(NegocioException e){
            throw new NegocioException("fallo", e);
        }
    
    }
}
