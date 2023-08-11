package co.com.bancolombia.service.libranza.api.v1.factory;

import co.com.bancolombia.service.libranza.dto.ErrorResponse;
import co.com.bancolombia.service.libranza.dto.ErrorResponseDTO;

public class ErrorResponseFactory {

	/**
	 * 
	 * @param errorResponseDTO
	 * @return ErrorResponse
	 */
	public ErrorResponse create(ErrorResponseDTO errorResponseDTO) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatusCode(errorResponseDTO.getMensajeFuncional().get(0).getCodigoFuncional());
		errorResponse.setStatusDesc(errorResponseDTO.getMensajeFuncional().get(0).getDescripcionFuncional());
		return errorResponse;
	}
}