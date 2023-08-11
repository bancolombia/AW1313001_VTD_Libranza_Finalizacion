package co.com.bancolombia.service.libranza.api.v1.service.documentos.impl;

import java.math.BigInteger;
import java.util.Map;

import com.grupobancolombia.intf.canal.movil.generaciontoken.v1.CargaUtilValidarToken;
import com.grupobancolombia.intf.canal.movil.generaciontoken.v1.DatosAutenticacion;
import com.grupobancolombia.intf.canal.movil.generaciontoken.v1.DatosBasicosCliente;
import com.grupobancolombia.intf.canal.movil.generaciontoken.v1.IdentificacionCliente;
import com.grupobancolombia.intf.canal.movil.generaciontoken.v1.ValidarToken;

import co.com.bancolombia.service.libranza.constans.Parameter;

public class GenerationTokenService {

	/**
	 * Valida que el token del cliente sea valido
	 * @param tipoDocumentoBanco
	 * @param numeroDocumento
	 * @param telefono
	 * @param otp
	 * @param parameterService
	 * @return ValidarToken
	 */
	public ValidarToken obtenerDatosClientes(String tipoDocumentoBanco, String numeroDocumento, String telefono,
			String otp, Map<String, String> parameterService) {
		ValidarToken validarToken = new ValidarToken();
		DatosAutenticacion datosAutenticacion = new DatosAutenticacion();

		datosAutenticacion.setIdServidor(parameterService.get(Parameter.ID_SERVIDOR_GENERACION_TOKEN));
		datosAutenticacion.setIdSistemaFuente(parameterService.get(Parameter.PROXY_ID_SISTEMA_FUENTE_GENERACION_TOKEN));

		CargaUtilValidarToken cargaUtilValidarToken = new CargaUtilValidarToken();
		IdentificacionCliente identificacionCliente = new IdentificacionCliente();

		identificacionCliente.setNumeroDocumento(numeroDocumento);
		identificacionCliente.setTipoDocumento(tipoDocumentoBanco);

		DatosBasicosCliente datosBasicosCliente = new DatosBasicosCliente();
		BigInteger celular = new BigInteger(telefono);
		datosBasicosCliente.setCelular(celular);

		cargaUtilValidarToken.setIdentificacionCliente(identificacionCliente);
		cargaUtilValidarToken.setDatosBasicosCliente(datosBasicosCliente);
		cargaUtilValidarToken
				.setTokenCifrado(parameterService.get(Parameter.CARGA_UTIL_GENERACION_TOKEN_GENERA_TOKEN_CIFRADO));
		cargaUtilValidarToken.setTokenEntrada(otp);

		validarToken.setCargaUtilValidarToken(cargaUtilValidarToken);
		validarToken.setDatosAutenticacion(datosAutenticacion);

		return validarToken;
	}
}