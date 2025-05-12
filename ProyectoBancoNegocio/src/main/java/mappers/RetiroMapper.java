/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.RetiroSinCuentaDTO;
import entidades.RetiroSinCuenta;

/**
 *
 * @author Ramón Zamudio
 */
public class RetiroMapper {
    public static RetiroSinCuenta toEntity(RetiroSinCuentaDTO retiro){
        return new RetiroSinCuenta(retiro.getFecha(), retiro.getMonto(), retiro.getIdCliente());
    }
    public static RetiroSinCuentaDTO toDTO(RetiroSinCuenta retiro){
        return new RetiroSinCuentaDTO(retiro.getFolio(),retiro.getFecha(),retiro.getContrasenia(), retiro.getMonto(), retiro.getIdCliente());
    }
}
