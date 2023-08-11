package co.com.bancolombia.service.libranza.api.v1.factory;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.inject.Inject;

import com.bcol.vtd.lib.comunes.dto.DatosPersonales;
import com.bcol.vtd.lib.comunes.dto.InformacionCredito;
import com.bcol.vtd.lib.comunes.dto.Solicitud;
import com.bcol.vtd.lib.comunes.dto.VentaDigitalLibreInversion;
import com.bcol.vtd.lib.comunes.dto.servicioOfertaDigital.Tasas;

import co.com.bancolombia.service.libranza.api.v1.service.comun.impl.ParameterService;
import co.com.bancolombia.service.libranza.constans.Parameter;
import co.com.bancolombia.service.libranza.dto.MailDTO;
import co.com.bancolombia.service.libranza.util.Enviroment;
import co.com.bancolombia.service.libranza.util.HashUtil;
import co.com.bancolombia.service.libranza.util.LibranzaLog;
import co.com.bancolombia.service.libranza.util.LibranzaUtil;

public class MailFactory {

	private ParameterService parameterService;
	private HashUtil hashUtil;

	@Inject
	public MailFactory(ParameterService parameterService, HashUtil hashUtil) {
		this.parameterService = parameterService;
		this.hashUtil = hashUtil;
	}

	/**
	 * 
	 * @param ventaDigitalLibreInversion
	 * @return Map<String, Object>
	 */
	public Map<String, Object> create(VentaDigitalLibreInversion ventaDigitalLibreInversion) {

		return dia;

	}

	/**
	 * Funci�n que crea un dto para generar la carta de bienvenida
	 * @param correoElectronico
	 * @param primerNombre
	 * @param numeroDocumento
	 * @param fechaHoraTransaccion
	 * @param informacionCredito
	 * @param documentoPdf
	 * @param tipoTasa
	 * @param tasas
	 * @return MailDTO
	 * @throws IOException 
	 */
	public MailDTO create() {

		mailDTO.setDocumentoPdf(documentoPdf);
		
		return mailDTO;
	}

}