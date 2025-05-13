/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import Interfaces.IRetiroSinCuentaDAO;
import conexion.IConexion;
import daos.RetiroSinCuentaDAO;
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

    public RetiroSinCuentaBO(IConexion conexion) {
        this.retiroDAO = new RetiroSinCuentaDAO(conexion);
    }
    
    @Override
    public RetiroSinCuentaDTO agregarRetiroSinCuenta(RetiroSinCuentaDTO retiro) throws NegocioException{
        if (retiro == null) {
            throw new NegocioException("El retiro no puede ser nulo.");
        }
        if (retiro.getMonto() == null || retiro.getMonto() <= 0) {
            throw new NegocioException("El monto debe ser mayor a 0.");
        }
        if (retiro.getIdCliente() <= 0) {
            throw new NegocioException("ID de cliente inválido.");
        }
        try{
            RetiroSinCuenta retiroAgregado = retiroDAO.agregarRetiroSinCuenta(RetiroMapper.toEntity(retiro));
            return RetiroMapper.toDTO(retiroAgregado);
        }catch(PersistenciaException e){
            throw new NegocioException("Error al agregar el retiro", e);
        }
    }
    @Override
    public RetiroSinCuentaDTO verificarDatos(int folio, String contrasenia) throws NegocioException {
        try{
            RetiroSinCuenta retiroAgregado = retiroDAO.verificarDatos(folio, contrasenia);
            return RetiroMapper.toDTO(retiroAgregado);
        }catch(PersistenciaException e){
            throw new NegocioException("Error al solicitar el retiro", e);
        }
    }
    @Override
    public boolean realizarRetiro(int folio, String contrasenia, double monto) throws NegocioException{
        if (contrasenia == null || contrasenia.length() != 8) {
            throw new NegocioException("La contraseña debe contener 8 dígitos.");
    }
        try{
            return retiroDAO.realizarRetiro(folio, contrasenia, monto);
        }catch(PersistenciaException e){
            throw new NegocioException("Error al realizar el retiro", e);
        }
    }
}
