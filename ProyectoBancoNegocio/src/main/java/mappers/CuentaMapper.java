/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.CuentaDTO;
import entidades.Cuenta;
import enums.EstadoCuenta;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public class CuentaMapper {
    public static Cuenta toEntity(CuentaDTO cuenta){
        return new Cuenta(cuenta.getFechaApertura(), cuenta.getSaldo(), cuenta.getEstado(),cuenta.getIdCliente());
    }
    public static CuentaDTO toDTO(Cuenta cuenta){
        return new CuentaDTO(cuenta.getId(),cuenta.getFechaApertura(), cuenta.getSaldo(), cuenta.getEstado(),cuenta.getIdCliente());
    }
    public static List<Cuenta> listToEntity(List<CuentaDTO> cuentas){
        LinkedList<Cuenta> listaCuents = new LinkedList<>();
        for(CuentaDTO cuenta : cuentas){
            listaCuents.add(toEntity(cuenta));
        }
        return listaCuents;
    }
    public static List<CuentaDTO> listToDTO(List<Cuenta> cuentas){
        LinkedList<CuentaDTO> listaCuents = new LinkedList<>();
        for(Cuenta cuenta : cuentas){
            listaCuents.add(toDTO(cuenta));
        }
        return listaCuents;
    }
    
}
    

    
