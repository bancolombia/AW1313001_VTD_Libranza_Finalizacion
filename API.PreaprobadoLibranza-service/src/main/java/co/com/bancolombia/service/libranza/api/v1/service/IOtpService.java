package co.com.bancolombia.service.libranza.api.v1.service;

import co.com.bancolombia.service.libranza.dto.ParameterOtpDTO;

public abstract class IOtpService {

	/**
	 * 
	 * @param parameterOtpDTO
	 * @param actualPage
	 * @return
	 */
	public abstract boolean validate(ParameterOtpDTO parameterOtpDTO, String actualPage);
}
