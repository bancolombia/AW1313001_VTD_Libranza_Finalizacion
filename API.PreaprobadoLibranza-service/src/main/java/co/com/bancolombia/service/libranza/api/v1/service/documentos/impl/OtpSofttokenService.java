package co.com.bancolombia.service.libranza.api.v1.service.documentos.impl;

import javax.inject.Inject;

import com.grupobancolombia.intf.seguridadcorporativa.canales.autenticarclienteotp.v2.AutenticarClienteOTPSoftokenResponse;

import co.com.bancolombia.service.libranza.api.v1.exception.ErrorAplicationException;
import co.com.bancolombia.service.libranza.api.v1.exception.FailAplicationException;
import co.com.bancolombia.service.libranza.api.v1.factory.PImplFactory;
import co.com.bancolombia.service.libranza.api.v1.service.IOtpService;
import co.com.bancolombia.service.libranza.api.v1.service.comun.impl.ParameterService;
import co.com.bancolombia.service.libranza.constans.Parameter;
import co.com.bancolombia.service.libranza.dto.ErrorRequestDTO;
import co.com.bancolombia.service.libranza.dto.ParameterOtpDTO;
import co.com.bancolombia.service.libranza.util.LibranzaLog;

/**
 * OtpSofttokenService
 * @author 1724147
 *
 */
public class OtpSofttokenService extends IOtpService {

	private ParameterService parameterService;
	/**
	 * Constructor encargado de la inyeccion de dependencias
	 * 
	 * @param autenticarClienteOTPImplFactory autenticarClienteOTPImplFactory
	 * @param parameterService parameterService
	 */
	@Inject
	public OtpSofttokenService(PImplFactory autenticarClienteOTPImplFactory,
                               ParameterService parameterService) {
		this.autenticarClienteOTPImplFactory = autenticarClienteOTPImplFactory;
		this.parameterService = parameterService;
		this.idProductoLibranza = this.parameterService != null
				&& this.parameterService.getParametersIdProduct().get(Parameter.ID_PRODUCT) != null
						? this.parameterService.getParametersIdProduct().get(Parameter.ID_PRODUCT)
						: "4";
	}

	
	/**
	 * Valida la creacion del clienteOTP con SoftToken
	 */
	@Override
	public boolean validate(ParameterOtpDTO parameterOtpDTO, String actualPage) {

		LibranzaLog.logInfoMessageCharactersInit(getClass(), "OtpSoftTokenService-validate", true);
		boolean isValid = false;
		String tipoDocumentoOtp = new TipoDocumentoResolver()
				.obtenerTipoDocumentoOTP(parameterOtpDTO.getTipoDocumento());
		String sharedKey = tipoDocumentoOtp + parameterOtpDTO.getNumeroDocumento();

		AutenticarClienteOTPSoftokenResponse autenticarClienteOTPSoftokenResponse = 
				new AutenticarClienteOTPSoftokenResponse();

		LibranzaLog.logInfoMessageCharactersInit(getClass(), "OtpSoftTokenService-validate", false);
		return isValid;
	}

	/**
	 * 
	 * @param errorCode
	 * @param idSesion
	 * @param actualPage
	 * @return
	 */
	private boolean answerValidateOTPSoftToken(String errorCode, String idSesion, String actualPage) {


		if ("1021".equals(errorCode) || "1022".equals(errorCode)) {
			throw new ErrorAplicationException("calling OtpSofttokenService",
					errorRequestDTO, idSesion, actualPage);
		}
		throw new FailAplicationException("calling OtpSofttokenService", errorRequestDTO,
				idSesion, actualPage);
	}
}