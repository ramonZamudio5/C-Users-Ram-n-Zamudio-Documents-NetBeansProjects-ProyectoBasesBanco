/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import entidades.Transferencia;
import exception.PersistenciaException;

/**
 *
 * @author Ramón Zamudio
 */
public interface ITransferenciaDAO {
    /**
     * Realiza una transferencia entre dos cuentas si hay suficiente saldo en la cuenta de origen.
     * 
     * @param transferencia Objeto que contiene los detalles de la transferencia.
     * @return El objeto Transferencia insertado.
     * @throws PersistenciaException Si no hay saldo suficiente o si ocurre un error durante el proceso.
     */
    public Transferencia realizarTransferencia(Transferencia transferencia) throws PersistenciaException;
    
}
