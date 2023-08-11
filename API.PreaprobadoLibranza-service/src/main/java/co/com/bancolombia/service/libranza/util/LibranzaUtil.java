package co.com.bancolombia.service.libranza.util;

import static co.com.bancolombia.service.libranza.util.LibranzaLog.logErrorMessage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.codehaus.jackson.map.ObjectMapper;

import com.bcol.vtd.lib.comunes.dto.InformacionCredito;
import com.bcol.vtd.lib.comunes.dto.VentaDigitalBD;
import com.bcol.vtd.lib.comunes.dto.servicioOfertaDigital.OfertaDigital;
import com.bcol.vtd.lib.comunes.exception.ValidacionException;

import co.com.bancolombia.service.libranza.dto.CompanyDTO;
import co.com.bancolombia.service.libranza.dto.CreditOfferDTO;
import co.com.bancolombia.service.libranza.dto.ErrorResponse;
import co.com.bancolombia.service.libranza.dto.ErrorResponseDTO;
import co.com.bancolombia.service.libranza.dto.MailDTO;
import co.com.bancolombia.service.libranza.dto.MensajeFuncionalDTO;
import co.com.bancolombia.service.libranza.dto.RequestInfoCreditDTO;
import co.com.bancolombia.service.libranza.dto.RequiredInformationCredit;
import co.com.bancolombia.service.libranza.model.RequiredInformationFinalization;
import co.com.bancolombia.service.libranza.wrapper.WrapperOfertaDigitalLibranza;

public class LibranzaUtil {


	public LibranzaUtil() {
		//Constructor por defecto
	}

	/**
	 * 
	 * <b>IBM. Colombia.</b>
	 *
	 * <p>
	 * Description: Method that return sub Map from parameters map based on list
	 * parameters
	 * </p>
	 *
	 * @author <A HREF="mailto:duvacano@co.ibm.com">Duban Cano</A>
	 *
	 * @param listParameters
	 * @param parameters
	 * @return Map<String, String>
	 */
	public static Map<String, String> map(List<String> listParameters, Map<String, String> parameters) {
		Map<String, String> map = new HashMap<>();

		return map;
	}

	public static String dateToMiliseconds(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd'T'HH:mm:ss");
		Date mdate = null;
		try {
			mdate = sdf.parse(date);
		} catch (ParseException e) {
			logErrorMessage(LibranzaUtil.class, e.getMessage(), e);
		}

		if (mdate == null) {
			return null;
		}

		return String.valueOf(mdate.getTime());
	}


	public static ErrorResponse defaultErrorResponse() {

		return errorResponse;

	}

	public static int doubleToInteger(double dnumber) {
		return (int) dnumber;
	}

	public static boolean isNullorEmpty(String str) {
		return null == str || "".equals(str);
	}

	public static String formatToString(Date fecha) {
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		return parser.format(fecha);
	}

	public static boolean isNullorEmpty(String... strs) {
		boolean isNullOrEmpty = false;
		if (strs.length == 0)
			return true;
		for (String str : strs) {
			isNullOrEmpty = (null == str || "".equals(str));
		}
		return isNullOrEmpty;
	}

	public static String getFileContent(String fileName) {
		String content = "";
		try {
			content = new String(Files.readAllBytes(Paths.get(fileName)));
		} catch (IOException e) {
			logErrorMessage(LibranzaUtil.class, e.getMessage(), e);
		}
		return content;
	}

	public static BigDecimal roundWithFiveDecimal(Double num) {
		return BigDecimal.valueOf(num).setScale(5, BigDecimal.ROUND_UP);
	}


	public static boolean beetween(double value, int min, int max) {
		return value >= min && value <= max;
	}

	public static boolean beetween(double value, double min, double max) {
		return min <= value && value <= max;
	}


	public static boolean isTasaVariable(String tipoTasa) {
		return "VARIABLE".equalsIgnoreCase(tipoTasa.trim());
	}

	public static String obtenerTipoCuenta(String tipoCuenta) {

		String tipoCuentaDescripcion = "";

		switch (tipoCuenta.trim()) {
		
		case ID_TIPO_CUENTA_CORRIENTE:
			    tipoCuentaDescripcion = "Corriente";
			    break;
		case ID_TIPO_CUENTA_AHORROS:
			    tipoCuentaDescripcion = "Ahorros";
			    break;
		default:
			    break;
		}

		return tipoCuentaDescripcion;
	}

	public String dummyDocument() {
		return getFileContentFromClassLoader("pdfpagare.txt");
	}

	/**
	 * Convert InputStream to String
	 * 
	 * @param is
	 * @return
	 */

	/**
	 * 
	 * @param encodeStringB64
	 * @param fileExtention
	 * @return
	 */
	public static File transformStringB64ToFile(String encodeStringB64, String fileExtention) {

		byte[] byteArrayFile = Base64.getDecoder().decode(encodeStringB64);

		File tempFile = null;

		try {
			tempFile = File.createTempFile("tmp", ".".concat(fileExtention), null);
		} catch (IOException e) {
			LibranzaLog.logErrorMessage(LibranzaUtil.class, "transformStringB64ToFile: " + e.getMessage(), e);
		}

		try (FileOutputStream fos = new FileOutputStream(tempFile)) {
			fos.write(byteArrayFile);
		} catch (IOException e) {
			LibranzaLog.logErrorMessage(LibranzaUtil.class, "Error iniciando FileOutputStream: " + e.getMessage(), e);
		}
		return tempFile;
	}
	
	public static String fileToB64String(File file) {
		byte[] encoded;
		try {
			encoded = Base64.getEncoder().encode(FileUtils.readFileToByteArray(file));
			return new String(encoded, StandardCharsets.US_ASCII);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	    
	}

	/**
	 * 
	 * @param encodeStringB64List
	 * @return
	 */
	public static File mergePDFFiles(List<String> encodeStringB64List) {

		String fileExtention = "pdf";

		List<File> listFiles = new ArrayList<File>();

		for (int i = 0; i < encodeStringB64List.size(); i++) {

			listFiles.add(transformStringB64ToFile(encodeStringB64List.get(i), fileExtention));

		}

		return mergePDFFiles(listFiles, fileExtention);
	}

	/**
	 * Funcion que une varios archivos pdf
	 * 
	 * @param encodeStringB64List lista de archivos
	 * @return Archivo pdf unido
	 */
	public static File mergePDFFiles(List<File> listFiles, String fileExtention) {

		try {

			// Instantiating PDFMergerUtility class
			PDFMergerUtility PDFmerger = new PDFMergerUtility();

			// Setting the destination file
			File mergedFile = File.createTempFile("merged", ".".concat(fileExtention), null);
			PDFmerger.setDestinationFileName(mergedFile.getAbsolutePath());

			for (File file : listFiles) {

				PDFmerger.addSource(file);
			}

			PDFmerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());

			return mergedFile;

		} catch (IOException e) {
			LibranzaLog.logErrorMessage(LibranzaUtil.class, "Error Uniendo archivos: " + e.getMessage(), e);
		}

		return null;
	}

	/**
	 * 
	 * @return retorna la informacion dummy del credito
	 */
	public static InformacionCredito getInformacionCreditoConSeguroDesempleo() {
	
		InformacionCredito informacionCredito = getInformacionCredito();
		informacionCredito.setIdPlan("D34");
		return informacionCredito;
	}

	/**
	 * 
	 * @return RequiredInformationFinalization Default
	 * 
	 * @throws ValidacionException validacionException
	 */
	public static RequiredInformationFinalization getRequiredInformationFinalizationDefault() throws ValidacionException {
		
		List<VentaDigitalBD> listaPasos = new ArrayList<>();
		OfertaDigital ofertaDigital = LibranzaUtil.getOfertaDigitalDefault();
		String documentoPdf = new WrapperOfertaDigitalLibranza(ofertaDigital).getDocumento();
		
		File archivoDummy = new File("src/test/resource/dummy.pdf");
		
		if (archivoDummy.exists()) {
			documentoPdf = fileToB64String(archivoDummy);
		}

		return requiredInformationFinalization;
	}
	
	/**
	 * Enmacarar un String
	 * @param textoAEnmascarar texto a enmascarar
	 * @param cantidadDigitosVisibles cantidad de digitos visibles
	 * @param visibleInicio Indica si los datos visibles son los iniciales o los finales
	 * @return String enmascarado
	 */

	/**
	 * Rellenar un String a la derecha
	 * @param s String a rellenar
	 * @param tamanoFinal Tama�o final del String
	 * @param caracterRelleno Caracter para rellenar
	 * @return String rellenadoo
	 */
	public static String padRight(String s, int tamanoFinal, char caracterRelleno) {
	     return String.format("%-" + tamanoFinal + "s", s).replace(' ',caracterRelleno);  
	}
	
	/**
	 * Rellenar un String a la izquierda
	 * @param s String a rellenar
	 * @param tamanoFinal Tama�o final del String
	 * @param caracterRelleno Caracter para rellenar
	 * @return String rellenadoo
	 */
    public static String padLeft(String s, int tamanoFinal, char caracterRelleno) {
        
    	return String.format("%" + tamanoFinal + "s", s).replace(' ',caracterRelleno);  
    }


}