/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import conexion.Conexion;
import conexion.IConexion;
import interfaces.IClienteBO;
import interfaces.ICuentaBO;
import interfaces.IRetiroSinCuentaBO;
import interfaces.ITransferenciaBO;

/**
 *
 * @author Ramón Zamudio
 */
public class FabricaBOs {
    public static IClienteBO getInstanceClienteBO(){
        IConexion conexion = new Conexion();
        IClienteBO clienteBO = new ClienteBO(conexion);
        return clienteBO;
    }
    public static ICuentaBO getInstanceCuentaBO(){
        IConexion conexion = new Conexion();
        ICuentaBO cuentaBO = new CuentaBO(conexion);
        return cuentaBO;
    }
    public static IRetiroSinCuentaBO getInstanceRetiroSinCuentaBO(){
        IConexion conexion = new Conexion();
        IRetiroSinCuentaBO retiroSinCuentaBO = new RetiroSinCuentaBO(conexion);
        return retiroSinCuentaBO;
    }
    public static ITransferenciaBO getInstanceTransferenciaBO(){
        IConexion conexion = new Conexion();
        ITransferenciaBO transferenciaBO = new TransferenciaBO(conexion);
        return transferenciaBO;
    }
    
}
