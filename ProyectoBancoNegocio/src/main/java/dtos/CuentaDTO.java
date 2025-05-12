/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import enums.EstadoCuenta;
import java.sql.Date;

/**
 *
 * @author Ramón Zamudio
 */
public class CuentaDTO {
    private int id;
    private Date fechaApertura;
    private Double saldo;
    private EstadoCuenta estado;
    private int idCliente;

    public CuentaDTO(int id, Date fechaApertura, Double saldo, EstadoCuenta estado, int idCliente) {
        this.id = id;
        this.fechaApertura = fechaApertura;
        this.saldo = saldo;
        this.estado = estado;
        this.idCliente = idCliente;
    }

    public CuentaDTO(Date fechaApertura, Double saldo, EstadoCuenta estado, int idCliente) {
        this.fechaApertura = fechaApertura;
        this.saldo = saldo;
        this.estado = estado;
        this.idCliente = idCliente;
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

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public EstadoCuenta getEstado() {
        return estado;
    }

    public void setEstado(EstadoCuenta estado) {
        this.estado = estado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "CuentaDTO{" + "id=" + id + ", fechaApertura=" + fechaApertura + ", saldo=" + saldo + ", estado=" + estado + ", idCliente=" + idCliente + '}';
    }
    
}
