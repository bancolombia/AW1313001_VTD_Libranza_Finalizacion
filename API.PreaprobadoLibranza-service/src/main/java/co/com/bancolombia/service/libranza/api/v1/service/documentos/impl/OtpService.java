package co.com.bancolombia.service.libranza.api.v1.service.documentos.impl;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import co.com.bancolombia.service.libranza.api.v1.exception.ErrorAplicationException;
import co.com.bancolombia.service.libranza.api.v1.factory.OtpServiceFactory;
import co.com.bancolombia.service.libranza.api.v1.factory.dto.ParameterFactoryDTO;
import co.com.bancolombia.service.libranza.api.v1.service.IOtpService;
import co.com.bancolombia.service.libranza.api.v1.service.comun.impl.ConnectorService;
import co.com.bancolombia.service.libranza.api.v1.service.comun.impl.ParameterService;
import co.com.bancolombia.service.libranza.constans.Parameter;
import co.com.bancolombia.service.libranza.dto.ErrorRequestDTO;
import co.com.bancolombia.service.libranza.dto.GenerateOtpResponseDTO;
import co.com.bancolombia.service.libranza.dto.ParameterOtpDTO;
import co.com.bancolombia.service.libranza.util.Enviroment;
import co.com.bancolombia.service.libranza.util.LibranzaLog;

public class OtpService extends IOtpService {

	private OtpServiceFactory otpServiceFactory;
	private ConnectorService connectorService;
	private ParameterService parameterService;
	private String idProductoLibranza;
	private static final String PREFIJO_ERROR_GENERAR_OTP = "4-GENOTP";
	private static final String[] errorsGenerateOTP = { "99", "1021", "1022", "1023", "96", "97", "98" };

	@Inject
	public OtpService(OtpServiceFactory otpServiceFactory, ConnectorService connectorService,
			ParameterService parameterService) {
		this.otpServiceFactory = otpServiceFactory;
		this.connectorService = connectorService;
		this.parameterService = parameterService;
		this.idProductoLibranza = this.parameterService != null
				&& this.parameterService.getParametersIdProduct().get(Parameter.ID_PRODUCT) != null
						? this.parameterService.getParametersIdProduct().get(Parameter.ID_PRODUCT)
						: "4";
	}

	/**
	 * Servicio encargado de Validar el OTP
	 * 
	 * @param parameterOtpDTO
	 * @param actualPage
	 * @return boolean
	 */
	@Override
	public boolean validate(ParameterOtpDTO parameterOtpDTO, String actualPage) {
		if (Enviroment.MOCKVALIDATEOTP) {
			return true;
		}
		ParameterFactoryDTO parameterFactoryDTO = new ParameterFactoryDTO(parameterOtpDTO.getMecanismoEnrolamiento());
		IOtpService otpService = (IOtpService) otpServiceFactory.create(parameterFactoryDTO, actualPage);
		return otpService.validate(parameterOtpDTO, actualPage);
	}

	/**
	 * Servicio encargado de generar el OTP
	 * 
	 * @param parameterOtpDTO
	 * @param actualPage
	 * @return Object
	 */
	public Object generate(ParameterOtpDTO parameterOtpDTO, String actualPage) {
		if (Enviroment.MOCKGENERATEOTP) {
			return null;
		}
		LibranzaLog.logInfoMessageCharactersInit(getClass(), "OtpService-generate", true);
		String tokenApp = parameterOtpDTO.getTokenApp();
		String idSession = parameterOtpDTO.getIdSesion();
		GenerateOtpResponseDTO generateOtpResponseDTO = null;
		try {
			generateOtpResponseDTO = connectorService.generateOtpApi(idSession, tokenApp, actualPage);

			if (generateOtpResponseDTO != null && checkPosibleErrorsGenerateOtp(generateOtpResponseDTO)) {
				LibranzaLog.logInfoMessage(getClass(), "El codigo de generateOtpResponseDTO - Generando el OTP es: "
						+ generateOtpResponseDTO.getCodigo());
				ErrorRequestDTO errorRequestDTO = new ErrorRequestDTO(this.idProductoLibranza,
						PREFIJO_ERROR_GENERAR_OTP + generateOtpResponseDTO.getCodigo());
				LibranzaLog.logInfoMessage(getClass(), " :::: La API de Errores se consumira con el siguiente DTO "
						+ errorRequestDTO.toString() + " :::");
				throw new ErrorAplicationException(generateOtpResponseDTO.getDescripcion(), errorRequestDTO, idSession,
						actualPage);
			}

			LibranzaLog.logInfoMessageCharactersInit(getClass(), "OtpService-generate", false);
			return generateOtpResponseDTO;
		} catch (ErrorAplicationException e) {
			LibranzaLog.logErrorMessage(this.getClass(), e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			LibranzaLog.logErrorMessage(this.getClass(), e.getMessage(), e);
			throw new ErrorAplicationException("calling ConnectorService.getOfertaDigitalByNumeroSolicitud",
					new ErrorRequestDTO(this.idProductoLibranza, "4-CSGEOTAP019"), idSession, actualPage);
		}
	}

	/**
	 * 
	 * @param generateOtpResponseDTO
	 * @return
	 */
	private boolean checkPosibleErrorsGenerateOtp(GenerateOtpResponseDTO generateOtpResponseDTO) {
		List<String> list = Arrays.asList(errorsGenerateOTP);
		return list.contains(generateOtpResponseDTO.getCodigo());
	}
}