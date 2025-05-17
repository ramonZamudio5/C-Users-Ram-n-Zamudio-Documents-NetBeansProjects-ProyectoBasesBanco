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
 * Clase que implementa la lógica de negocio para los retiros sin cuenta.
 * Valida los datos antes de delegar la operación al DAO correspondiente.
 * 
 * @author Ramón Zamudio
 */
public class RetiroSinCuentaBO implements IRetiroSinCuentaBO {
    private IRetiroSinCuentaDAO retiroDAO;

    /**
     * Constructor que inicializa el DAO de retiro sin cuenta con una conexión a base de datos.
     * 
     * @param conexion Objeto de conexión a la base de datos.
     */
    public RetiroSinCuentaBO(IConexion conexion) {
        this.retiroDAO = new RetiroSinCuentaDAO(conexion);
    }

    /**
     * Registra un nuevo retiro sin cuenta en el sistema tras validar los datos.
     * 
     * @param retiro DTO con la información del retiro a registrar.
     * @return RetiroSinCuentaDTO con los datos del retiro registrado.
     * @throws NegocioException Si los datos del retiro son inválidos o ocurre un error al persistir.
     */
    @Override
    public RetiroSinCuentaDTO agregarRetiroSinCuenta(RetiroSinCuentaDTO retiro) throws NegocioException {
        if (retiro == null) {
            throw new NegocioException("El retiro no puede ser nulo.");
        }

        if (retiro.getMonto() == null || retiro.getMonto() <= 0) {
            throw new NegocioException("El monto debe ser mayor a 0.");
        }

        if (retiro.getIdCuenta() <= 0) {
            throw new NegocioException("ID de cliente inválido.");
        }

        try {
            RetiroSinCuenta retiroAgregado = retiroDAO.agregarRetiroSinCuenta(RetiroMapper.toEntity(retiro));
            return RetiroMapper.toDTO(retiroAgregado);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al agregar el retiro", e);
        }
    }

    /**
     * Realiza un retiro sin cuenta validando el folio y la contraseña de 8 dígitos.
     * 
     * @param folio Número de folio del retiro generado previamente.
     * @param contrasenia Contraseña de 8 dígitos para autorizar el retiro.
     * @return true si el retiro fue exitoso, false si no se pudo completar.
     * @throws NegocioException Si la contraseña es inválida o ocurre un error al realizar el retiro.
     */
    @Override
    public boolean realizarRetiro(int folio, String contrasenia) throws NegocioException {
        if (contrasenia == null || contrasenia.length() != 8) {
            throw new NegocioException("La contraseña debe contener 8 dígitos.");
        }

        try {
            return retiroDAO.realizarRetiro(folio, contrasenia);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al realizar el retiro", e);
        }
    }
}