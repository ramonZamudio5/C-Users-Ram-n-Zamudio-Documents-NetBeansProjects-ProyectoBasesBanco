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
    private Double decimal;
    private EstadoCuenta estado;

    public Cuenta(Date fechaApertura, Double decimal, EstadoCuenta estado) {
        this.fechaApertura = fechaApertura;
        this.decimal = decimal;
        this.estado = estado;
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

    public Double getDecimal() {
        return decimal;
    }

    public void setDecimal(Double decimal) {
        this.decimal = decimal;
    }

    public EstadoCuenta getEstado() {
        return estado;
    }

    public void setEstado(EstadoCuenta estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "id=" + id + ", fechaApertura=" + fechaApertura + ", decimal=" + decimal + ", estado=" + estado + '}';
    }
    
    
}
