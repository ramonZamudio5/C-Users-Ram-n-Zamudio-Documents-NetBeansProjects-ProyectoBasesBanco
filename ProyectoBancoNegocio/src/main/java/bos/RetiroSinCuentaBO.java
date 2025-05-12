/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import Interfaces.IRetiroSinCuentaDAO;
import dtos.RetiroSinCuentaDTO;
import entidades.RetiroSinCuenta;
import excepciones.NegocioException;
import exception.PersistenciaException;
import interfaces.IRetiroSinCuentaBO;
import mappers.RetiroMapper;

/**
 *
 * @author Ramón Zamudio
 */
public class RetiroSinCuentaBO implements IRetiroSinCuentaBO{
    private IRetiroSinCuentaDAO retiroDAO;

    public RetiroSinCuentaBO(IRetiroSinCuentaDAO retiroDAO) {
        this.retiroDAO = retiroDAO;
    }
    
    @Override
    public RetiroSinCuentaDTO agregarRetiroSinCuenta(RetiroSinCuentaDTO retiro) throws NegocioException{
        try{
            RetiroSinCuenta retiroAgregado = retiroDAO.agregarRetiroSinCuenta(RetiroMapper.toEntity(retiro));
            return RetiroMapper.toDTO(retiroAgregado);
        }catch(PersistenciaException e){
            throw new NegocioException("Error al agregar el retiro", e);
        }
    }
    @Override
    public RetiroSinCuentaDTO solicitarRetiro(int folio, String contrasenia) throws NegocioException {
        try{
            RetiroSinCuenta retiroAgregado = retiroDAO.solicitarRetiro(folio, contrasenia);
            return RetiroMapper.toDTO(retiroAgregado);
        }catch(PersistenciaException e){
            throw new NegocioException("Error al solicitar el retiro", e);
        }
    }
    @Override
    public boolean realizarRetiro(int folio, String contrasenia, double monto) throws NegocioException{
        try{
            return retiroDAO.realizarRetiro(folio, contrasenia, monto);
        }catch(PersistenciaException e){
            throw new NegocioException("Error al realizar el retiro", e);
        }
    }
}
