/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import bos.FabricaBOs;
import dtos.ClienteDTO;
import dtos.CuentaDTO;
import excepciones.NegocioException;
import excepciones.PresentacionException;
import interfaces.IClienteBO;
import interfaces.ICuentaBO;
import interfaces.IRetiroSinCuentaBO;
import interfaces.ITransferenciaBO;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public class ControlNavegacion {
    private final IClienteBO clienteBO;
    private final ICuentaBO cuentaBO;
    private final IRetiroSinCuentaBO retiroSinCuentaBO;
    private final ITransferenciaBO transferenciaBO;

    public ControlNavegacion() {
        clienteBO = FabricaBOs.getInstanceClienteBO();
        cuentaBO = FabricaBOs.getInstanceCuentaBO();
        retiroSinCuentaBO = FabricaBOs.getInstanceRetiroSinCuentaBO();
        transferenciaBO = FabricaBOs.getInstanceTransferenciaBO();
    }
    
    
    public void openFormPaginaPrincipal(){
        new PantallaPrincipal(this).setVisible(true);
    }
    public void openFormPantallaUsuarioSinCuenta(){
        new PantallaUsuarioSinCuenta(this).setVisible(true);
    }
    public void openFormCrearCuenta(){
        new CrearCuenta(this).setVisible(true);
    }
    public void openFormRetiroSinCuenta(){
        new RetiroSinCuenta(this).setVisible(true);
    }
    public void openFormpantallaLogIn(){
        new PantallaLogIn(this).setVisible(true);
    }
    public void openFormSeleccionarAccionCuenta(int idCliente){
        new SeleccionarAccionCuenta(this, idCliente).setVisible(true);
    }
    public void openFormElegirCuenta(String origen,int idCliente){
        new ElegirCuenta(this, origen,idCliente).setVisible(true);
    }
    
    
    
    
    
    
    
    
    
    public boolean realizarRetiro(int folio, String contrasenia)throws PresentacionException{
        try{
            return retiroSinCuentaBO.realizarRetiro(folio, contrasenia);
        }catch(NegocioException e){
            throw new PresentacionException("error al realizar el retiro", e);
        }
    }
    
    public ClienteDTO agregarCliente(ClienteDTO cliente) throws PresentacionException{
        try {
            return clienteBO.agregarCliente(cliente);
        } catch (NegocioException ex) {
            throw new PresentacionException("Error al agregar el cliente en la base de datos", ex);
        }
    }
    
    public boolean validarCliente(int id, int contrasenia) throws PresentacionException{
        try {
            return clienteBO.validarCliente(id, contrasenia);
        } catch (NegocioException ex) {
           throw new PresentacionException("Error al validar el cliente", ex);
        }
    }
    
    public List<CuentaDTO> obtenerTodasLasCuentas(int idCliente) throws PresentacionException{
        try {
            return cuentaBO.obtenerTodasLasCuentas(idCliente);
        } catch (NegocioException ex) {
            throw new PresentacionException("error al obetner las cuentas del cliente con id: "+idCliente, ex);
        }
        
    }
    
}
