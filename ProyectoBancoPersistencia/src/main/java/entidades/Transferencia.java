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
public class Transferencia {
    private int id;
    private Date fecha;
    private Double monto;
    private int idOrigen;
    private int idDestino;

    public Transferencia() {
    }

    public Transferencia(Date fecha, Double monto, int idOrigen, int idDestino) {
        this.fecha = fecha;
        this.monto = monto;
        this.idOrigen = idOrigen;
        this.idDestino = idDestino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public int getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(int idOrigen) {
        this.idOrigen = idOrigen;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    @Override
    public String toString() {
        return "Transferencia{" + "id=" + id + ", fecha=" + fecha + ", monto=" + monto + ", idOrigen=" + idOrigen + ", idDestino=" + idDestino + '}';
    }


    
}
