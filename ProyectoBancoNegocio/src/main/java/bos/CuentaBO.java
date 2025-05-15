/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import Interfaces.ICuentaDAO;
import conexion.IConexion;
import daos.CuentaDAO;
import dtos.CuentaDTO;
import entidades.Cuenta;
import enums.EstadoCuenta;
import excepciones.NegocioException;
import exception.PersistenciaException;
import interfaces.ICuentaBO;
import java.time.LocalDate;
import java.util.List;
import mappers.CuentaMapper;

/**
 *
 * @author Ramón Zamudio
 */
public class CuentaBO implements ICuentaBO{
    private ICuentaDAO cuentaDAO;

    public CuentaBO(IConexion conexion) {
        this.cuentaDAO = new CuentaDAO(conexion);
    }
    @Override
    public CuentaDTO agregarCuenta(CuentaDTO cuenta)throws NegocioException{
        if (cuenta == null) {
        throw new NegocioException("La cuenta no puede ser nula.");
        }

        if (cuenta.getSaldo() == null || cuenta.getSaldo() < 0) {
            throw new NegocioException("El saldo inicial no puede ser nulo ni negativo.");
        }

        if (cuenta.getFechaApertura() == null) {
            throw new NegocioException("La fecha de apertura es obligatoria.");
        }

        LocalDate apertura = cuenta.getFechaApertura().toLocalDate();
        if (apertura.isAfter(LocalDate.now())) {
            throw new NegocioException("La fecha de apertura no puede ser futura.");
        }

        if (cuenta.getEstado() == null) {
            throw new NegocioException("El estado de la cuenta es obligatorio.");
        }

        if (cuenta.getIdCliente() <= 0) {
            throw new NegocioException("ID de cliente inválido.");
        }
        try{
            Cuenta cuentaAgregada = cuentaDAO.agregarCuenta(CuentaMapper.toEntity(cuenta));
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
    public List<CuentaDTO> obtenerTodasLasCuentas(int idCliente) throws NegocioException{
        if(idCliente <= 0){
            throw new NegocioException("El id no puede ser menor a 0");
        }
        try{
            return CuentaMapper.listToDTO(cuentaDAO.obtenerTodasLasCuentas(idCliente));
        }catch(PersistenciaException e){
            throw new NegocioException("Error al consultar con la base de datos", e);
        }
    } 
    @Override
    public boolean editarEstadoCuenta(int idCuenta, String nuevoEstado) throws NegocioException{
        if (nuevoEstado == null) {
            throw new NegocioException("El nuevo estado no puede ser nulo.");
        }
        EstadoCuenta enumEstado;
        if(nuevoEstado.equals("ACTIVA")){
            enumEstado = EstadoCuenta.ACTIVA;
        }else{
            enumEstado = EstadoCuenta.CANCELADO;
        }
        try{
            Cuenta cuenta = cuentaDAO.consultarCuentaPorId(idCuenta);
            if (cuenta == null) {
                throw new NegocioException("La cuenta no existe.");
            }

            if (cuenta.getEstado() == enumEstado) {
                throw new NegocioException("La cuenta ya tiene el estado especificado.");
            }
            return cuentaDAO.editarEstadoCuenta(idCuenta, enumEstado);
        }catch(PersistenciaException e){
            throw new NegocioException("Error al editar estado de cuenta", e);
        }
    }

    @Override
    public List<CuentaDTO> obtenerTodasLasCuentasSinImporarEstado(int idCliente) throws NegocioException {
        if(idCliente <= 0){
            throw new NegocioException("El id no puede ser menor a 0");
        }
        try{
            return CuentaMapper.listToDTO(cuentaDAO.obtenerTodasLasCuentasSinImporarEstado(idCliente));
        }catch(PersistenciaException e){
            throw new NegocioException("Error al consultar con la base de datos", e);
        }
    }
}
