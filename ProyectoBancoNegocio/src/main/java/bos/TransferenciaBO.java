/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import Interfaces.ITransferenciaDAO;
import conexion.IConexion;
import daos.TransferenciaDAO;
import dtos.TransferenciaDTO;
import entidades.Transferencia;
import excepciones.NegocioException;
import exception.PersistenciaException;
import interfaces.ITransferenciaBO;
import mappers.TransferenciaMapper;

/**
 * Implementación de la lógica de negocio para las transferencias entre cuentas.
 * Esta clase valida los datos de la transferencia y utiliza el DAO para persistirla.
 * 
 * @author Ramón Zamudio
 */
public class TransferenciaBO implements ITransferenciaBO {

    private ITransferenciaDAO transferenciaDAO;

    /**
     * Constructor que recibe una conexión para inicializar el DAO de transferencia.
     * 
     * @param conexion Instancia de conexión a la base de datos.
     */
    public TransferenciaBO(IConexion conexion) {
        this.transferenciaDAO = new TransferenciaDAO(conexion);
    }

    /**
     * Realiza una transferencia entre dos cuentas validando los datos de entrada.
     * 
     * @param transferencia DTO con la información de la transferencia a realizar.
     * @return DTO con la información de la transferencia realizada.
     * @throws NegocioException Si los datos son inválidos o ocurre un error en persistencia.
     */
    @Override
    public TransferenciaDTO realizarTransferencia(TransferenciaDTO transferencia) throws NegocioException {
        if (transferencia == null) {
            throw new NegocioException("La transferencia no puede ser nula.");
        }
        if (transferencia.getMonto() == null || transferencia.getMonto() <= 0) {
            throw new NegocioException("El monto de la transferencia debe ser mayor a cero.");
        }
        if (transferencia.getIdOrigen() <= 0 || transferencia.getIdDestino() <= 0) {
            throw new NegocioException("Los IDs de cuenta de origen y destino deben ser válidos.");
        }
        if (transferencia.getIdOrigen() == transferencia.getIdDestino()) {
            throw new NegocioException("La cuenta de origen y destino no pueden ser la misma.");
        }
        try {
            Transferencia transRealizada = transferenciaDAO.realizarTransferencia(TransferenciaMapper.toEntity(transferencia));
            return TransferenciaMapper.toDTO(transRealizada);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al realizar la transferencia", e);
        }
    }
}