/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import bos.FabricaBOs;
import dtos.ClienteDTO;
import dtos.CuentaDTO;
import dtos.RetiroSinCuentaDTO;
import dtos.TransferenciaDTO;
import excepciones.NegocioException;
import excepciones.PresentacionException;
import interfaces.IClienteBO;
import interfaces.ICuentaBO;
import interfaces.IRetiroSinCuentaBO;
import interfaces.ITransferenciaBO;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
    public void openFormEditarEstado(int idCuenta){
        new EditarEstado(this, idCuenta).setVisible(true);
    }
    public void openFormSolicitarRetiro(int idCuenta){
        new SolicitarRetiro(this, idCuenta).setVisible(true);
    }
    public void openFormTransferencia(int idCuenta){
        new Transferencia(this, idCuenta).setVisible(true);
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
    public boolean editarEstado(int idCuenta, String estado) throws PresentacionException{
        try {
            return cuentaBO.editarEstadoCuenta(idCuenta, estado);
        } catch (NegocioException ex) {
            throw new PresentacionException("Error al editar el estado de la cuenta", ex);
        }
    }
    
    public CuentaDTO consultarCuentaPorId(int idCuenta)throws PresentacionException{
        try {
            return cuentaBO.consultarCuentaPorId(idCuenta);
        } catch (NegocioException ex) {
            throw new PresentacionException("Error al consultar la cuenta en la base de datos", ex);
        }
    }
    
    public RetiroSinCuentaDTO agregarRetiroSinCuenta(RetiroSinCuentaDTO retiro) throws PresentacionException{
        try {
            return retiroSinCuentaBO.agregarRetiroSinCuenta(retiro);
        } catch (NegocioException ex) {
            throw new PresentacionException("Error al agregar el retiro en la base de datos", ex);
        }
    }
    
    public TransferenciaDTO realizarTransferencia(TransferenciaDTO transferencia)throws PresentacionException{
        try {
            return transferenciaBO.realizarTransferencia(transferencia);
        } catch (NegocioException ex) {
            throw new PresentacionException("Error al transferir el dinero", ex);
        }
    }
    
     public List<CuentaDTO> obtenerTodasLasCuentasSinImporarEstado(int idCliente) throws PresentacionException{
        try {
            return cuentaBO.obtenerTodasLasCuentasSinImporarEstado(idCliente);
        } catch (NegocioException ex) {
            throw new PresentacionException("Error al consultar las cuentas", ex);
        }
     }
    
    public void mostrarMensajeRetiroExitoso() {
        JDialog dialogo = new JDialog();
        dialogo.setTitle("Mensaje");
        dialogo.setModal(true);
        dialogo.setSize(300, 200);
        dialogo.setLocationRelativeTo(null);
        dialogo.setLayout(new BorderLayout());
        JLabel mensaje = new JLabel("Retiro Exitoso", SwingConstants.CENTER);
        mensaje.setFont(new Font("Arial", Font.PLAIN, 18));
        dialogo.add(mensaje, BorderLayout.CENTER);

        JButton boton = new JButton("Regresar al menu");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFormPaginaPrincipal();
                dialogo.dispose();
            }
        });

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(boton);
        dialogo.add(panelBoton, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    
    public void mostrarErrorRetiro() {
        JDialog dialogo = new JDialog();
        dialogo.setTitle("Error");
        dialogo.setModal(true);
        dialogo.setSize(300, 200);
        dialogo.setLocationRelativeTo(null);
        dialogo.setLayout(new BorderLayout());

        JLabel mensaje = new JLabel("Error al realizar el retiro", SwingConstants.CENTER);
        mensaje.setFont(new Font("Arial", Font.PLAIN, 16));
        dialogo.add(mensaje, BorderLayout.CENTER);
        JButton boton = new JButton("Aceptar");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogo.dispose();
            }
        });
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(boton);
        dialogo.add(panelBoton, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    
    public void mostrarMensajeCuentaCreadaExitosamente(ClienteDTO cliente) {
        JDialog dialogo = new JDialog();
        dialogo.setTitle("Mensaje");
        dialogo.setModal(true);
        dialogo.setSize(300, 200);
        dialogo.setLocationRelativeTo(null);
        dialogo.setLayout(new BorderLayout());
        JLabel mensaje = new JLabel("Creacion de cuenta Exitosa", SwingConstants.CENTER);
        JLabel mensaje2 = new JLabel("numero de cliente: "+cliente.getId(), SwingConstants.CENTER);
        JLabel mensaje3 = new JLabel("Contraseña:"+cliente.getContrasenia(), SwingConstants.CENTER);
        mensaje.setFont(new Font("Arial", Font.PLAIN, 18));
        mensaje2.setFont(new Font("Arial", Font.PLAIN, 14));
        mensaje3.setFont(new Font("Arial", Font.PLAIN, 14));
        JPanel panelMensajes = new JPanel();
        panelMensajes.setLayout(new BoxLayout(panelMensajes, BoxLayout.Y_AXIS));
        panelMensajes.add(mensaje);
        panelMensajes.add(mensaje2);
        panelMensajes.add(mensaje3);
        mensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        mensaje2.setAlignmentX(Component.CENTER_ALIGNMENT);
        mensaje3.setAlignmentX(Component.CENTER_ALIGNMENT);

        dialogo.add(panelMensajes, BorderLayout.CENTER);

        JButton boton = new JButton("Regresar al menu");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFormPaginaPrincipal();
                dialogo.dispose();
            }
        });
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(boton);
        dialogo.add(panelBoton, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    
    public void mostrarErrorCrearCuenta() {
        JDialog dialogo = new JDialog();
        dialogo.setTitle("Error");
        dialogo.setModal(true);
        dialogo.setSize(300, 200);
        dialogo.setLocationRelativeTo(null);
        dialogo.setLayout(new BorderLayout());

        JLabel mensaje = new JLabel("Error al registrar el usuario", SwingConstants.CENTER);
        mensaje.setFont(new Font("Arial", Font.PLAIN, 16));
        dialogo.add(mensaje, BorderLayout.CENTER);
        JButton boton = new JButton("Aceptar");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogo.dispose();
            }
        });
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(boton);
        dialogo.add(panelBoton, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    
    public void mostrarErrorLogIn() {
        JDialog dialogo = new JDialog();
        dialogo.setTitle("Error");
        dialogo.setModal(true);
        dialogo.setSize(300, 200);
        dialogo.setLocationRelativeTo(null);
        dialogo.setLayout(new BorderLayout());

        JLabel mensaje = new JLabel("Error al iniciar sesion", SwingConstants.CENTER);
        mensaje.setFont(new Font("Arial", Font.PLAIN, 16));
        dialogo.add(mensaje, BorderLayout.CENTER);
        JButton boton = new JButton("Aceptar");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogo.dispose();
            }
        });
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(boton);
        dialogo.add(panelBoton, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    public void mostrarMensajeEstadoActualizado(int idCuenta) {
        JDialog dialogo = new JDialog();
        dialogo.setTitle("Mensaje");
        dialogo.setModal(true);
        dialogo.setSize(300, 200);
        dialogo.setLocationRelativeTo(null);
        dialogo.setLayout(new BorderLayout());
        JLabel mensaje = new JLabel("Estado actualizado con exito", SwingConstants.CENTER);
        mensaje.setFont(new Font("Arial", Font.PLAIN, 18));
        dialogo.add(mensaje, BorderLayout.CENTER);

        JButton boton = new JButton("Regresar al menu");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFormSeleccionarAccionCuenta(idCuenta);
                dialogo.dispose();
            }
        });
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(boton);
        dialogo.add(panelBoton, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    public void mostrarErrorActualizarEstado() {
        JDialog dialogo = new JDialog();
        dialogo.setTitle("Error");
        dialogo.setModal(true);
        dialogo.setSize(300, 200);
        dialogo.setLocationRelativeTo(null);
        dialogo.setLayout(new BorderLayout());

        JLabel mensaje = new JLabel("Error al actualizar el estado", SwingConstants.CENTER);
        mensaje.setFont(new Font("Arial", Font.PLAIN, 16));
        dialogo.add(mensaje, BorderLayout.CENTER);
        JButton boton = new JButton("Aceptar");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogo.dispose();
            }
        });
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(boton);
        dialogo.add(panelBoton, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    public void mostrarMensajeSolicitudRetiroExitoso(RetiroSinCuentaDTO retiro, int idCuenta) {
        JDialog dialogo = new JDialog();
        dialogo.setTitle("Mensaje");
        dialogo.setModal(true);
        dialogo.setSize(300, 200);
        dialogo.setLocationRelativeTo(null);
        dialogo.setLayout(new BorderLayout());
        JLabel mensaje = new JLabel("Solicitud de retiro Exitosa", SwingConstants.CENTER);
        JLabel mensaje2 = new JLabel("Folio:"+retiro.getFolio(), SwingConstants.CENTER);
        JLabel mensaje3 = new JLabel("Contraseña:"+retiro.getContrasenia(), SwingConstants.CENTER);
        mensaje.setFont(new Font("Arial", Font.PLAIN, 18));
        mensaje2.setFont(new Font("Arial", Font.PLAIN, 14));
        mensaje3.setFont(new Font("Arial", Font.PLAIN, 14));
        JPanel panelMensajes = new JPanel();
        panelMensajes.setLayout(new BoxLayout(panelMensajes, BoxLayout.Y_AXIS));
        panelMensajes.add(mensaje);
        panelMensajes.add(mensaje2);
        panelMensajes.add(mensaje3);
        mensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        mensaje2.setAlignmentX(Component.CENTER_ALIGNMENT);
        mensaje3.setAlignmentX(Component.CENTER_ALIGNMENT);

        dialogo.add(panelMensajes, BorderLayout.CENTER);

        JButton boton = new JButton("Regresar al menu");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFormSeleccionarAccionCuenta(idCuenta);
                dialogo.dispose();
            }
        });
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(boton);
        dialogo.add(panelBoton, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    public void mostrarErrorSolicitudRetiro() {
        JDialog dialogo = new JDialog();
        dialogo.setTitle("Error");
        dialogo.setModal(true);
        dialogo.setSize(300, 200);
        dialogo.setLocationRelativeTo(null);
        dialogo.setLayout(new BorderLayout());

        JLabel mensaje = new JLabel("Error al Solicitar el retiro", SwingConstants.CENTER);
        mensaje.setFont(new Font("Arial", Font.PLAIN, 16));
        dialogo.add(mensaje, BorderLayout.CENTER);
        JButton boton = new JButton("Aceptar");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogo.dispose();
            }
        });
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(boton);
        dialogo.add(panelBoton, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    public void mostrarMensajeTransferenciaExitosa(int idCuenta) {
        JDialog dialogo = new JDialog();
        dialogo.setTitle("Mensaje");
        dialogo.setModal(true);
        dialogo.setSize(300, 200);
        dialogo.setLocationRelativeTo(null);
        dialogo.setLayout(new BorderLayout());
        JLabel mensaje = new JLabel("transferencia realizada con exito", SwingConstants.CENTER);
        mensaje.setFont(new Font("Arial", Font.PLAIN, 18));
        dialogo.add(mensaje, BorderLayout.CENTER);

        JButton boton = new JButton("Regresar al menu");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFormSeleccionarAccionCuenta(idCuenta);
                dialogo.dispose();
            }
        });
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(boton);
        dialogo.add(panelBoton, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    public void mostrarErrorTransferencia() {
        JDialog dialogo = new JDialog();
        dialogo.setTitle("Error");
        dialogo.setModal(true);
        dialogo.setSize(300, 200);
        dialogo.setLocationRelativeTo(null);
        dialogo.setLayout(new BorderLayout());

        JLabel mensaje = new JLabel("Error al transferir el dinero", SwingConstants.CENTER);
        mensaje.setFont(new Font("Arial", Font.PLAIN, 16));
        dialogo.add(mensaje, BorderLayout.CENTER);
        JButton boton = new JButton("Aceptar");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogo.dispose();
            }
        });
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(boton);
        dialogo.add(panelBoton, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
    
    
}
