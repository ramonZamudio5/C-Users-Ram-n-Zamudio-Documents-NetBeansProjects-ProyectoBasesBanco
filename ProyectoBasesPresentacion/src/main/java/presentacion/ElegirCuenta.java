/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import dtos.CuentaDTO;
import excepciones.PresentacionException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 *
 * @author Ramón Zamudio
 */
public class ElegirCuenta extends javax.swing.JFrame {
    ControlNavegacion control;
    String origen;
    int idCliente;
    JPanel jPanel1;
    /**
     * Creates new form ElegirCuenta
     * @param control
     * @param origen
     * @param idCliente
     */
    public ElegirCuenta(ControlNavegacion control, String origen,int idCliente) {
        initComponents();
        this.control = control;
        this.origen = origen;
        this.idCliente = idCliente;
        jPanel1 = new JPanel();
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        jPanel1.setPreferredSize(new Dimension(350, 0));
        jScrollPane1.setViewportView(jPanel1);
        cargarLabels();
        cargarScrollPane();
    }
    
    public void cargarScrollPane(){
        List<CuentaDTO> listaCuentas;
        try {
            if(origen.equals("editar")){
                listaCuentas = control.obtenerTodasLasCuentasSinImporarEstado(idCliente);
                for (CuentaDTO cuenta : listaCuentas) {
                JPanel cuentaPanel = new JPanel();
                cuentaPanel.setLayout(new BoxLayout(cuentaPanel, BoxLayout.Y_AXIS));
                cuentaPanel.setPreferredSize(new Dimension(jPanel1.getWidth(), 60));
                cuentaPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
                cuentaPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                cuentaPanel.setBackground(new Color(230, 230, 250));
                cuentaPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel lblNumeroCuenta = new JLabel("Cuenta Nº: " + cuenta.getId());
                lblNumeroCuenta.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel lblSaldo = new JLabel("Saldo: $" + String.format("%.2f", cuenta.getSaldo()));
                lblSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);

                cuentaPanel.add(lblNumeroCuenta);
                cuentaPanel.add(lblSaldo);
                jPanel1.add(cuentaPanel);
                cuentaPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        switch(origen){
                            case "editar":
                                control.openFormEditarEstado(cuenta.getId());
                                break;
                            case "retiro":
                                control.openFormSolicitarRetiro(cuenta.getId());
                                break;
                            case "transferencia":
                                control.openFormTransferencia(cuenta.getId());
                                break;
                        }
                    }
                });
            }
            }else{
                listaCuentas = control.obtenerTodasLasCuentas(idCliente);


                for (CuentaDTO cuenta : listaCuentas) {
                    JPanel cuentaPanel = new JPanel();
                    cuentaPanel.setLayout(new BoxLayout(cuentaPanel, BoxLayout.Y_AXIS));
                    cuentaPanel.setPreferredSize(new Dimension(jPanel1.getWidth(), 60));
                    cuentaPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
                    cuentaPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    cuentaPanel.setBackground(new Color(230, 230, 250));
                    cuentaPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    JLabel lblNumeroCuenta = new JLabel("Cuenta Nº: " + cuenta.getId());
                    lblNumeroCuenta.setAlignmentX(Component.CENTER_ALIGNMENT);
                    JLabel lblSaldo = new JLabel("Saldo: $" + String.format("%.2f", cuenta.getSaldo()));
                    lblSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);

                    cuentaPanel.add(lblNumeroCuenta);
                    cuentaPanel.add(lblSaldo);
                    jPanel1.add(cuentaPanel);
                    cuentaPanel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            switch(origen){
                                case "editar":
                                    control.openFormEditarEstado(cuenta.getId());
                                    dispose();
                                    break;
                                case "retiro":
                                    control.openFormSolicitarRetiro(cuenta.getId());
                                    dispose();
                                    break;
                                case "transferencia":
                                    control.openFormTransferencia(cuenta.getId());
                                    dispose();
                                    break;
                            }
                        }
                    });
                }
            }
            jPanel1.setPreferredSize(new Dimension(jScrollPane1.getWidth(), listaCuentas.size() * 70));

            jPanel1.revalidate();
            jPanel1.repaint();
        } catch (PresentacionException ex) {
            Logger.getLogger(ElegirCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void cargarLabels(){
        switch(origen){
            case "editar":
                jLabel1.setText("Seleccione la cuenta que desea editar");
                jLabel2.setVisible(false);
                break;
            case "retiro":
                jLabel1.setText("Seleccione la cuenta de la cual desea");
                jLabel2.setText("hacer el retiro");
                break;
            case "transferencia":
                jLabel1.setText("Seleccione la cuenta de la cual desea");
                jLabel2.setText( "hacer la transferencia");
                break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setText("jLabel1");

        jButton1.setText("regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(jLabel2)))
                .addContainerGap(230, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        control.openFormSeleccionarAccionCuenta(idCliente);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
