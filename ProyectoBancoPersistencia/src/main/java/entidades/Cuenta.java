/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import enums.EstadoCuenta;
import java.sql.Date;

/**
 *
 * @author Ramón Zamudio
 */
public class Cuenta {
    private int id;
    private Date fechaApertura;
    private Double saldo;
    private EstadoCuenta estado;
    private int idCliente;

    public Cuenta(Date fechaApertura, Double decimal, EstadoCuenta estado) {
        this.fechaApertura = fechaApertura;
        this.saldo = decimal;
        this.estado = estado;
    }

    public Cuenta(Date fechaApertura, Double decimal, EstadoCuenta estado, int idCliente) {
        this.fechaApertura = fechaApertura;
        this.saldo = decimal;
        this.estado = estado;
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cuenta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double decimal) {
        this.saldo = decimal;
    }

    public EstadoCuenta getEstado() {
        return estado;
    }

    public void setEstado(EstadoCuenta estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "id=" + id + ", fechaApertura=" + fechaApertura + ", decimal=" + saldo + ", estado=" + estado + '}';
    }
    
    
}
