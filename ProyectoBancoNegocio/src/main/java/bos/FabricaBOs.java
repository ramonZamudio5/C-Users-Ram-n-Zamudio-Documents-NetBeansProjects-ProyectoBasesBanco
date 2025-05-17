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
 * Clase de utilidad que implementa el patrón Factory (Fábrica).
 * Provee instancias de objetos de negocio (BO) para ser utilizados en la capa de aplicación.
 * Cada método genera una nueva instancia del BO correspondiente junto con su conexión asociada.
 * 
 * @author Ramón Zamudio
 */
public class FabricaBOs {

    /**
     * Crea una nueva instancia de IClienteBO con su respectiva conexión.
     * 
     * @return Una implementación de {@link IClienteBO}.
     */
    public static IClienteBO getInstanceClienteBO() {
        IConexion conexion = new Conexion();
        return new ClienteBO(conexion);
    }

    /**
     * Crea una nueva instancia de ICuentaBO con su respectiva conexión.
     * 
     * @return Una implementación de {@link ICuentaBO}.
     */
    public static ICuentaBO getInstanceCuentaBO() {
        IConexion conexion = new Conexion();
        return new CuentaBO(conexion);
    }

    /**
     * Crea una nueva instancia de IRetiroSinCuentaBO con su respectiva conexión.
     * 
     * @return Una implementación de {@link IRetiroSinCuentaBO}.
     */
    public static IRetiroSinCuentaBO getInstanceRetiroSinCuentaBO() {
        IConexion conexion = new Conexion();
        return new RetiroSinCuentaBO(conexion);
    }

    /**
     * Crea una nueva instancia de ITransferenciaBO con su respectiva conexión.
     * 
     * @return Una implementación de {@link ITransferenciaBO}.
     */
    public static ITransferenciaBO getInstanceTransferenciaBO() {
        IConexion conexion = new Conexion();
        return new TransferenciaBO(conexion);
    }
}