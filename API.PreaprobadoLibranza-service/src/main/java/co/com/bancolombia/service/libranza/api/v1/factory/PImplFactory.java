package co.com.bancolombia.service.libranza.api.v1.factory;

import com.bcol.vtd.proxy.clienteauthotp.facade.impl.AutenticarClienteOTPImpl;

import co.com.bancolombia.service.libranza.api.v1.exception.ErrorAplicationException;
import co.com.bancolombia.service.libranza.api.v1.factory.dto.ParameterFactoryDTO;
import co.com.bancolombia.service.libranza.api.v1.service.comun.impl.ParameterService;
import co.com.bancolombia.service.libranza.dto.ErrorRequestDTO;
import co.com.bancolombia.service.libranza.util.LibranzaLog;

/**
 * 
 * Factory de AutenticarClienteOTP
 * 
 * @author 1724147
 *
 */
public class PImplFactory {


	public AutenticarClienteOTPImpl createAutenticarClienteOTPImpl(String idSesion, String ipCliente,
			String tokenUsuario, String actualPage) {
		try {
			return new AutenticarClienteOTPImpl(parameterService.getParametersAutenticarClienteOTP(), idSesion,
					ipCliente, tokenUsuario);
		} catch (Exception e) {
			LibranzaLog.logErrorMessage(this.getClass(), e.getMessage(), e);
			throw new ErrorAplicationException("calling  AutenticarClienteOTPImpl",
					new ErrorRequestDTO(this.idProductoLibranza, ""), idSesion, actualPage);
		}
	}


}