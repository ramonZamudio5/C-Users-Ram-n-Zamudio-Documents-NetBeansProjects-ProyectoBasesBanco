/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.sql.Date;

/**
 *
 * @author Ramón Zamudio
 */
public class RetiroSinCuenta {
    private int folio;
    private Date fecha;
    private String contrasenia;
    private Double monto;

    public RetiroSinCuenta() {
    }

    public RetiroSinCuenta(Date fecha, String contrasenia, Double monto) {
        this.fecha = fecha;
        this.contrasenia = contrasenia;
        this.monto = monto;
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

    @Override
    public String toString() {
        return "RetiroSinCuenta{" + "folio=" + folio + ", fecha=" + fecha + ", contrasenia=" + contrasenia + ", monto=" + monto + '}';
    }
    
}
