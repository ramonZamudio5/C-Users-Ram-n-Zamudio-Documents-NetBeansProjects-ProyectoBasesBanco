/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.TransferenciaDTO;
import entidades.Transferencia;

/**
 * Clase encargada de mapear entre las entidades Transferencia y TransferenciaDTO.
 * Proporciona métodos para convertir objetos entre las representaciones de entidad y DTO.
 * 
 * @author Ramón Zamudio
 */
public class TransferenciaMapper {

    /**
     * Convierte un TransferenciaDTO a una entidad Transferencia.
     * 
     * @param trans DTO con los datos de la transferencia.
     * @return Objeto Transferencia correspondiente.
     */
    public static Transferencia toEntity(TransferenciaDTO trans){
        return new Transferencia(trans.getFecha(), trans.getMonto(), trans.getIdOrigen(), trans.getIdDestino());
    }

    /**
     * Convierte una entidad Transferencia a un TransferenciaDTO.
     * 
     * @param trans Entidad transferencia con los datos.
     * @return DTO Transferencia con los datos correspondientes.
     */
    public static TransferenciaDTO toDTO(Transferencia trans){
        return new TransferenciaDTO(trans.getFecha(), trans.getMonto(), trans.getIdOrigen(), trans.getIdDestino());
    }
}