/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import Interfaces.ICuentaDAO;
import dtos.CuentaDTO;
import entidades.Cuenta;
import enums.EstadoCuenta;
import excepciones.NegocioException;
import exception.PersistenciaException;
import interfaces.ICuentaBO;
import java.sql.SQLException;
import java.util.List;
import mappers.CuentaMapper;

/**
 *
 * @author Ramón Zamudio
 */
public class CuentaBO implements ICuentaBO{
    private ICuentaDAO cuentaDAO;

    public CuentaBO(ICuentaDAO cuentaDAO) {
        this.cuentaDAO = cuentaDAO;
    }
    @Override
    public CuentaDTO agregarCuenta(Cuenta cuenta)throws NegocioException{
        try{
            Cuenta cuentaAgregada = cuentaDAO.agregarCuenta(cuenta);
            return CuentaMapper.toDTO(cuentaAgregada);
        }catch(PersistenciaException e){
            throw new NegocioException("Error al agregar la cuenta", e);
        }
    }
    @Override
    public CuentaDTO consultarCuentaPorId(int id) throws NegocioException{
        try{
            return CuentaMapper.toDTO(cuentaDAO.consultarCuentaPorId(id));
        }catch(PersistenciaException e){
            throw new NegocioException("Error al consultar con la base de datos", e);
        }
    }
    @Override
    public List<CuentaDTO> obtenerTodasLasCuentas() throws NegocioException{
        try{
            return CuentaMapper.listToDTO(cuentaDAO.obtenerTodasLasCuentas());
        }catch(PersistenciaException e){
            throw new NegocioException("Error al consultar con la base de datos", e);
        }
    } 
    @Override
    public boolean editarEstadoCuenta(int idCuenta, EstadoCuenta nuevoEstado) throws NegocioException{
        try{
            return cuentaDAO.editarEstadoCuenta(idCuenta, nuevoEstado);
        }catch(PersistenciaException e){
            throw new NegocioException("Error al editar estado de cuenta", e);
        }
    }
}
