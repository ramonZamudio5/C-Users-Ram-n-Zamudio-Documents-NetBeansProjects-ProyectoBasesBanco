/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.CuentaDTO;
import entidades.Cuenta;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase encargada de mapear entre las entidades Cuenta y CuentaDTO.
 * Proporciona métodos para convertir objetos individuales y listas entre ambas representaciones.
 * 
 * @author Ramón Zamudio
 */
public class CuentaMapper {

    /**
     * Convierte un CuentaDTO a una entidad Cuenta.
     * 
     * @param cuenta DTO con los datos de la cuenta.
     * @return Objeto Cuenta correspondiente.
     */
    public static Cuenta toEntity(CuentaDTO cuenta){
        return new Cuenta(cuenta.getFechaApertura(), cuenta.getSaldo(), cuenta.getEstado(), cuenta.getIdCliente());
    }

    /**
     * Convierte una entidad Cuenta a un CuentaDTO.
     * 
     * @param cuenta Entidad cuenta con los datos.
     * @return DTO Cuenta con los datos correspondientes.
     */
    public static CuentaDTO toDTO(Cuenta cuenta){
        return new CuentaDTO(cuenta.getId(), cuenta.getFechaApertura(), cuenta.getSaldo(), cuenta.getEstado(), cuenta.getIdCliente());
    }

    /**
     * Convierte una lista de CuentaDTO a una lista de entidades Cuenta.
     * 
     * @param cuentas Lista de DTOs Cuenta.
     * @return Lista de entidades Cuenta.
     */
    public static List<Cuenta> listToEntity(List<CuentaDTO> cuentas){
        LinkedList<Cuenta> listaCuents = new LinkedList<>();
        for(CuentaDTO cuenta : cuentas){
            listaCuents.add(toEntity(cuenta));
        }
        return listaCuents;
    }

    /**
     * Convierte una lista de entidades Cuenta a una lista de CuentaDTO.
     * 
     * @param cuentas Lista de entidades Cuenta.
     * @return Lista de DTOs Cuenta.
     */
    public static List<CuentaDTO> listToDTO(List<Cuenta> cuentas){
        LinkedList<CuentaDTO> listaCuents = new LinkedList<>();
        for(Cuenta cuenta : cuentas){
            listaCuents.add(toDTO(cuenta));
        }
        return listaCuents;
    }
}
    

    
