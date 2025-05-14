/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.time.LocalDateTime;

/**
 *
 * @author Ramón Zamudio
 */
public class RetiroSinCuentaDTO {
    private int folio;
    private LocalDateTime fecha;
    private String contrasenia;
    private Double monto;
    private int idCuenta;

    public RetiroSinCuentaDTO(int folio, LocalDateTime fecha, String contrasenia, Double monto, int idCuenta) {
        this.folio = folio;
        this.fecha = fecha;
        this.contrasenia = contrasenia;
        this.monto = monto;
        this.idCuenta = idCuenta;
    }

    public RetiroSinCuentaDTO(LocalDateTime fecha, Double monto, int idCuenta) {
        this.fecha = fecha;
        this.monto = monto;
        this.idCuenta = idCuenta;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
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

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    @Override
    public String toString() {
        return "RetiroSinCuentaDTO{" + "folio=" + folio + ", fecha=" + fecha + ", contrasenia=" + contrasenia + ", monto=" + monto + ", idCuenta=" + idCuenta + '}';
    }
    
    
}
