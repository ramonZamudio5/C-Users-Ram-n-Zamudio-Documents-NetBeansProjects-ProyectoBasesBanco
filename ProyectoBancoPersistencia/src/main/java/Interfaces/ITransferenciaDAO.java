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
    public Transferencia realizarTransferencia(Transferencia transferencia) throws PersistenciaException;
    
}
