package co.com.bancolombia.service.libranza.api.v1.factory;

import co.com.bancolombia.service.libranza.api.v1.factory.dto.ParameterFactoryDTO;

public interface IFactory {

	/**
	 * 
	 * @param parameterFactoryDTO
	 * @param actualPage
	 * @return Object
	 */
	public Object create(ParameterFactoryDTO parameterFactoryDTO, String actualPage);
}