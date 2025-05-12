/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import Interfaces.ITransferenciaDAO;
import dtos.TransferenciaDTO;
import entidades.Transferencia;
import excepciones.NegocioException;
import exception.PersistenciaException;
import interfaces.ITransferenciaBO;
import mappers.TransferenciaMapper;

/**
 *
 * @author Ramón Zamudio
 */
public class TransferenciaBO implements ITransferenciaBO{
    ITransferenciaDAO transferenciaDAO;

    public TransferenciaBO(ITransferenciaDAO transferenciaDAO) {
        this.transferenciaDAO = transferenciaDAO;
    }
    @Override
    public TransferenciaDTO realizarTransferencia(TransferenciaDTO transferencia) throws NegocioException{
        try{
            Transferencia transRealizada = transferenciaDAO.realizarTransferencia(TransferenciaMapper.toEntity(transferencia));
            return TransferenciaMapper.toDTO(transRealizada);
        }catch(PersistenciaException e){
            throw new NegocioException("Error al realizar la transferencia", e);
        }
    }
}
