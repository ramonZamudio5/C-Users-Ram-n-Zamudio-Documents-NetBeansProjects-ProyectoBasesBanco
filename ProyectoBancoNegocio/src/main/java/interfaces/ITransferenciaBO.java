/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.TransferenciaDTO;
import excepciones.NegocioException;

/**
 *
 * @author Ramón Zamudio
 */
public interface ITransferenciaBO {
    public TransferenciaDTO realizarTransferencia(TransferenciaDTO transferencia) throws NegocioException;
}
