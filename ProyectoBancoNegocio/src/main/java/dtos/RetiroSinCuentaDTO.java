/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.sql.Date;

/**
 *
 * @author Ramón Zamudio
 */
public class RetiroSinCuentaDTO {
    private int folio;
    private Date fecha;
    private String contrasenia;
    private Double monto;
    private int idCliente;

    public RetiroSinCuentaDTO(int folio, Date fecha, String contrasenia, Double monto, int idCliente) {
        this.folio = folio;
        this.fecha = fecha;
        this.contrasenia = contrasenia;
        this.monto = monto;
        this.idCliente = idCliente;
    }

    public RetiroSinCuentaDTO(Date fecha, Double monto, int idCliente) {
        this.fecha = fecha;
        this.monto = monto;
        this.idCliente = idCliente;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "RetiroSinCuentaDTO{" + "folio=" + folio + ", fecha=" + fecha + ", contrasenia=" + contrasenia + ", monto=" + monto + ", idCliente=" + idCliente + '}';
    }
    
    
}
