/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.RetiroSinCuentaDTO;
import entidades.RetiroSinCuenta;

/**
 * Clase encargada de mapear entre las entidades RetiroSinCuenta y RetiroSinCuentaDTO.
 * Proporciona métodos para convertir objetos individuales entre ambas representaciones.
 * 
 * @author Ramón Zamudio
 */
public class RetiroMapper {

    /**
     * Convierte un RetiroSinCuentaDTO a una entidad RetiroSinCuenta.
     * 
     * @param retiro DTO con los datos del retiro.
     * @return Objeto RetiroSinCuenta correspondiente.
     */
    public static RetiroSinCuenta toEntity(RetiroSinCuentaDTO retiro){
        return new RetiroSinCuenta(retiro.getFecha(), retiro.getMonto(), retiro.getIdCuenta());
    }

    /**
     * Convierte una entidad RetiroSinCuenta a un RetiroSinCuentaDTO.
     * 
     * @param retiro Entidad retiro con los datos.
     * @return DTO RetiroSinCuenta con los datos correspondientes.
     */
    public static RetiroSinCuentaDTO toDTO(RetiroSinCuenta retiro){
        return new RetiroSinCuentaDTO(retiro.getFolio(), retiro.getFecha(), retiro.getContrasenia(), retiro.getMonto(), retiro.getIdCuenta());
    }
}