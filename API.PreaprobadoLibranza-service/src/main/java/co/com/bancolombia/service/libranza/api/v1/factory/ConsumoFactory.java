package co.com.bancolombia.service.libranza.api.v1.factory;

import com.bcol.vtd.proxy.gestionsolcreditoconsumo.util.PeticionGestionSolicitudCreditoConsumo;
import com.bcol.vtd.proxy.gestionsolcreditoconsumo.util.PeticionMomentoDos;

import co.com.bancolombia.service.libranza.api.v1.exception.ErrorAplicationException;
import co.com.bancolombia.service.libranza.api.v1.factory.dto.ParameterFactoryDTO;
import co.com.bancolombia.service.libranza.api.v1.service.comun.impl.ParameterService;
import co.com.bancolombia.service.libranza.dto.ErrorRequestDTO;

public class ConsumoFactory implements IFactory{


	/**
	 * 
	 * @param traceabilityInformation
	 * @param tipoVenta
	 * @param tipoDocumento
	 * @param numeroDocumento
	 * @param correo
	 * @param celular
	 * @return PeticionGestionSolicitudCreditoConsumo
	 */
	public PeticionGestionSolicitudCreditoConsumo createRequest(String traceabilityInformation, String tipoVenta,
			String tipoDocumento, String numeroDocumento, String correo, String celular) {

		return peticion;
	}

	/**
	 * 
	 * @param nombreArchivo
	 * @param archivo
	 * @param codigoSolicitante
	 * @param numeroSolicitud
	 * @param firmaCliente
	 * @return PeticionMomentoDos
	 */
	public PeticionMomentoDos createRequestMomentoDos(String nombreArchivo, byte[] archivo, String codigoSolicitante,
			String numeroSolicitud, String firmaCliente) {

		return peticion;
	}
}