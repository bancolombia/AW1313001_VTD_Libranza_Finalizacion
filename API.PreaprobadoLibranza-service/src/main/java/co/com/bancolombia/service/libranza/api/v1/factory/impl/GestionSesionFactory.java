package co.com.bancolombia.service.libranza.api.v1.factory.impl;

import javax.inject.Inject;

import com.bcol.vtd.proxy.gestionsesion.facade.impl.GestionSesionImpl;

import co.com.bancolombia.service.libranza.api.v1.factory.IFactory;
import co.com.bancolombia.service.libranza.api.v1.factory.dto.ParameterFactoryDTO;
import co.com.bancolombia.service.libranza.api.v1.service.comun.impl.ParameterService;
import co.com.bancolombia.service.libranza.util.LibranzaLog;

public class GestionSesionFactory implements IFactory {

	private ParameterService parameterService;

	@Inject
	public GestionSesionFactory(ParameterService parameterService) {
		this.parameterService = parameterService;
	}

}
