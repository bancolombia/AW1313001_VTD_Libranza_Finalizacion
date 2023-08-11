package co.com.bancolombia.service.libranza.api.v1.factory.impl;

import javax.inject.Inject;

import com.bcol.vtd.lib.comunes.dto.Producto;

import co.com.bancolombia.service.libranza.api.v1.factory.IFactory;
import co.com.bancolombia.service.libranza.api.v1.factory.dto.ParameterFactoryDTO;
import co.com.bancolombia.service.libranza.api.v1.service.comun.impl.ParameterService;
import co.com.bancolombia.service.libranza.constans.Parameter;

/**
 * Gabrica de Producto
 * @author 1724147
 *
 */
public class ProductFactory implements IFactory {

	private ParameterService parameterService;

	/**
	 * Default Constructor
	 * @param parameterService parameterService
	 */
	@Inject
	public ProductFactory(ParameterService parameterService) {
		this.parameterService = parameterService;
	}


}