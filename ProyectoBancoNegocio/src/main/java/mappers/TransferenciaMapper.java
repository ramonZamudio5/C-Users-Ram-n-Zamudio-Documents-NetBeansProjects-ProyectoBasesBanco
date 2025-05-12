/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.TransferenciaDTO;
import entidades.Transferencia;

/**
 *
 * @author Ramón Zamudio
 */
public class TransferenciaMapper {
    public static Transferencia toEntity(TransferenciaDTO trans){
        return new Transferencia(trans.getFecha(), trans.getMonto(), trans.getIdOrigen(), trans.getIdDestino());
    }
    public static TransferenciaDTO toDTO(Transferencia trans){
        return new TransferenciaDTO(trans.getFecha(), trans.getMonto(), trans.getIdOrigen(), trans.getIdDestino());
    }
}
